package edu.uark.mobileprogramming.findfamiliar

import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiceRollerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiceRollerFragment : Fragment() {
    private var selectedButton: ImageButton? = null
    private var rollNum: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dice_roller, container, false)
        val darkBlue = context?.let { ContextCompat.getColor(it, R.color.dark_blue) }
        val blue = context?.let { ContextCompat.getColor(it, R.color.blue) }
        val modifier: EditText = view.findViewById(R.id.ModifierEdit)
        val numDice: EditText = view.findViewById(R.id.NumDiceEdit)
        val resultDisplay: TextView = view.findViewById(R.id.ResultView)
        val equationDisplay: TextView = view.findViewById(R.id.EquationView)

        val D4Button: ImageButton = view.findViewById(R.id.D4button)
        val D6Button: ImageButton = view.findViewById(R.id.D6button)
        val D8Button: ImageButton = view.findViewById(R.id.D8button)
        val D10Button: ImageButton = view.findViewById(R.id.D10button)
        val D12Button: ImageButton = view.findViewById(R.id.D12button)
        val D20Button: ImageButton = view.findViewById(R.id.D20button)

        if (darkBlue != null && blue != null) {
            D4Button.setOnClickListener { handleButtonClick(D4Button, darkBlue, blue, 4) }
            D6Button.setOnClickListener { handleButtonClick(D6Button, darkBlue, blue, 6) }
            D8Button.setOnClickListener { handleButtonClick(D8Button, darkBlue, blue, 8) }
            D10Button.setOnClickListener { handleButtonClick(D10Button, darkBlue, blue, 10) }
            D12Button.setOnClickListener { handleButtonClick(D12Button, darkBlue, blue, 12) }
            D20Button.setOnClickListener { handleButtonClick(D20Button, darkBlue, blue, 20) }
        }

        val rollButton: Button = view.findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollNum?.let { it1 -> onRoll(it1, numDice, modifier, equationDisplay, resultDisplay) }
        }

        return view
    }

    private fun handleButtonClick(button: ImageButton, darkBlue: Int, blue: Int, newRollNum: Int) {
        selectedButton?.setColorFilter(blue)

        button.setColorFilter(darkBlue)
        selectedButton = button

        // Set the roll number based on the clicked button
        rollNum = newRollNum
    }

    private fun onRoll(rollNum: Int, numDice: TextView, modifier: TextView, equationDisplay: TextView, resultDisplay: TextView) {
        val numDiceValue = if (numDice.text.toString().isNotEmpty()) {
            numDice.text.toString().toInt()
        } else {
            numDice.setText("1")
            1
        }
            var arrayNum = emptyArray<Int>()
            var total = 0
            if (modifier.text.toString() != "") {
                total = modifier.text.toString().toInt()
            } else {
                modifier.setText("0")
            }
            for (i in 1..numDiceValue) {
                val temp = Random.nextInt(1, rollNum)
                arrayNum += temp
                total += temp
            }
            equationDisplay.text =
                "Equation: " + arrayNum.joinToString() + " + " + modifier.text.toString()
            resultDisplay.text = total.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DiceRollerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiceRollerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        }
}
