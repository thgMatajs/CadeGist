package io.gentalha.code.cadegist.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.gentalha.code.cadegist.cache.dao.GistDao
import io.gentalha.code.cadegist.cache.database.GistDatabase.Companion.DB_VERSION
import io.gentalha.code.cadegist.cache.entities.CacheGist

@Database(
    entities = [CacheGist::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class GistDatabase : RoomDatabase() {

    abstract fun gistDao(): GistDao

    companion object {
        const val DB_VERSION = 1
        private var instance: GistDatabase? = null
        private const val DB_NAME = "cade_gist"

        fun getDatabase(context: Context): GistDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { gistDatabase ->
                instance = gistDatabase
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GistDatabase::class.java,
            DB_NAME)
            .build()

        fun destroyInstance() {
            instance = null
        }
    }
}