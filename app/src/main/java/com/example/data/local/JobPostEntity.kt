package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.JobPost

@Entity(tableName = "job_posts")
data class JobPostEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
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
    fun toDomainModel(): JobPost = JobPost(
        id = id,
        titleKn = titleKn,
        titleEn = titleEn,
        company = company,
        locationKn = locationKn,
        locationEn = locationEn,
        categoryKn = categoryKn,
        categoryEn = categoryEn,
        shortDescKn = shortDescKn,
        shortDescEn = shortDescEn,
        fullArticleKn = fullArticleKn,
        fullArticleEn = fullArticleEn,
        applyUrl = applyUrl,
        sourceWebsiteName = sourceWebsiteName,
        sourceWebsiteUrl = sourceWebsiteUrl,
        postedTimestamp = postedTimestamp,
        salaryRange = salaryRange,
        experience = experience,
        isCustomUserAdded = isCustomUserAdded
    )

    companion object {
        fun fromDomainModel(post: JobPost): JobPostEntity = JobPostEntity(
            id = post.id,
            titleKn = post.titleKn,
            titleEn = post.titleEn,
            company = post.company,
            locationKn = post.locationKn,
            locationEn = post.locationEn,
            categoryKn = post.categoryKn,
            categoryEn = post.categoryEn,
            shortDescKn = post.shortDescKn,
            shortDescEn = post.shortDescEn,
            fullArticleKn = post.fullArticleKn,
            fullArticleEn = post.fullArticleEn,
            applyUrl = post.applyUrl,
            sourceWebsiteName = post.sourceWebsiteName,
            sourceWebsiteUrl = post.sourceWebsiteUrl,
            postedTimestamp = post.postedTimestamp,
            salaryRange = post.salaryRange,
            experience = post.experience,
            isCustomUserAdded = post.isCustomUserAdded
        )
    }
}
