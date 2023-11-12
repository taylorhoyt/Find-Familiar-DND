package edu.uark.mobileprogramming.findfamiliar
import android.app.Application
import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FindFamiliarApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CharactersRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { CharactersRepository(
        database.charactersDao(),
        database.characterStatsDao(),
        database.characterFeatsExtrasDao(),
        database.characterWeaponsDao())
    }
}