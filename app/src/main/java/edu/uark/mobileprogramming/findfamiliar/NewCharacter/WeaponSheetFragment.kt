package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uark.mobileprogramming.findfamiliar.FindFamiliarApplication
import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
import edu.uark.mobileprogramming.findfamiliar.R
import edu.uark.mobileprogramming.findfamiliar.WeaponListAdapter

private const val ARG_CHARACTER_ID = "character_id"

class WeaponSheetFragment : Fragment(), WeaponListAdapter.OnButtonClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeaponListAdapter
    private lateinit var addBtn: Button
    private lateinit var viewModel: NewCharacterViewModel
    private lateinit var repository: CharactersRepository
    private lateinit var resultView: TextView
    private var currentCharacterId: Int = -1

    private val newCharacterViewModel: NewCharacterViewModel by lazy {
        (requireActivity() as NewCharacterActivity).newCharacterViewModel
    }

    val startWeaponActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            Log.d("WeaponSheetFragment","Completed")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val application = requireActivity().application
        if (application is FindFamiliarApplication) {
            val findFamiliarApplication = application as FindFamiliarApplication
            repository = findFamiliarApplication.repository
        } else {
            throw IllegalStateException("Application is not of type FindFamiliarApplication")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weapon_sheet, container, false)
        viewModel = ViewModelProvider(this, NewCharacterViewModelFactory(repository, -1)).get(
            NewCharacterViewModel::class.java)

        currentCharacterId = (activity as NewCharacterActivity).characterId
        Log.d("WEAPON FRAG", currentCharacterId.toString())

        addBtn = view.findViewById(R.id.addAbilityBtn)
        resultView = view.findViewById(R.id.resultView)
        addBtn.setOnClickListener {
            val intent = Intent(requireContext(), WeaponActivity::class.java)
            intent.putExtra("character_id", currentCharacterId)
            startWeaponActivity.launch(intent)
        }

        recyclerView = view.findViewById(R.id.recyclerviewWeapon)
        adapter = WeaponListAdapter {
            it.weaponName?.let { it -> Log.d("Weapon Sheet Fragment", it) }
            val intent = Intent(requireContext(), WeaponActivity::class.java)
            intent.putExtra("weapon_id", it.weaponId)
            intent.putExtra("character_id", currentCharacterId)
            startWeaponActivity.launch(intent)
        }
        adapter.setOnButtonClickListener(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allCharacterWeapons.observe(viewLifecycleOwner) { characterWeapons ->
            characterWeapons.let {
                adapter.submitList(it)
            }
        }
        return view
    }

    override fun onButtonClick(item: Int) {
        // Handle the button click, update the TextView, etc.
        resultView.text = item.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(characterId: Int) =
            WeaponSheetFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CHARACTER_ID, characterId)
                }
            }
    }
}