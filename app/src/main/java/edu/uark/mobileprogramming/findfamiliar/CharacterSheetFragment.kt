package edu.uark.mobileprogramming.findfamiliar

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uark.mobileprogramming.findfamiliar.Model.Characters
import edu.uark.mobileprogramming.findfamiliar.Model.CharactersRepository
import edu.uark.mobileprogramming.findfamiliar.NewCharacter.NewCharacterActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterSheetFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterListAdapter
    private lateinit var addCharBtn: Button
    private lateinit var viewModel: CharactersViewModel
    private lateinit var repository: CharactersRepository
    private var numberOfCharacters: Int = 0

    val startNewCharacterActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            Log.d("CharacterSheetFragment","Completed")
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
        val view = inflater.inflate(R.layout.fragment_character_sheet, container, false)
        viewModel = ViewModelProvider(this,
            CharactersViewModel.CharactersViewModelFactory(repository)
        ).get(CharactersViewModel::class.java)
        addCharBtn = view.findViewById(R.id.addAbilityBtn)
        // sets on click for add character
        // when add character is called, it creates an empty character.
        addCharBtn.setOnClickListener {
            var characterId = -1
            CoroutineScope(SupervisorJob()).launch {
                 val id = withContext(Dispatchers.IO){
                    viewModel.insertBlankCharacter()
                }.getOrDefault(-1)
                characterId = id.toInt()

            }.invokeOnCompletion {
                numberOfCharacters = characterId
                val intent = Intent(requireContext(), NewCharacterActivity::class.java)
                intent.putExtra("character_id", characterId)
                startNewCharacterActivity.launch(intent)
            }
        }

        recyclerView = view.findViewById(R.id.recyclerviewCharacter)
        adapter = CharacterListAdapter {
            clickedCharacter ->
            clickedCharacter.name?.let { it -> Log.d("Character Sheet Fragment", it) }
            val intent = Intent(requireContext(), NewCharacterActivity::class.java)
            intent.putExtra("character_id", clickedCharacter.characterId)
            startNewCharacterActivity.launch(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allCharacters.observe(viewLifecycleOwner) { characters ->
            characters.let {
                // increments numberOfCharacters for each entry
                ++numberOfCharacters
                adapter.submitList(it)
            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterSheetFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}