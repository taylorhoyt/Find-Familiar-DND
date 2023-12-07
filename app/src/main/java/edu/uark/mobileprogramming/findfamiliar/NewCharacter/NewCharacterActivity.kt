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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewCharacterActivity : AppCompatActivity() {

    var characterId: Int = -1
    private var currentCharacter: Characters = Characters(null, null, null, null, null, null, null, null, null)
    private var currentCharacterStats: CharacterStats = CharacterStats(null, null, null, null, null, null, null)

    val newCharacterViewModel: NewCharacterViewModel by viewModels {
        NewCharacterViewModelFactory((application as FindFamiliarApplication).repository,characterId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.person -> replaceFragment(CharacterInfoFragment().apply{
                    arguments = Bundle().apply {
                        putInt("character_id", characterId)
                    }
                })
                R.id.weapon -> replaceFragment(WeaponSheetFragment().apply{
                    arguments = Bundle().apply {
                        putInt("character_id", characterId)
                    }
                })
                R.id.skills -> replaceFragment(SkillsInfoFragment())
                R.id.ability -> replaceFragment(AbilitySheetFragment().apply{
                    arguments = Bundle().apply {
                        putInt("character_id", characterId)
                    }
                })
                R.id.savingthrow -> replaceFragment(SavingThrowInfoFragment())
            }
            true
        }

        characterId = intent.getIntExtra("character_id",-1)
        Log.d("NEW CHAR ACTIVITY ID", characterId.toString())
        if (savedInstanceState == null) {
            replaceFragment(CharacterInfoFragment().apply{
                arguments = Bundle().apply {
                    putInt("character_id", characterId)
                }
            })
        }

        val fab = findViewById<Button>(R.id.homeBtn)
        fab.setOnClickListener {
            CoroutineScope(SupervisorJob()).launch {

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


    fun updateCharacterSkills(characterStats: CharacterStats){
        currentCharacterStats = characterStats
    }

    fun getCharacterSkills(): CharacterStats{
        return currentCharacterStats
    }


}
