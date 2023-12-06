package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.appcompat.app.AppCompatActivity
import edu.uark.mobileprogramming.findfamiliar.R
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.uark.mobileprogramming.findfamiliar.FindFamiliarApplication
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterStats
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NewCharacterActivity : AppCompatActivity() {

    private var characterId: Int = -1
    lateinit var currentLiveCharacter: LiveData<Characters>
    private lateinit var currentCharacter: Characters
    private lateinit var currentCharacterStats: CharacterStats
    private var isNewCharacter = true

    private val newCharacterViewModel: NewCharacterViewModel by viewModels {
        NewCharacterViewModelFactory((application as FindFamiliarApplication).repository,-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.person -> replaceFragment(CharacterInfoFragment(isNewCharacter, characterId))
                R.id.weapon -> replaceFragment(WeaponSheetFragment())
                R.id.skills -> replaceFragment(SkillsInfoFragment())
                R.id.ability -> replaceFragment(AbilitySheetFragment())
                R.id.savingthrow -> replaceFragment(SavingThrowInfoFragment())
            }
            true
        }

        // Initialize with the default fragment
        if (savedInstanceState == null) {
            replaceFragment(CharacterInfoFragment(isNewCharacter, characterId))
        }

        characterId = intent.getIntExtra("character_id",-1)
        Log.d("NEW CHAR ACTIVITY ID", characterId.toString() )
        newCharacterViewModel.updateId(characterId)
        newCharacterViewModel.currentCharacter.observe(this) { character ->
            currentCharacter = character
            currentLiveCharacter = newCharacterViewModel.currentCharacter
        }

        /*TODO: to update the database, the fragment can call getActivity() in onStop(), and then
        *  update the character.*/
        // TODO: to update each part of the character, build update functions in each fragment,
        // TODO: and then call the update function before switching fragments
        val fab = findViewById<Button>(R.id.homeBtn)
        fab.setOnClickListener {
            CoroutineScope(SupervisorJob()).launch {
                // TODO: create new update function that takes in objects for each part
                // TODO: so like update(character, stats, feats, weapons)
                newCharacterViewModel.update(currentCharacter)
                //newCharacterViewModel.updateStats(currentCharacterStats)
            }
            setResult(RESULT_OK)
            finish()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.new_fragment_container, fragment)
            .commit()
    }

    fun updateCharacterInfo(character:Characters){
        currentCharacter = character
    }

    fun getCharacterInfo(): Characters{
        return currentCharacter
    }

    fun updateCharacterSkills(characterStats: CharacterStats){
        currentCharacterStats = characterStats
    }

    fun getCharacterSkills(): CharacterStats{
        return currentCharacterStats
    }


}
