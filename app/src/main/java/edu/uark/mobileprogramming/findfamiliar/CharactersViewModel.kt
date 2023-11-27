    package edu.uark.mobileprogramming.findfamiliar

import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharactersRepository) : ViewModel() {

    val allCharacters: LiveData<List<Characters>> = repository.getCharacters().asLiveData()

    fun update(character: Characters){
        viewModelScope.launch {
            repository.update(character)
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
