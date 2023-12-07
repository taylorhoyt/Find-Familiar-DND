package edu.uark.mobileprogramming.findfamiliar.Model

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CharactersRepository(private val charactersDao: CharactersDao,
                          private val characterStatsDao: CharacterStatsDao,
                          private val characterFeatsExtrasDao: CharacterFeatsExtrasDao,
                          private val characterWeaponsDao: CharacterWeaponsDao) {


    // CharactersDao ------------------------------------------------------------------------------
    fun getCharacters(): Flow<List<Characters>> = charactersDao.getCharacters()

    fun getCharacterWeapons(): Flow<List<CharacterWeapons>> = charactersDao.getCharacterWeapons()

    fun getCharacterFeats(): Flow<List<CharacterFeatsExtras>> = charactersDao.getCharacterFeats()

    fun loadAllByIds(characterIds: IntArray): List<Characters> = charactersDao.loadAllByIds(characterIds)

    suspend fun getCharacterById(characterId: Int): Characters? {
        return charactersDao.getCharacterById(characterId)
    }

    fun getCharacter(id: Int): Flow<Characters> = charactersDao.getCharacter(id)

    @WorkerThread
    suspend fun insert(character: Characters): Long = charactersDao.insert(character)

    @WorkerThread
    suspend fun insertWeapon(weapons: CharacterWeapons) = charactersDao.insert(weapons)

    @WorkerThread
    suspend fun delete(character: Characters) = charactersDao.delete(character)

    @WorkerThread
    suspend fun deleteAll() = charactersDao.deleteAll()

    @WorkerThread
    suspend fun update(character: Characters) = charactersDao.update(character)

    // CharacterStatsDao --------------------------------------------------------------------------

    suspend fun getStatsById(statsId: Int): CharacterStats? {
        return characterStatsDao.getStatsById(statsId)
    }

    @WorkerThread
    suspend fun insert(characterStats: CharacterStats) = characterStatsDao.insert(characterStats)

    @WorkerThread
    suspend fun delete(characterStats: CharacterStats) = characterStatsDao.delete(characterStats)

    @WorkerThread
    suspend fun update(characterStats: CharacterStats) = characterStatsDao.update(characterStats)


    // CharacterFeatsExtrasDao --------------------------------------------------------------------
    fun loadAllFeatsAndExtrasForCharacter(characterId: Int): Flow<List<CharacterFeatsExtras>> = characterFeatsExtrasDao.loadAllFeatsAndExtrasForCharacter(characterId)

    suspend fun getAbilityById(abilityId: Int): CharacterFeatsExtras? {
        return characterFeatsExtrasDao.getAbilityById(abilityId)
    }

    @WorkerThread
    suspend fun insert(characterFeatsExtras: CharacterFeatsExtras) = characterFeatsExtrasDao.insert(characterFeatsExtras)

    @WorkerThread
    suspend fun delete(characterFeatsExtras: CharacterFeatsExtras) = characterFeatsExtrasDao.delete(characterFeatsExtras)

    @WorkerThread
    suspend fun update(characterFeatsExtras: CharacterFeatsExtras) = characterFeatsExtrasDao.update(characterFeatsExtras)


    // CharacterWeaponsDao ------------------------------------------------------------------------
    fun loadAllWeaponsForCharacter(characterId: Int): Flow<List<CharacterWeapons>> = characterWeaponsDao.loadAllWeaponsForCharacter(characterId)

    suspend fun getWeaponById(weaponId: Int): CharacterWeapons? {
        return characterWeaponsDao.getWeaponById(weaponId)
    }

    @WorkerThread
    suspend fun insert(characterWeapons: CharacterWeapons) = characterWeaponsDao.insert(characterWeapons)

    @WorkerThread
    suspend fun delete(characterWeapons: CharacterWeapons) = characterWeaponsDao.delete(characterWeapons)

    @WorkerThread
    suspend fun update(characterWeapons: CharacterWeapons) = characterWeaponsDao.update(characterWeapons)
}
