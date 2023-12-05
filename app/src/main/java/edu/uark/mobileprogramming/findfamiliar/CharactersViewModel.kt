    package edu.uark.mobileprogramming.findfamiliar

import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterFeatsExtras
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterStats
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterWeapons
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharactersRepository) : ViewModel() {

    val allCharacters: LiveData<List<Characters>> = repository.getCharacters().asLiveData()

    fun update(character: Characters){
        viewModelScope.launch {
            repository.update(character)
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

    /**
     * Launches a new coroutine and inserts a new blank character
     * Includes only character stats and character info
     */
    suspend fun insertBlankCharacter(){
        var characterId:Long = -1
        coroutineScope {
            val statsId = repository.insert(
                CharacterStats(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            // inserts character, which returns characterId as a long, which is then
            // converted to and saved as Int
            characterId = repository.insert(
                Characters(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    statsId.toInt()
                )
            )
            /*val weaponsId = repository.insert(
                CharacterWeapons(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    characterId.toInt()
                )
            )
            val featsId = repository.insert(
                CharacterFeatsExtras(
                    null,
                    null,
                    null,
                    characterId.toInt()
                )
            )*/
        }
    }
}

class CharactersViewModelFactory(private val repository: CharactersRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharactersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
