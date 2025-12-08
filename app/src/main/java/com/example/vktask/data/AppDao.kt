package com.example.vktask.data

import androidx.room.*

@Dao
interface AppDao {

    @Query("SELECT * FROM apps")
    suspend fun getAll(): List<AppEntity>

    @Query("SELECT * FROM apps WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): AppEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(app: AppEntity)

    @Update
    suspend fun update(app: AppEntity)
}
