package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.uark.mobileprogramming.findfamiliar.R
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.uark.mobileprogramming.findfamiliar.FindFamiliarApplication
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NewCharacterActivity : AppCompatActivity() {
    private lateinit var charName: EditText
    private lateinit var charClass: EditText
    private lateinit var charLevel: EditText

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
                R.id.weapon -> replaceFragment(WeaponInfoFragment())
                R.id.skills -> replaceFragment(SkillsInfoFragment())
                R.id.ability -> replaceFragment(AbilityInfoFragment())
                R.id.savingthrow -> replaceFragment(SavingThrowInfoFragment())
            }
            true
        }

        // Initialize with the default fragment
        if (savedInstanceState == null) {
            replaceFragment(CharacterInfoFragment())
        }

        val fab = findViewById<FloatingActionButton>(R.id.homeBtn)
        fab.setOnClickListener {
            CoroutineScope(SupervisorJob()).launch {
                newCharacterViewModel.insert(Characters(null, "Name", 1, 1, "Class", 10, 1, "X", null))
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
