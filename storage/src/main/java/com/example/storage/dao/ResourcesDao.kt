package com.example.storage.dao

import androidx.room.*
import com.example.storage.model.ResourceEntity
import java.util.HashMap

@Dao
abstract class ResourcesDao {
    @Query("SELECT * FROM resources")
    abstract suspend fun getResources(): List<ResourceEntity>

    suspend fun getResourcesAsMap(): Map<String, String> {
        val result = HashMap<String, String>()
        this.getResources().forEach {
            result[it.key] = it.value
        }
        return result
    }

    @Query("SELECT * FROM resources WHERE `key` = :key")
    abstract suspend fun get(key: String): ResourceEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(resource: List<ResourceEntity>)

    @Query("DELETE FROM resources")
    abstract fun clear()

    @Transaction
    open suspend fun update(resourceEntityList: List<ResourceEntity>) {

        if(resourceEntityList.isEmpty())
            return

        clear()
        insert(resourceEntityList)
    }
}