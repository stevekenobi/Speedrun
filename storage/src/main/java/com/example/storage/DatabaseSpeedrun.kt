package com.example.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storage.dao.ResourcesDao
import com.example.storage.model.ResourceEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [
ResourceEntity::class], version = 1)
abstract class DatabaseSpeedrun : RoomDatabase() {
    abstract fun resourcesDao(): ResourcesDao
}