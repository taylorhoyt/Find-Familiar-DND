package edu.uark.mobileprogramming.findfamiliar.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Characters::class, CharacterStats::class, CharacterFeatsExtras::class, CharacterWeapons::class], version = 1)
abstract class CharactersRoomDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun characterStatsDao(): CharacterStatsDao
    abstract fun characterFeatsExtrasDao(): CharacterFeatsExtrasDao
    abstract fun characterWeaponsDao(): CharacterWeaponsDao

    private class CharactersDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    val cDao = database.charactersDao()
                    val cStatsDao = database.characterStatsDao()
                    val cFeatsDao = database.characterFeatsExtrasDao()
                    val cWeaponsDao = database.characterWeaponsDao()

                    // Delete all content here.
                    cWeaponsDao.deleteAll()
                    cFeatsDao.deleteAll()
                    cStatsDao.deleteAll()
                    cDao.deleteAll()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CharactersRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CharactersRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersRoomDatabase::class.java,
                    "task_database"
                )
                    .addCallback(CharactersDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
