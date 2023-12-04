package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
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

        AcrobaticsButton.setOnClickListener { handleButtonClick(dexterity, resultDisplay) }
        AnimalHandlingButton.setOnClickListener { handleButtonClick(wisdom, resultDisplay) }
        ArcanaButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay) }
        DeceptionButton.setOnClickListener { handleButtonClick(charisma, resultDisplay) }
        AthleticsButton.setOnClickListener { handleButtonClick(strength, resultDisplay) }
        HistoryButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay) }
        InsightButton.setOnClickListener { handleButtonClick(wisdom, resultDisplay) }
        IntimidationButton.setOnClickListener { handleButtonClick(charisma, resultDisplay) }
        InvestigationButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay) }
        MedicineButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay) }
        NatureButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay) }
        PerceptionButton.setOnClickListener { handleButtonClick(charisma, resultDisplay) }
        PersuasionButton.setOnClickListener { handleButtonClick(charisma, resultDisplay) }
        ReligionButton.setOnClickListener { handleButtonClick(intelligence, resultDisplay) }
        SleightOfHandButton.setOnClickListener { handleButtonClick(dexterity, resultDisplay) }
        StealthButton.setOnClickListener { handleButtonClick(dexterity, resultDisplay) }
        SurvivalButton.setOnClickListener { handleButtonClick(wisdom, resultDisplay) }

        return view
    }

    private fun handleButtonClick(modifier: Int, resultDisplay: TextView) {
        var total = Random.nextInt(1,20)
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