package com.example.storage.dao

import androidx.room.*
import com.example.storage.model.ResourceEntity
import io.reactivex.Single
import java.util.HashMap

@Dao
abstract class ResourcesDao {
    @Query("SELECT * FROM resources")
    abstract fun getResources(): Single<List<ResourceEntity>>

    fun getResourcesAsMap(): Single<Map<String, String>> =
        this.getResources().map { resourceEntities ->
            val resourcesMap = HashMap<String, String>()
            for ((key, value) in resourceEntities) {
                resourcesMap[key] = value
            }
            resourcesMap
        }

    @Query("SELECT * FROM resources WHERE `key` = :key")
    abstract operator fun get(key: String): Single<ResourceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(resource: List<ResourceEntity?>)

    @Query("DELETE FROM resources")
    abstract fun clear()

    @Transaction
    open fun update(resourceEntityList: List<ResourceEntity?>?) {

        if(resourceEntityList == null)
            return

        clear()
        insert(resourceEntityList)
    }
}