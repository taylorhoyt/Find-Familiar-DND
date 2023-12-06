package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import edu.uark.mobileprogramming.findfamiliar.FindFamiliarApplication
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterStats
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import edu.uark.mobileprogramming.findfamiliar.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CHARACTER_ID = "character_id"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterInfoFragment() : Fragment() {

    private lateinit var view: View
    private lateinit var nameEditText: EditText
    private lateinit var classEditText: EditText
    private lateinit var levelEditText: EditText
    private lateinit var armorClassEditText: EditText
    private lateinit var strengthEditText: EditText
    private lateinit var intelligenceEditText: EditText
    private lateinit var dexterityEditText: EditText
    private lateinit var wisdomEditText: EditText
    private lateinit var constitutionEditText: EditText
    private lateinit var charismaEditText: EditText
    private lateinit var addBtn: Button
    private lateinit var subBtn: Button
    private lateinit var healthEditText: EditText
    private var currentCharacter: Characters = Characters(null, null, null, null, null, null, null, null, null)
    private var currentCharacterId: Int = -1

    private val newCharacterViewModel: NewCharacterViewModel by lazy {
        (requireActivity() as NewCharacterActivity).newCharacterViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(activity as NewCharacterActivity)
    }

    override fun onStop(){
        super.onStop()
        saveCharacter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_character_info, container, false)
        nameEditText = view.findViewById(R.id.nameEdit)
        classEditText = view.findViewById(R.id.classEdit)
        levelEditText = view.findViewById(R.id.levelEdit)
        armorClassEditText = view.findViewById(R.id.armorClassEdit)
        strengthEditText = view.findViewById(R.id.strengthView)
        intelligenceEditText = view.findViewById(R.id.intelligenceEdit)
        dexterityEditText = view.findViewById(R.id.dexterityView)
        wisdomEditText = view.findViewById(R.id.wisdomEdit)
        constitutionEditText = view.findViewById(R.id.constitutionEdit)
        charismaEditText = view.findViewById(R.id.charismaEdit)
        healthEditText = view.findViewById(R.id.hpEdit)
        addBtn = view.findViewById(R.id.addHealthButton)
        subBtn = view.findViewById(R.id.subtractHealthButton)
        addBtn.setOnClickListener {
            val currentValue = healthEditText.text.toString().toIntOrNull() ?: 0
            val newValue = currentValue + 1
            newValue.let{healthEditText.setText(it.toString())}

        }
        subBtn.setOnClickListener {
            val currentValue = healthEditText.text.toString().toIntOrNull() ?: 0
            val newValue = currentValue - 1
            if (currentValue > 0) {
                newValue.let{healthEditText.setText(it.toString())}
            }
        }


        currentCharacterId = arguments?.getInt(ARG_CHARACTER_ID, -1)!!
        Log.d("CHAR INFO FRAG", "Character ID: $currentCharacterId")
        newCharacterViewModel.loadCharacterById(currentCharacterId)

        newCharacterViewModel.currentCharacter.observe(viewLifecycleOwner) {
            character ->
            Log.d("YourFragment", "Current Character: $character")
            currentCharacter = character
            updateView()
        }
        return view
    }

    private fun saveCharacter(){
        Log.d("CHARACTER INFO DEAD", "updating")
        CoroutineScope(SupervisorJob()).launch {
            newCharacterViewModel.update(getCharacterInfoFromView())
        }
    }

    private fun getCharacterInfoFromView(): Characters{
        return Characters(
            currentCharacterId,
            nameEditText.text.toString(),
            healthEditText.text.toString().toIntOrNull(),
            armorClassEditText.text.toString().toIntOrNull(),
            classEditText.text.toString(),
            levelEditText.text.toString().toIntOrNull(),
            currentCharacter.proficiencyBonus,
            currentCharacter.proficiencies,
            currentCharacter.statsId
        )
    }

    private fun updateView(){
        currentCharacter.name?.let{nameEditText.setText(it)}
        currentCharacter.className?.let{ classEditText.setText(it)}
        currentCharacter.level?.let { levelEditText.setText(it.toString()) }
        currentCharacter.armor?.let { armorClassEditText.setText(it.toString()) }
        currentCharacter.health?.let { healthEditText.setText(it.toString() ) }
    }

    private fun getCharacterStatsFromView(): CharacterStats {
        return CharacterStats(
            currentCharacter.statsId,
            strengthEditText.toString().toIntOrNull(),
            constitutionEditText.toString().toIntOrNull(),
            wisdomEditText.toString().toIntOrNull(),
            dexterityEditText.toString().toIntOrNull(),
            charismaEditText.toString().toIntOrNull(),
            intelligenceEditText.toString().toIntOrNull()
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(characterId: Int) =
            CharacterInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CHARACTER_ID, characterId)
                }
            }
    }
}