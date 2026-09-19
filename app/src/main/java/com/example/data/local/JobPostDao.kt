package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JobPostDao {

    @Query("SELECT * FROM job_posts ORDER BY postedTimestamp DESC")
    fun getAllJobPostsFlow(): Flow<List<JobPostEntity>>

    @Query("""
        SELECT * FROM job_posts 
        WHERE titleKn LIKE '%' || :query || '%' 
           OR titleEn LIKE '%' || :query || '%' 
           OR company LIKE '%' || :query || '%' 
           OR locationKn LIKE '%' || :query || '%' 
           OR locationEn LIKE '%' || :query || '%' 
           OR categoryKn LIKE '%' || :query || '%' 
           OR categoryEn LIKE '%' || :query || '%' 
        ORDER BY postedTimestamp DESC
    """)
    fun searchJobPostsFlow(query: String): Flow<List<JobPostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<JobPostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: JobPostEntity): Long

    // Automatic 7-day circular rolling retention
    @Query("DELETE FROM job_posts WHERE postedTimestamp < :cutoffTimestamp")
    suspend fun deletePostsOlderThan(cutoffTimestamp: Long): Int

    @Query("SELECT COUNT(*) FROM job_posts")
    suspend fun getCount(): Int

    @Query("DELETE FROM job_posts")
    suspend fun clearAll()
}
