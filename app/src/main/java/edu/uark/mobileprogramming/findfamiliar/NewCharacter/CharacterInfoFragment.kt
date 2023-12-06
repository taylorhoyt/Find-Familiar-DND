package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterStats
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import edu.uark.mobileprogramming.findfamiliar.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterInfoFragment(private var isNewCharacter: Boolean, private var currentCharacterId: Int) : Fragment() {

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
    private lateinit var healthTextView: TextView

    private lateinit var currentCharacter: Characters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as NewCharacterActivity)
        arguments?.let {
        }
    }

    override fun onStop(){
        super.onStop()
        (activity as NewCharacterActivity).updateCharacterInfo(getCharacterInfoFromView())
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
        healthTextView = view.findViewById(R.id.hpEdit)
        addBtn = view.findViewById(R.id.addHealthButton)
        subBtn = view.findViewById(R.id.subtractHealthButton)
        addBtn.setOnClickListener {
            val currentValue = healthTextView.text.toString().toIntOrNull() ?: 0
            val newValue = currentValue + 1
            healthTextView.text = newValue.toString()
        }
        subBtn.setOnClickListener {
            val currentValue = healthTextView.text.toString().toIntOrNull() ?: 0
            val newValue = currentValue - 1
            if (currentValue > 0) {
                healthTextView.text = newValue.toString()
            }
        }
        if(!isNewCharacter) {
            (activity as NewCharacterActivity).currentLiveCharacter.observe(viewLifecycleOwner) { character ->
                currentCharacter = character
                updateView()
            }
        }
        return view
    }

    //currentCharacter = (activity as NewCharacterActivity).getCharacterInfo()

    private fun getCharacterInfoFromView(): Characters{
        return Characters(
            currentCharacter.characterId,
            nameEditText.text.toString(),
            healthTextView.text.toString().toIntOrNull(),
            armorClassEditText.toString().toIntOrNull(),
            classEditText.toString(),
            levelEditText.toString().toIntOrNull(),
            currentCharacter.proficiencyBonus,
            currentCharacter.proficiencies,
            currentCharacter.statsId
        )
    }

    private fun updateView(){
        currentCharacter.name?.let{nameEditText.setText(it)}
        currentCharacter.className?.let{ classEditText.setText(it)}
        currentCharacter.level?.let { levelEditText.setText(it) }
        currentCharacter.armor?.let { armorClassEditText.setText(it) }
        currentCharacter.health?.let { healthTextView.setText(it) }
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
        fun newInstance(param1: String, param2: String) =
            CharacterInfoFragment(true, -1).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}