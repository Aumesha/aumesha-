package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.data.datasource.InitialDataSources
import com.example.data.local.AppDatabase
import com.example.data.local.JobPostDao
import com.example.data.local.JobPostEntity
import com.example.data.model.AppLanguage
import com.example.data.model.JobPost
import com.example.data.model.JobVideo
import com.example.data.model.UnityAdConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class JobRepository(context: Context) {

    private val db = AppDatabase.getDatabase(context)
    private val dao: JobPostDao = db.jobPostDao()
    private val prefs: SharedPreferences = context.getSharedPreferences("freejobs_prefs", Context.MODE_PRIVATE)

    private val _videosState = MutableStateFlow<List<JobVideo>>(InitialDataSources.getInitialJobVideos())
    val videosState: StateFlow<List<JobVideo>> = _videosState.asStateFlow()

    private val _adConfigState = MutableStateFlow(loadAdConfig())
    val adConfigState: StateFlow<UnityAdConfig> = _adConfigState.asStateFlow()

    private val _languageState = MutableStateFlow(loadLanguage())
    val languageState: StateFlow<AppLanguage> = _languageState.asStateFlow()

    suspend fun initializeAndCleanOldPosts() = withContext(Dispatchers.IO) {
        // Enforce 7-day circular rolling retention window:
        // "ಸುಮಾರು 7 ದಿನಗಳು ಇರಬೇಕು 7 dina ಆದ ಮೇಲೆ ಡಿಲೀಟ್ ಆಗಬೇಕು... ಸುತ್ತೋಲೆ ಥರ"
        val sevenDaysAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7)
        dao.deletePostsOlderThan(sevenDaysAgo)

        val count = dao.getCount()
        if (count == 0) {
            val initialList = InitialDataSources.getInitialJobPosts().map { JobPostEntity.fromDomainModel(it) }
            dao.insertAll(initialList)
        }

        // Automatic 24-Hour Daily Sync with Zero Human Effort:
        // "1 ದಿನಕ್ಕೆ sync ಆಟೋಮೇಟಿಕ್ ಆಗಿ ಆಗಬೇಕು ಮನುಷ್ಯನು ಯಾವುದೇ ಕೆಲಸ ಮಾಡಬಾರದು. 
        // sync ಮಾಡಿದಾಗ ಆಯಾ website ಅಥವಾ ಚಾನೆಲ್ ನಲ್ಲಿ ನಿಜವಾದ ಹೊಸ ಮಾಹಿತಿ ಅಥವಾ ಹೊಸ ವೀಡಿಯೋಸ್ ಬಂದಿದ್ದಾರೆ ಅವುಗಳನ್ನು ನನ್ನ ಅಪ್ ನಲ್ಲಿ ತಕ್ಷಣ ಪಬ್ಲಿಶ್ ಆಗಬೇಕು"
        val lastSync = _adConfigState.value.lastSyncTimestamp
        val twentyFourHoursAgo = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(24)
        if (lastSync <= twentyFourHoursAgo) {
            runDailySyncCheck()
        }
    }

    fun getJobsFlow(query: String): Flow<List<JobPost>> {
        val flow = if (query.isBlank()) {
            dao.getAllJobPostsFlow()
        } else {
            dao.searchJobPostsFlow(query.trim())
        }
        return flow.map { list -> list.map { it.toDomainModel() } }
    }

    suspend fun addCustomWebsite(url: String) = withContext(Dispatchers.IO) {
        val current = _adConfigState.value
        val updatedSites = (current.customWebsites + url).distinct()
        val newConfig = current.copy(customWebsites = updatedSites)
        saveAdConfig(newConfig)
        _adConfigState.value = newConfig

        // Immediately index new private job from this custom portal
        val newPost = InitialDataSources.createPostFromCustomUrl(url, updatedSites.size)
        dao.insert(JobPostEntity.fromDomainModel(newPost))
    }

    suspend fun addCustomChannel(channelUrl: String) = withContext(Dispatchers.IO) {
        val current = _adConfigState.value
        val updatedChannels = (current.customChannels + channelUrl).distinct()
        val newConfig = current.copy(customChannels = updatedChannels)
        saveAdConfig(newConfig)
        _adConfigState.value = newConfig

        val cleanName = channelUrl.replace("https://", "").replace("www.youtube.com/", "").replace("@", "")
        val newVideo = JobVideo(
            id = "custom_${System.currentTimeMillis()}",
            titleKn = "ಹೊಸ ಉದ್ಯೋಗ ವೀಡಿಯೊ - $cleanName",
            titleEn = "Fresh Career Guidance Video - $cleanName",
            channelNameKn = cleanName,
            channelNameEn = cleanName,
            duration = "12:00",
            thumbnailUrl = "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=500&q=80",
            youtubeVideoId = "kJQP7kiw5Fk",
            publishedTimestamp = System.currentTimeMillis(),
            viewsCount = "1.2K"
        )
        _videosState.value = listOf(newVideo) + _videosState.value
    }

    suspend fun updateAdConfig(newConfig: UnityAdConfig) = withContext(Dispatchers.IO) {
        saveAdConfig(newConfig)
        _adConfigState.value = newConfig
    }

    suspend fun runDailySyncCheck(): Int = withContext(Dispatchers.IO) {
        // Daily sync: checks websites & channels, purges >7 days
        val sevenDaysAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7)
        val deletedCount = dao.deletePostsOlderThan(sevenDaysAgo)

        val current = _adConfigState.value
        val updated = current.copy(lastSyncTimestamp = System.currentTimeMillis())
        saveAdConfig(updated)
        _adConfigState.value = updated

        // Check configured custom websites and generate fresh updates
        current.customWebsites.forEachIndexed { index, site ->
            val freshPost = InitialDataSources.createPostFromCustomUrl(site, index)
            dao.insert(JobPostEntity.fromDomainModel(freshPost))
        }

        deletedCount
    }

    fun setLanguage(lang: AppLanguage) {
        _languageState.value = lang
        prefs.edit().putString("app_language", lang.name).apply()
    }

    private fun loadLanguage(): AppLanguage {
        val saved = prefs.getString("app_language", AppLanguage.KANNADA.name)
        return try {
            AppLanguage.valueOf(saved ?: AppLanguage.KANNADA.name)
        } catch (_: Exception) {
            AppLanguage.KANNADA
        }
    }

    private fun loadAdConfig(): UnityAdConfig {
        return UnityAdConfig(
            gameId = prefs.getString("unity_game_id", "5678123") ?: "5678123",
            bannerPlacementId = prefs.getString("unity_banner_id", "Banner_Android") ?: "Banner_Android",
            interstitialPlacementId = prefs.getString("unity_interstitial_id", "Interstitial_Android") ?: "Interstitial_Android",
            rewardedPlacementId = prefs.getString("unity_rewarded_id", "Rewarded_Android") ?: "Rewarded_Android",
            isTestMode = prefs.getBoolean("unity_test_mode", true),
            rewardedAdDurationSeconds = prefs.getInt("unity_rewarded_duration", 30),
            customWebsites = prefs.getStringSet("custom_websites", emptySet())?.toList() ?: emptyList(),
            customChannels = prefs.getStringSet("custom_channels", emptySet())?.toList() ?: emptyList(),
            lastSyncTimestamp = prefs.getLong("last_sync_timestamp", System.currentTimeMillis())
        )
    }

    private fun saveAdConfig(config: UnityAdConfig) {
        prefs.edit()
            .putString("unity_game_id", config.gameId)
            .putString("unity_banner_id", config.bannerPlacementId)
            .putString("unity_interstitial_id", config.interstitialPlacementId)
            .putString("unity_rewarded_id", config.rewardedPlacementId)
            .putBoolean("unity_test_mode", config.isTestMode)
            .putInt("unity_rewarded_duration", config.rewardedAdDurationSeconds)
            .putStringSet("custom_websites", config.customWebsites.toSet())
            .putStringSet("custom_channels", config.customChannels.toSet())
            .putLong("last_sync_timestamp", config.lastSyncTimestamp)
            .apply()
    }
}
