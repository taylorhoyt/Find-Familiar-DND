package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
class CharacterInfoFragment : Fragment() {
    private lateinit var addBtn: Button
    private lateinit var subBtn: Button
    private lateinit var healthTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_character_info, container, false)
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
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}