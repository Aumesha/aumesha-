package com.example.data.model

data class JobPost(
    val id: Long = 0,
    val titleKn: String,
    val titleEn: String,
    val company: String,
    val locationKn: String,
    val locationEn: String,
    val categoryKn: String,
    val categoryEn: String,
    val shortDescKn: String,
    val shortDescEn: String,
    val fullArticleKn: String,
    val fullArticleEn: String,
    val applyUrl: String,
    val sourceWebsiteName: String,
    val sourceWebsiteUrl: String,
    val postedTimestamp: Long,
    val salaryRange: String,
    val experience: String,
    val isCustomUserAdded: Boolean = false
) {
    fun getTitle(lang: AppLanguage): String = if (lang == AppLanguage.KANNADA) titleKn else titleEn
    fun getLocation(lang: AppLanguage): String = if (lang == AppLanguage.KANNADA) locationKn else locationEn
    fun getCategory(lang: AppLanguage): String = if (lang == AppLanguage.KANNADA) categoryKn else categoryEn
    fun getShortDesc(lang: AppLanguage): String = if (lang == AppLanguage.KANNADA) shortDescKn else shortDescEn
    fun getFullArticle(lang: AppLanguage): String = if (lang == AppLanguage.KANNADA) fullArticleKn else fullArticleEn

    // Formatted relative age within the 7-day window
    fun getFormattedAge(lang: AppLanguage): String {
        val diffMs = System.currentTimeMillis() - postedTimestamp
        val days = (diffMs / (1000 * 60 * 60 * 24)).coerceAtLeast(0)
        val hours = (diffMs / (1000 * 60 * 60)).coerceAtLeast(0)
        return when {
            days == 0L && hours == 0L -> if (lang == AppLanguage.KANNADA) "ಈಗಷ್ಟೇ ಅಪ್ಡೇಟ್ ಆಗಿದೆ" else "Just now"
            days == 0L -> if (lang == AppLanguage.KANNADA) "$hours ಗಂಟೆಗಳ ಹಿಂದೆ" else "$hours hours ago"
            days == 1L -> if (lang == AppLanguage.KANNADA) "ನಿನ್ನೆ" else "Yesterday"
            else -> if (lang == AppLanguage.KANNADA) "$days ದಿನಗಳ ಹಿಂದೆ" else "$days days ago"
        }
    }
}

data class JobVideo(
    val id: String,
    val titleKn: String,
    val titleEn: String,
    val channelNameKn: String,
    val channelNameEn: String,
    val duration: String,
    val thumbnailUrl: String,
    val youtubeVideoId: String,
    val publishedTimestamp: Long,
    val viewsCount: String
) {
    fun getTitle(lang: AppLanguage): String = if (lang == AppLanguage.KANNADA) titleKn else titleEn
    fun getChannel(lang: AppLanguage): String = if (lang == AppLanguage.KANNADA) channelNameKn else channelNameEn
}
