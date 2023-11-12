package edu.uark.mobileprogramming.findfamiliar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random
import kotlin.random.nextUInt


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

        val view = inflater.inflate(R.layout.fragment_dice_roller, container, false)

        // Find the button by its ID
        val modifier: EditText = view.findViewById(R.id.ModifierEdit)
        val NumDice: EditText = view.findViewById(R.id.NumDiceEdit)
        val ResultDisplay: TextView = view.findViewById(R.id.ResultView)
        val EquationDisplay: TextView = view.findViewById(R.id.EquationView)
        val D4Button: Button = view.findViewById(R.id.D4button)
        D4Button.setOnClickListener {
            if(NumDice.text.toString()!="") {
            var arrayNum = emptyArray<Int>()
            var total = 0
            if(modifier.text.toString()!="")
            {
                total = modifier.text.toString().toInt()
            }
            else{modifier.setText("0")}
            for(i in 1..NumDice.text.toString().toInt())
            {
                val temp = Random.nextInt(1, 4)
                arrayNum+=temp
                total+=temp
            }

                EquationDisplay.text = "Equation: " + arrayNum.joinToString() + " + " + modifier.text.toString()
                ResultDisplay.text = "Result: " + total.toString()
            }
        }
        val D6Button: Button = view.findViewById(R.id.D6button)
        D6Button.setOnClickListener {
            if(NumDice.text.toString()!="") {
            var arrayNum = emptyArray<Int>()
            var total = 0
            if(modifier.text.toString()!="")
            {
                total = modifier.text.toString().toInt()
            }
            else{modifier.setText("0")}
                for (i in 1..NumDice.text.toString().toInt()) {
                    val temp = Random.nextInt(1, 6)
                    arrayNum += temp
                    total += temp
                }
                EquationDisplay.text =
                    "Equation: " + arrayNum.joinToString() + " + " + modifier.text.toString()
                ResultDisplay.text = "Result: " + total.toString()
            }
        }
        val D8Button: Button = view.findViewById(R.id.D8button)
        D8Button.setOnClickListener {
            if(NumDice.text.toString()!="") {
            var arrayNum = emptyArray<Int>()
            var total = 0
            if(modifier.text.toString()!="")
            {
                total = modifier.text.toString().toInt()
            }
            else{modifier.setText("0")}
                for (i in 1..NumDice.text.toString().toInt()) {
                    val temp = Random.nextInt(1, 8)
                    arrayNum += temp
                    total += temp
                }
                EquationDisplay.text =
                    "Equation: " + arrayNum.joinToString() + " + " + modifier.text.toString()
                ResultDisplay.text = "Result: " + total.toString()
            }
        }
        val D10Button: Button = view.findViewById(R.id.D10button)
        D10Button.setOnClickListener {
            if(NumDice.text.toString()!="") {
            var arrayNum = emptyArray<Int>()
            var total = 0
            if(modifier.text.toString()!="")
            {
                total = modifier.text.toString().toInt()
            }
            else{modifier.setText("0")}
                for (i in 1..NumDice.text.toString().toInt()) {
                    val temp = Random.nextInt(1, 10)
                    arrayNum += temp
                    total += temp
                }
                EquationDisplay.text =
                    "Equation: " + arrayNum.joinToString() + " + " + modifier.text.toString()
                ResultDisplay.text = "Result: " + total.toString()
            }
        }
        val D12Button: Button = view.findViewById(R.id.D12button)
        D12Button.setOnClickListener {
            if(NumDice.text.toString()!="") {
            var arrayNum = emptyArray<Int>()
            var total = 0
            if(modifier.text.toString()!="")
            {
                total = modifier.text.toString().toInt()
            }
            else{modifier.setText("0")}
                for (i in 1..NumDice.text.toString().toInt()) {
                    val temp = Random.nextInt(1, 12)
                    arrayNum += temp
                    total += temp
                }
                EquationDisplay.text =
                    "Equation: " + arrayNum.joinToString() + " + " + modifier.text.toString()
                ResultDisplay.text = "Result: " + total.toString()
            }
        }
        val D20Button: Button = view.findViewById(R.id.D20button)
        D20Button.setOnClickListener {
            if(NumDice.text.toString()!="") {
            var arrayNum = emptyArray<Int>()
            var total = 0
            if(modifier.text.toString()!="")
            {
                total = modifier.text.toString().toInt()
            }
            else{modifier.setText("0")}
                for (i in 1..NumDice.text.toString().toInt()) {
                    val temp = Random.nextInt(1, 20)
                    arrayNum += temp
                    total += temp
                }
                EquationDisplay.text =
                    "Equation: " + arrayNum.joinToString() + " + " + modifier.text.toString()
                ResultDisplay.text = "Result: " + total.toString()
            }
        }

        return view
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
