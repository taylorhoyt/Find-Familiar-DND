package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.uark.mobileprogramming.findfamiliar.FindFamiliarApplication
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterWeapons
import edu.uark.mobileprogramming.findfamiliar.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class WeaponActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner

    private val newCharacterViewModel: NewCharacterViewModel by viewModels {
        NewCharacterViewModelFactory((application as FindFamiliarApplication).repository,-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapon_info)

        val addBtn = findViewById<Button>(R.id.addAbilityBtn)
        addBtn.setOnClickListener {
            CoroutineScope(SupervisorJob()).launch {
                Log.d("WeaponActivity", "Weapon insert")
                newCharacterViewModel.insertWeapon(
                    CharacterWeapons(null, "The Destroyer", "", 1, 1, 1, 1, null)
                )
            }
            setResult(RESULT_OK)
            finish()
        }

        spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.stats_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}


