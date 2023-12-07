package edu.uark.mobileprogramming.findfamiliar.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    // get a list of all characters
    @Query("SELECT * FROM characters_table")
    fun getCharacters(): Flow<List<Characters>>

    @Query("SELECT * FROM character_weapons_table")
    fun getCharacterWeapons(): Flow<List<CharacterWeapons>>

    @Query("SELECT * FROM character_feats_extras_table")
    fun getCharacterFeats(): Flow<List<CharacterFeatsExtras>>

    @Query("SELECT * FROM characters_table WHERE character_id IN (:characterIds)")
    fun loadAllByIds(characterIds: IntArray): List<Characters>

    //Get a single character with a given id as LiveData
    @Query("SELECT * FROM characters_table WHERE character_id=:id")
    fun getCharacter(id:Int): Flow<Characters>

    @Query("SELECT * FROM characters_table WHERE character_id = :id")
    suspend fun getCharacterById(id: Int): Characters?

    // insert a single character and return character id
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Characters):Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weapons: CharacterWeapons)

    @Delete
    suspend fun delete(character: Characters)

    //Delete all characters
    @Query("DELETE FROM characters_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(character: Characters)
}

@Dao
interface CharacterStatsDao {
    // get character stats by stats id
    @Query("SELECT * FROM character_stats_table WHERE stats_id IN(:statsId)")
    suspend fun getStatsById(statsId: Int): CharacterStats?

    // insert character stats and return stats id
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterStats: CharacterStats):Long

    @Delete
    suspend fun delete(characterStats: CharacterStats)

    //Delete all stats
    @Query("DELETE FROM character_stats_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(characterStats: CharacterStats)
}

@Dao
interface CharacterFeatsExtrasDao {

    // get all feats and extras for a particular character
    @Query("SELECT * FROM character_feats_extras_table WHERE feats_character_id IN (:characterId)")
    fun loadAllFeatsAndExtrasForCharacter(characterId: Int): Flow<List<CharacterFeatsExtras>>

    // get one feat/extra by id
    @Query("SELECT * FROM character_feats_extras_table WHERE feats_id IN(:featsId)")
    suspend fun getAbilityById(featsId: Int): CharacterFeatsExtras?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterFeatsExtras: CharacterFeatsExtras)

    @Delete
    suspend fun delete(characterFeatsExtras: CharacterFeatsExtras)

    //Delete all feats and extras
    @Query("DELETE FROM character_feats_extras_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(characterFeatsExtras: CharacterFeatsExtras)
}

@Dao
interface CharacterWeaponsDao {

    // get all weapons for a particular character
    @Query("SELECT * FROM character_weapons_table WHERE weapons_character_id IN (:characterId)")
    fun loadAllWeaponsForCharacter(characterId: Int): Flow<List<CharacterWeapons>>

    // get a single weapon by id
    @Query("SELECT * FROM character_weapons_table WHERE weapons_id IN(:weaponId)")
    suspend fun getWeaponById(weaponId: Int): CharacterWeapons?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterWeapons: CharacterWeapons)

    @Delete
    suspend fun delete(characterWeapons: CharacterWeapons)

    // Delete all weapons
    @Query("DELETE FROM character_weapons_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(characterWeapons: CharacterWeapons)
}
