package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.AppLanguage
import com.example.data.model.JobPost
import com.example.data.model.JobVideo
import com.example.data.model.UnityAdConfig
import com.example.data.repository.JobRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class PolicyPage {
    ABOUT,
    PRIVACY_POLICY,
    TERMS,
    DISCLAIMER,
    CONTACT
}

@OptIn(ExperimentalCoroutinesApi::class)
class FreeJobsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobRepository(application)

    val language: StateFlow<AppLanguage> = repository.languageState
    val adConfig: StateFlow<UnityAdConfig> = repository.adConfigState
    val videos: StateFlow<List<JobVideo>> = repository.videosState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val jobs: StateFlow<List<JobPost>> = _searchQuery
        .flatMapLatest { query -> repository.getJobsFlow(query) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedTab = MutableStateFlow(0) // 0 = Jobs, 1 = Job Videos
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    private val _selectedJobDetail = MutableStateFlow<JobPost?>(null)
    val selectedJobDetail: StateFlow<JobPost?> = _selectedJobDetail.asStateFlow()

    private val _isSettingsOpen = MutableStateFlow(false)
    val isSettingsOpen: StateFlow<Boolean> = _isSettingsOpen.asStateFlow()

    private val _isPolicyOpen = MutableStateFlow(false)
    val isPolicyOpen: StateFlow<Boolean> = _isPolicyOpen.asStateFlow()

    private val _activePolicyPage = MutableStateFlow(PolicyPage.ABOUT)
    val activePolicyPage: StateFlow<PolicyPage> = _activePolicyPage.asStateFlow()

    // Full screen ad when opening videos tab
    private val _showInterstitialAd = MutableStateFlow(false)
    val showInterstitialAd: StateFlow<Boolean> = _showInterstitialAd.asStateFlow()

    // Rewarded ad on video click
    private val _pendingVideoToUnlock = MutableStateFlow<JobVideo?>(null)
    val pendingVideoToUnlock: StateFlow<JobVideo?> = _pendingVideoToUnlock.asStateFlow()

    private val _activePlayingVideo = MutableStateFlow<JobVideo?>(null)
    val activePlayingVideo: StateFlow<JobVideo?> = _activePlayingVideo.asStateFlow()

    private val _userMessage = MutableStateFlow<String?>(null)
    val userMessage: StateFlow<String?> = _userMessage.asStateFlow()

    init {
        viewModelScope.launch {
            repository.initializeAndCleanOldPosts()
        }
        // Continuous automated daily sync check without human intervention
        // Requirement: "1 ದಿನಕ್ಕೆ sync ಆಟೋಮೇಟಿಕ್ ಆಗಿ ಆಗಬೇಕು ಮನುಷ್ಯನು ಯಾವುದೇ ಕೆಲಸ ಮಾಡಬಾರದು"
        viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(1000L * 60 * 30) // Check periodically in background
                val lastSync = adConfig.value.lastSyncTimestamp
                val oneDayAgo = System.currentTimeMillis() - java.util.concurrent.TimeUnit.HOURS.toMillis(24)
                if (lastSync <= oneDayAgo) {
                    repository.runDailySyncCheck()
                }
            }
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun selectTab(tabIndex: Int) {
        if (tabIndex == 1 && _selectedTab.value != 1) {
            // Per user prompt requirement:
            // "Videos menu ಒತ್ತಿದ ಕೂಡಲೇ ಒಂದು full Screen ad ಬರಬೇಕು. ಅದು ಮುಗಿದ ಮೇಲೆ 2 ನೇ ಮೆನು open ಆಗಬೇಕು."
            _showInterstitialAd.value = true
        } else {
            _selectedTab.value = tabIndex
        }
    }

    fun onInterstitialAdDismissed() {
        _showInterstitialAd.value = false
        _selectedTab.value = 1 // Switch to Videos tab after full-screen ad
    }

    fun openJobDetail(post: JobPost) {
        _selectedJobDetail.value = post
    }

    fun closeJobDetail() {
        _selectedJobDetail.value = null
    }

    fun openSettings() {
        _isSettingsOpen.value = true
    }

    fun closeSettings() {
        _isSettingsOpen.value = false
    }

    fun openPolicyDialog(page: PolicyPage = PolicyPage.ABOUT) {
        _activePolicyPage.value = page
        _isPolicyOpen.value = true
    }

    fun closePolicyDialog() {
        _isPolicyOpen.value = false
    }

    fun setPolicyPage(page: PolicyPage) {
        _activePolicyPage.value = page
    }

    fun toggleLanguage() {
        val current = language.value
        val next = if (current == AppLanguage.KANNADA) AppLanguage.ENGLISH else AppLanguage.KANNADA
        repository.setLanguage(next)
    }

    fun updateAdConfig(newConfig: UnityAdConfig) {
        viewModelScope.launch {
            repository.updateAdConfig(newConfig)
            _userMessage.value = if (language.value == AppLanguage.KANNADA) 
                "Unity Ad ಐಡಿಗಳು ಮತ್ತು ಸೆಟ್ಟಿಂಗ್ಸ್ ಯಶಸ್ವಿಯಾಗಿ ನವೀಕರಿಸಲಾಗಿದೆ" 
            else 
                "Unity Ad IDs and settings updated successfully"
        }
    }

    fun addCustomWebsite(url: String) {
        if (url.isBlank()) return
        viewModelScope.launch {
            repository.addCustomWebsite(url)
            _userMessage.value = if (language.value == AppLanguage.KANNADA) 
                "ಹೊಸ ಖಾಸಗಿ ವೆಬ್‌ಸೈಟ್ ಸೇರಿಸಲಾಗಿದೆ & ಮಾಹಿತಿಯನ್ನು ಸಿಂಕ್ ಮಾಡಲಾಗಿದೆ!" 
            else 
                "Custom website added & job updates synchronized!"
        }
    }

    fun addCustomChannel(channelUrl: String) {
        if (channelUrl.isBlank()) return
        viewModelScope.launch {
            repository.addCustomChannel(channelUrl)
            _userMessage.value = if (language.value == AppLanguage.KANNADA) 
                "ಹೊಸ YouTube ಚಾನೆಲ್ ಸೇರಿಸಲಾಗಿದೆ!" 
            else 
                "New YouTube channel added successfully!"
        }
    }

    fun triggerDailySyncCheck() {
        viewModelScope.launch {
            val purged = repository.runDailySyncCheck()
            _userMessage.value = if (language.value == AppLanguage.KANNADA) 
                "ದೈನಂದಿನ ಪರಿಶೀಲನೆ ಪೂರ್ಣಗೊಂಡಿದೆ. 7 ದಿನಕ್ಕಿಂತ ಹಳೆಯದಾದ $purged ಪೋಸ್ಟ್‌ಗಳನ್ನು ಸ್ವಚ್ಛಗೊಳಿಸಲಾಗಿದೆ." 
            else 
                "Daily check complete. $purged posts older than 7 days removed."
        }
    }

    // Video click -> Rewarded ad workflow
    fun onVideoClicked(video: JobVideo) {
        _pendingVideoToUnlock.value = video
    }

    fun dismissRewardedAdPrompt() {
        _pendingVideoToUnlock.value = null
    }

    fun onRewardedAdCompleted() {
        val video = _pendingVideoToUnlock.value
        _pendingVideoToUnlock.value = null
        if (video != null) {
            _activePlayingVideo.value = video
        }
    }

    fun closeInAppVideoPlayer() {
        _activePlayingVideo.value = null
    }

    fun clearUserMessage() {
        _userMessage.value = null
    }
}
