package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import edu.uark.mobileprogramming.findfamiliar.R
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SavingThrowInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavingThrowInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_saving_throw_info, container, false)
        val strength: Int = 1
        val dexterity: Int = 2
        val wisdom:Int = 3
        val intelligence:Int = 4
        val charisma: Int = -1
        val constitution: Int = 7
        val proficiencyBonus:Int = 3
        val resultDisplay: TextView = view.findViewById(R.id.resultView)
        val StrengthButton: Button = view.findViewById(R.id.rollButtonStrength)
        val DexterityButton: Button = view.findViewById(R.id.rollButtonDexterity)
        val ConstitutionButton: Button = view.findViewById(R.id.rollButtonConstitution)
        val IntelligenceButton: Button = view.findViewById(R.id.rollButtonIntelligence)
        val WisdomButton: Button = view.findViewById(R.id.rollButtonWisdom)
        val CharismaButton: Button = view.findViewById(R.id.rollButtonCharisma)

        val StrengthProficiency: Switch = view.findViewById(R.id.proficiencyStrength)
        val DexterityProficiency: Switch = view.findViewById(R.id.proficiencyDexterity)
        val ConstitutionProficiency: Switch = view.findViewById(R.id.proficiencyConstitution)
        val IntelligenceProficiency: Switch = view.findViewById(R.id.proficiencyIntelligence)
        val WisdomProficiency: Switch = view.findViewById(R.id.proficiencyWisdom)
        val CharismaProficiency: Switch = view.findViewById(R.id.proficiencyCharisma)

        StrengthButton.setOnClickListener { handleButtonClick(strength, resultDisplay, StrengthProficiency, proficiencyBonus) }
        DexterityButton.setOnClickListener { handleButtonClick(dexterity, resultDisplay, DexterityProficiency, proficiencyBonus) }
        ConstitutionButton.setOnClickListener { handleButtonClick(constitution, resultDisplay, ConstitutionProficiency, proficiencyBonus) }
        IntelligenceButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay, IntelligenceProficiency, proficiencyBonus) }
        WisdomButton.setOnClickListener { handleButtonClick(wisdom, resultDisplay, WisdomProficiency, proficiencyBonus) }
        CharismaButton.setOnClickListener { handleButtonClick(charisma, resultDisplay, CharismaProficiency, proficiencyBonus) }

        return view
    }

    private fun handleButtonClick(modifier: Int, resultDisplay: TextView, proficiency: Switch, proficiencyBonus: Int) {
        var total = Random.nextInt(1,20)
        if(proficiency.isChecked)
            total+=proficiencyBonus
        total+=modifier
        resultDisplay.text = "Result: "+total
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SavingThrowInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavingThrowInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}