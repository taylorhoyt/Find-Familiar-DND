package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.icu.text.DateFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.uark.mobileprogramming.findfamiliar.R
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.uark.mobileprogramming.findfamiliar.FindFamiliarApplication
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NewCharacterActivity : AppCompatActivity() {
    private lateinit var charName: EditText
    private lateinit var charClass: EditText
    private lateinit var charLevel: EditText
    private var characterId: Int = -1

    private val newCharacterViewModel: NewCharacterViewModel by viewModels {
        NewCharacterViewModelFactory((application as FindFamiliarApplication).repository,-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.person -> replaceFragment(CharacterInfoFragment())
                R.id.weapon -> replaceFragment(WeaponSheetFragment())
                R.id.skills -> replaceFragment(SkillsInfoFragment())
                R.id.ability -> replaceFragment(AbilitySheetFragment())
                R.id.savingthrow -> replaceFragment(SavingThrowInfoFragment())
            }
            true
        }

        characterId = intent.getIntExtra("character_id",-1)

        // Initialize with the default fragment
        if (savedInstanceState == null) {
            replaceFragment(CharacterInfoFragment())
        }

        // TODO: to update each part of the character, build update functions in each fragment,
        // TODO: and then call the update function before switching fragments
        val fab = findViewById<Button>(R.id.homeBtn)
        fab.setOnClickListener {
            CoroutineScope(SupervisorJob()).launch {
                // TODO: create new update function that takes in objects for each part
                // TODO: so like update(character, stats, feats, weapons)
                newCharacterViewModel.update(Characters(
                    characterId,
                    "name",
                    1,
                    1,
                    "Class",
                    10,
                    1,
                    "X",
                    null))
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
}
