package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
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

    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var damageDieEditText: EditText
    private lateinit var numDiceEditText: EditText
    private lateinit var weaponQualityEditText: EditText
    private lateinit var spinner: Spinner
    private var currentWeapon: CharacterWeapons = CharacterWeapons(null, null, null, null, null, null, null, null)
    private var currentWeaponId: Int = -1
    private var currentCharacterId: Int = -1

    private val newCharacterViewModel: NewCharacterViewModel by viewModels {
        NewCharacterViewModelFactory((application as FindFamiliarApplication).repository,-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapon_info)
        nameEditText = findViewById(R.id.abilityNameEdit)
        descriptionEditText = findViewById(R.id.abilityDescriptionEdit)
        damageDieEditText = findViewById(R.id.damageDieEdit)
        numDiceEditText = findViewById(R.id.numDiceEdit)
        weaponQualityEditText = findViewById(R.id.weaponQualityEdit)

        currentCharacterId = intent.getIntExtra("character_id", -1)
        currentWeaponId = intent.getIntExtra("weapon_id", -1)

        if(currentWeaponId != -1){
            // existing weapon n shit
            newCharacterViewModel.loadWeaponById(currentWeaponId)

            newCharacterViewModel.currentWeapon.observe(this){
                weapon ->
                currentWeapon = weapon
                updateView()
            }
        }

        val addBtn = findViewById<Button>(R.id.addAbilityBtn)
        addBtn.setOnClickListener {
            CoroutineScope(SupervisorJob()).launch {
                Log.d("WeaponActivity", "Weapon insert")
                if(currentWeaponId == -1){
                    newCharacterViewModel.insertWeapon(getWeaponFromView())
                } else {
                    newCharacterViewModel.updateWeapon(getWeaponFromView())
                }
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

    private fun getWeaponFromView(): CharacterWeapons {
        return CharacterWeapons(
            if(currentWeaponId == -1) null else currentWeaponId,
            nameEditText.text.toString(),
            descriptionEditText.text.toString(),
            damageDieEditText.text.toString().toIntOrNull(),
            null,
            numDiceEditText.text.toString().toIntOrNull(),
            weaponQualityEditText.text.toString().toIntOrNull(),
            currentCharacterId
        )
    }

    private fun updateView(){
        currentWeapon.weaponName?.let {nameEditText.setText(it)}
        currentWeapon.weaponDescription?.let {descriptionEditText.setText(it)}
        currentWeapon.weaponDamage?.let {damageDieEditText.setText(it.toString())}
        currentWeapon.weaponAttackModifier?.let {numDiceEditText.setText(it.toString())}
        currentWeapon.weaponQuality?.let {weaponQualityEditText.setText(it.toString())}
    }
}


