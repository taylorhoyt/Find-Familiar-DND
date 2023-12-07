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
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uark.mobileprogramming.findfamiliar.AbilityListAdapter
import edu.uark.mobileprogramming.findfamiliar.FindFamiliarApplication
import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
import edu.uark.mobileprogramming.findfamiliar.R

private const val ARG_CHARACTER_ID = "character_id"

class AbilitySheetFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AbilityListAdapter
    private lateinit var addBtn: Button
    private lateinit var viewModel: NewCharacterViewModel
    private lateinit var repository: CharactersRepository
    private var currentCharacterId: Int = -1

    private val newCharacterViewModel: NewCharacterViewModel by lazy {
        (requireActivity() as NewCharacterActivity).newCharacterViewModel
    }

    val startAbilityActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            Log.d("AbilitySheetFragment","Completed")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val view = inflater.inflate(R.layout.fragment_ability_sheet, container, false)
        viewModel = ViewModelProvider(this, NewCharacterViewModelFactory(repository, -1)).get(
            NewCharacterViewModel::class.java)

        //currentCharacterId = arguments?.getInt(ARG_CHARACTER_ID, -1)!!
        currentCharacterId = (activity as NewCharacterActivity).characterId
        Log.d("ABILITY FRAG", currentCharacterId.toString())

        addBtn = view.findViewById(R.id.addAbilBtn)
        addBtn.setOnClickListener {
            val intent = Intent(requireContext(), AbilityActivity::class.java)
            intent.putExtra("character_id", currentCharacterId)
            startAbilityActivity.launch(intent)
        }

        recyclerView = view.findViewById(R.id.recyclerviewAbility)
        adapter = AbilityListAdapter {
            it.featName?.let { it -> Log.d("Ability Sheet Fragment", it) }
            val intent = Intent(requireContext(), AbilityActivity::class.java)
            intent.putExtra("ability_id", it.featId)
            intent.putExtra("character_id", currentCharacterId)
            startAbilityActivity.launch(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allCharacterFeats.observe(viewLifecycleOwner) { characterAbilites ->
            characterAbilites.let {
                adapter.submitList(it)
            }
        }
        return view
    }



    companion object {
        @JvmStatic
        fun newInstance(characterId: Int) =
            AbilitySheetFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CHARACTER_ID, characterId)
                }
            }
    }
}