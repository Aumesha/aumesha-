package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.AppLanguage
import com.example.ui.components.AppTopBanner
import com.example.ui.components.InAppVideoPlayerModal
import com.example.ui.components.PolicyDialog
import com.example.ui.components.SettingsDialog
import com.example.ui.components.UnityInterstitialAdModal
import com.example.ui.components.UnityRewardedAdModal
import com.example.ui.screens.JobDetailScreen
import com.example.ui.screens.JobVideosScreen
import com.example.ui.screens.JobsFeedScreen
import com.example.ui.theme.BackgroundDeep
import com.example.ui.theme.CardBorder
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.PrimaryAmber
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceHighlight
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FreeJobsViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: FreeJobsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Requirement: Hide bottom system navigation bar (triangle, circle, box),
        // and show transiently when user swipes up from the bottom!
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        insetsController.hide(WindowInsetsCompat.Type.navigationBars())

        setContent {
            MyApplicationTheme {
                FreeJobsApp(viewModel = viewModel)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // Keep system navigation bars hidden in immersive mode
            val insetsController = WindowCompat.getInsetsController(window, window.decorView)
            insetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            insetsController.hide(WindowInsetsCompat.Type.navigationBars())
        }
    }
}

@Composable
fun FreeJobsApp(viewModel: FreeJobsViewModel) {
    val language by viewModel.language.collectAsStateWithLifecycle()
    val adConfig by viewModel.adConfig.collectAsStateWithLifecycle()
    val jobs by viewModel.jobs.collectAsStateWithLifecycle()
    val videos by viewModel.videos.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val selectedJobDetail by viewModel.selectedJobDetail.collectAsStateWithLifecycle()
    val isSettingsOpen by viewModel.isSettingsOpen.collectAsStateWithLifecycle()
    val isPolicyOpen by viewModel.isPolicyOpen.collectAsStateWithLifecycle()
    val activePolicyPage by viewModel.activePolicyPage.collectAsStateWithLifecycle()
    val showInterstitialAd by viewModel.showInterstitialAd.collectAsStateWithLifecycle()
    val pendingVideoToUnlock by viewModel.pendingVideoToUnlock.collectAsStateWithLifecycle()
    val activePlayingVideo by viewModel.activePlayingVideo.collectAsStateWithLifecycle()
    val userMessage by viewModel.userMessage.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(userMessage) {
        userMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearUserMessage()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDeep)
            .statusBarsPadding()
            .navigationBarsPadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            if (selectedJobDetail == null) {
                AppTopBanner(
                    language = language,
                    onToggleLanguage = { viewModel.toggleLanguage() },
                    onOpenSettings = { viewModel.openSettings() },
                    onOpenPolicyPage = { page -> viewModel.openPolicyDialog(page) }
                )
            }
        },
        bottomBar = {
            if (selectedJobDetail == null) {
                // Exactly 2 menus as requested: "ಒಂದು job ಇನ್ನೊಂದು job videos"
                NavigationBar(
                    containerColor = SurfaceCard,
                    tonalElevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .border(1.dp, CardBorder, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .testTag("main_bottom_nav_bar")
                ) {
                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { viewModel.selectTab(0) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Work,
                                contentDescription = "Jobs Tab",
                                tint = if (selectedTab == 0) PrimaryAmber else TextSecondary
                            )
                        },
                        label = {
                            Text(
                                text = if (language == AppLanguage.KANNADA) "ಉದ್ಯೋಗಗಳು (Jobs)" else "Jobs",
                                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 11.sp,
                                color = if (selectedTab == 0) PrimaryAmber else TextSecondary
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = SurfaceHighlight
                        ),
                        modifier = Modifier.testTag("nav_jobs_tab")
                    )

                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { viewModel.selectTab(1) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.VideoLibrary,
                                contentDescription = "Videos Tab",
                                tint = if (selectedTab == 1) PrimaryAmber else TextSecondary
                            )
                        },
                        label = {
                            Text(
                                text = if (language == AppLanguage.KANNADA) "ವೀಡಿಯೊಗಳು (Job Videos)" else "Job Videos",
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 11.sp,
                                color = if (selectedTab == 1) PrimaryAmber else TextSecondary
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = SurfaceHighlight
                        ),
                        modifier = Modifier.testTag("nav_job_videos_tab")
                    )
                }
            }
        },
        containerColor = BackgroundDeep
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Detail Screen or Tab Content
            if (selectedJobDetail != null) {
                JobDetailScreen(
                    job = selectedJobDetail!!,
                    adConfig = adConfig,
                    language = language,
                    onBack = { viewModel.closeJobDetail() }
                )
            } else {
                when (selectedTab) {
                    0 -> JobsFeedScreen(
                        jobs = jobs,
                        searchQuery = searchQuery,
                        onSearchQueryChange = { viewModel.onSearchQueryChanged(it) },
                        adConfig = adConfig,
                        language = language,
                        onReadMoreClicked = { job -> viewModel.openJobDetail(job) }
                    )
                    1 -> JobVideosScreen(
                        videos = videos,
                        adConfig = adConfig,
                        language = language,
                        onVideoClicked = { video -> viewModel.onVideoClicked(video) }
                    )
                }
            }

            // Full-screen Interstitial Ad Modal when opening Videos tab
            if (showInterstitialAd) {
                UnityInterstitialAdModal(
                    adConfig = adConfig,
                    language = language,
                    onDismiss = { viewModel.onInterstitialAdDismissed() }
                )
            }

            // Rewarded Ad Modal when clicking video
            if (pendingVideoToUnlock != null) {
                UnityRewardedAdModal(
                    video = pendingVideoToUnlock!!,
                    adConfig = adConfig,
                    language = language,
                    onDismiss = { viewModel.dismissRewardedAdPrompt() },
                    onAdCompleted = { viewModel.onRewardedAdCompleted() }
                )
            }

            // In-app Video Player Modal (Plays directly inside FreeJobs app)
            if (activePlayingVideo != null) {
                InAppVideoPlayerModal(
                    video = activePlayingVideo!!,
                    adConfig = adConfig,
                    language = language,
                    onClose = { viewModel.closeInAppVideoPlayer() }
                )
            }

            // Settings Modal (Password protected with "aumesha")
            if (isSettingsOpen) {
                SettingsDialog(
                    adConfig = adConfig,
                    language = language,
                    onClose = { viewModel.closeSettings() },
                    onUpdateConfig = { newConfig -> viewModel.updateAdConfig(newConfig) },
                    onAddWebsite = { url -> viewModel.addCustomWebsite(url) },
                    onAddChannel = { ch -> viewModel.addCustomChannel(ch) },
                    onTriggerDailySync = { viewModel.triggerDailySyncCheck() }
                )
            }

            // Policy Dialog (For Unity Ad approval & Google Play compliance)
            if (isPolicyOpen) {
                PolicyDialog(
                    initialPage = activePolicyPage,
                    language = language,
                    onClose = { viewModel.closePolicyDialog() },
                    onPageSelected = { page -> viewModel.setPolicyPage(page) }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

