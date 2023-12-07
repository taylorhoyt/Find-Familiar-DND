package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.uark.mobileprogramming.findfamiliar.FindFamiliarApplication
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterFeatsExtras
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterWeapons
import edu.uark.mobileprogramming.findfamiliar.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AbilityActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private var currentAbility: CharacterFeatsExtras = CharacterFeatsExtras(null, null, null, null)
    private var currentAbilityId: Int = -1
    private var currentCharacterId: Int = -1

    private val newCharacterViewModel: NewCharacterViewModel by viewModels {
        NewCharacterViewModelFactory((application as FindFamiliarApplication).repository,-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ability_info)
        nameEditText = findViewById(R.id.abilityNameEdit)
        descriptionEditText = findViewById(R.id.abilityDescriptionEdit)

        currentCharacterId = intent.getIntExtra("character_id", -1)
        currentAbilityId = intent.getIntExtra("ability_id", -1)

        if(currentAbilityId != -1){
            // then this is an existing shabang
            newCharacterViewModel.loadAbilityById(currentAbilityId)

            newCharacterViewModel.currentAbilities.observe(this) {
                ability ->
                currentAbility = ability
                updateView()
            }
        }

        val addBtn = findViewById<Button>(R.id.addAbilityBtn)
        addBtn.setOnClickListener {
            CoroutineScope(SupervisorJob()).launch {
                Log.d("AbilityActivity", "Ability insert")
                if(currentAbilityId == -1){
                    newCharacterViewModel.insertFeat(getAbilityFromView())
                } else {
                    newCharacterViewModel.updateAbility(getAbilityFromView())
                }

            }
            setResult(RESULT_OK)
            finish()
        }
    }

    private fun getAbilityFromView(): CharacterFeatsExtras{
        return CharacterFeatsExtras(
            if(currentAbilityId == -1) null else currentAbilityId,
            nameEditText.text.toString(),
            descriptionEditText.text.toString(),
            currentCharacterId
        )
    }

    private fun updateView(){
        currentAbility.featName?.let {nameEditText.setText(it)}
        currentAbility.featDescription?.let {descriptionEditText.setText(it)}
    }
}


