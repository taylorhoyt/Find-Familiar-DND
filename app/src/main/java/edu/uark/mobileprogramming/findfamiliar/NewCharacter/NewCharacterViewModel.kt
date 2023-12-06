package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterFeatsExtras
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterStats
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterWeapons
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewCharacterViewModel(private val repository: CharactersRepository, private val id:Int): ViewModel() {

    var currentCharacterStats: LiveData<CharacterStats> = repository.loadAllStatsForCharacter(id).asLiveData()

    var allCharacterWeapons: LiveData<List<CharacterWeapons>> = repository.getCharacterWeapons().asLiveData()

    var allCharacterFeats: LiveData<List<CharacterFeatsExtras>> = repository.getCharacterFeats().asLiveData()

    private val _currentCharacter = MutableLiveData<Characters>()
    val currentCharacter: LiveData<Characters>
        get() = _currentCharacter


    fun loadCharacterById(characterId: Int){
        viewModelScope.launch {
            val character = repository.getCharacterById(characterId)
            character?.let {
                _currentCharacter.value = it
            }
        }
    }


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    suspend fun insert(character: Characters){
        coroutineScope {
            repository.insert(character)
        }
    }

    suspend fun insertWeapon(weapon: CharacterWeapons){
        coroutineScope {
            repository.insert(weapon)
        }
    }

    suspend fun insertFeat(feat: CharacterFeatsExtras){
        coroutineScope {
            repository.insert(feat)
        }
    }

    /**
     * Launching a new coroutine to Update the data in a non-blocking way
     */
    suspend fun update(character: Characters) {
        coroutineScope {
            repository.update(character)
        }
    }

    suspend fun updateStats(characterStats: CharacterStats){
        coroutineScope {
            repository.update(characterStats)
        }
    }
}

class NewCharacterViewModelFactory(private val repository: CharactersRepository,private val id:Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewCharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewCharacterViewModel(repository,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
