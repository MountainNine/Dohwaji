package com.mtnine.dohwaji.database

import androidx.room.*
import com.mtnine.dohwaji.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {
    @Insert
    suspend fun insertPost(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Delete
    suspend fun deletePost(post: Post)

    @Query("SELECT * FROM Post WHERE id >= :page * :limit ORDER BY id DESC LIMIT :limit")
    suspend fun getAllPosts(page: Int, limit: Int=50) : List<Post>
}