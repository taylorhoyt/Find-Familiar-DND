package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterFeatsExtras
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterWeapons
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
import kotlinx.coroutines.coroutineScope

class NewCharacterViewModel(private val repository: CharactersRepository, private val id:Int): ViewModel() {

    var curTask: LiveData<Characters> = repository.getCharacter(id).asLiveData()

    val allCharacterWeapons: LiveData<List<CharacterWeapons>> = repository.getCharacterWeapons().asLiveData()

    val allCharacterFeats: LiveData<List<CharacterFeatsExtras>> = repository.getCharacterFeats().asLiveData()

    fun updateId(id:Int){
        curTask = repository.getCharacter(id).asLiveData()
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
