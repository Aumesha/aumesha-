package com.example.data.model

data class UnityAdConfig(
    val gameId: String = "5678123",
    val bannerPlacementId: String = "Banner_Android",
    val interstitialPlacementId: String = "Interstitial_Android",
    val rewardedPlacementId: String = "Rewarded_Android",
    val isTestMode: Boolean = true,
    val rewardedAdDurationSeconds: Int = 30,
    val customWebsites: List<String> = emptyList(),
    val customChannels: List<String> = emptyList(),
    val lastSyncTimestamp: Long = System.currentTimeMillis()
)
