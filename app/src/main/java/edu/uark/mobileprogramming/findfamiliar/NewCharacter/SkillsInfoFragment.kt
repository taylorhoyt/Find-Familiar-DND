package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
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
 * Use the [SkillsInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillsInfoFragment : Fragment() {
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
        val view =  inflater.inflate(R.layout.fragment_skills_info, container, false)
        val strength: Int = 1
        val dexterity: Int = 2
        val wisdom:Int = 3
        val intelligence:Int = 4
        val charisma: Int = -1
        val proficiencyBonus: Int = 3
        val resultDisplay: TextView = view.findViewById(R.id.resultView)
        val AcrobaticsButton: Button = view.findViewById(R.id.rollButtonAcrobatics)
        val AnimalHandlingButton: Button = view.findViewById(R.id.rollButtonAnimalHandling)
        val ArcanaButton: Button = view.findViewById(R.id.rollButtonArcana)
        val AthleticsButton: Button = view.findViewById(R.id.rollButtonAthletics)
        val DeceptionButton: Button = view.findViewById(R.id.rollButtonDeception)
        val HistoryButton: Button = view.findViewById(R.id.rollButtonHistory)
        val InsightButton: Button = view.findViewById(R.id.rollButtonInsight)
        val IntimidationButton: Button = view.findViewById(R.id.rollButtonIntimidation)
        val InvestigationButton: Button = view.findViewById(R.id.rollButtonInvestigation)
        val MedicineButton: Button = view.findViewById(R.id.rollButtonMedicine)
        val NatureButton: Button = view.findViewById(R.id.rollButtonNature)
        val PerceptionButton: Button = view.findViewById(R.id.rollButtonPerception)
        val PersuasionButton: Button = view.findViewById(R.id.rollButtonPersuasion)
        val ReligionButton: Button = view.findViewById(R.id.rollButtonReligion)
        val SleightOfHandButton: Button = view.findViewById(R.id.rollButtonSleightOfHand)
        val StealthButton: Button = view.findViewById(R.id.rollButtonStealth)
        val SurvivalButton: Button = view.findViewById(R.id.rollButtonSurvival)

        val AcrobaticsProficiency: Switch = view.findViewById(R.id.proficiencyAcrobatics)
        val AnimalHandlingProficiency: Switch = view.findViewById(R.id.proficiencyAnimalHandling)
        val ArcanaProficiency: Switch = view.findViewById(R.id.proficiencyArcana)
        val AthleticsProficiency: Switch = view.findViewById(R.id.proficiencyAthletics)
        val DeceptionProficiency: Switch = view.findViewById(R.id.proficiencyDeception)
        val HistoryProficiency: Switch = view.findViewById(R.id.proficiencyHistory)
        val InsightProficiency: Switch = view.findViewById(R.id.proficiencyInsight)
        val IntimidationProficiency: Switch = view.findViewById(R.id.proficiencyIntimidation)
        val InvestigationProficiency: Switch = view.findViewById(R.id.proficiencyInvestigation)
        val MedicineProficiency: Switch = view.findViewById(R.id.proficiencyMedicine)
        val NatureProficiency: Switch = view.findViewById(R.id.proficiencyNature)
        val PerceptionProficiency: Switch = view.findViewById(R.id.proficiencyPerception)
        val PersuasionProficiency: Switch = view.findViewById(R.id.proficiencyPersuasion)
        val ReligionProficiency: Switch = view.findViewById(R.id.proficiencyReligion)
        val SleightOfHandProficiency: Switch = view.findViewById(R.id.proficiencySleightOfHand)
        val StealthProficiency: Switch = view.findViewById(R.id.proficiencyStealth)
        val SurvivalProficiency: Switch = view.findViewById(R.id.proficiencySurvival)

        AcrobaticsButton.setOnClickListener { handleButtonClick(dexterity, resultDisplay, AcrobaticsProficiency, proficiencyBonus) }
        AnimalHandlingButton.setOnClickListener { handleButtonClick(wisdom, resultDisplay, AnimalHandlingProficiency, proficiencyBonus) }
        ArcanaButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay, ArcanaProficiency, proficiencyBonus) }
        DeceptionButton.setOnClickListener { handleButtonClick(charisma, resultDisplay, DeceptionProficiency, proficiencyBonus) }
        AthleticsButton.setOnClickListener { handleButtonClick(strength, resultDisplay, AthleticsProficiency, proficiencyBonus) }
        HistoryButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay, HistoryProficiency, proficiencyBonus) }
        InsightButton.setOnClickListener { handleButtonClick(wisdom, resultDisplay, InsightProficiency, proficiencyBonus) }
        IntimidationButton.setOnClickListener { handleButtonClick(charisma, resultDisplay, IntimidationProficiency, proficiencyBonus) }
        InvestigationButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay, InvestigationProficiency, proficiencyBonus) }
        MedicineButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay, MedicineProficiency, proficiencyBonus) }
        NatureButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay, NatureProficiency, proficiencyBonus) }
        PerceptionButton.setOnClickListener { handleButtonClick(charisma, resultDisplay, PerceptionProficiency, proficiencyBonus) }
        PersuasionButton.setOnClickListener { handleButtonClick(charisma, resultDisplay, PersuasionProficiency, proficiencyBonus) }
        ReligionButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay, ReligionProficiency, proficiencyBonus) }
        SleightOfHandButton.setOnClickListener { handleButtonClick(dexterity, resultDisplay, SleightOfHandProficiency, proficiencyBonus) }
        StealthButton.setOnClickListener { handleButtonClick(dexterity, resultDisplay, StealthProficiency, proficiencyBonus) }
        SurvivalButton.setOnClickListener { handleButtonClick(wisdom, resultDisplay, SurvivalProficiency, proficiencyBonus) }

        return view
    }

    private fun handleButtonClick(modifier: Int, resultDisplay: TextView, proficiency: Switch, proficiencyBonus: Int) {
        var total = Random.nextInt(1,21)
        if(proficiency.isChecked)
            total+=proficiencyBonus
        total+=modifier
        resultDisplay.text = total.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SkillsInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SkillsInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}