    package edu.uark.mobileprogramming.findfamiliar

import android.util.Log
import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

    class CharactersViewModel(private val repository: CharactersRepository) : ViewModel() {

        val allCharacters: LiveData<List<Characters>> = repository.getCharacters().asLiveData()
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

        fun update(character: Characters) {
            viewModelScope.launch {
                repository.update(character)
            }
        }

        /**
         * Launching a new coroutine to insert the data in a non-blocking way
         */
        suspend fun insert(character: Characters) {
            coroutineScope {
                repository.insert(character)
            }
        }

        suspend fun getCharacter(id: Long) {

        }

        /**
         * Launches a new coroutine and inserts a new blank character
         * Includes only character stats and character info
         */
        suspend fun insertBlankCharacter(): Result<Long> {
            var characterId: Long = -1
            return withContext(Dispatchers.IO) {
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
                Log.d("CHARVM COROUTINE FINISH", characterId.toString())
                return@withContext Result.success(characterId)
            }

        }

        class CharactersViewModelFactory(private val repository: CharactersRepository) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return CharactersViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
