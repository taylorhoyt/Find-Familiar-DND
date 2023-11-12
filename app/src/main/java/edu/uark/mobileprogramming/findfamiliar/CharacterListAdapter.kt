package edu.uark.mobileprogramming.findfamiliar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uark.mobileprogramming.findfamiliar.Model.Characters

class CharacterListAdapter(val characterClicked:(characters:Characters)->Unit): ListAdapter<Characters, CharacterListAdapter.CharacterViewHolder>(CharacterComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    //Displays data and content
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.characterName, current.characterClass, current.characterLevel)
        holder.itemView.tag= current
        holder.itemView.setOnClickListener{
            characterClicked(holder.itemView.tag as Characters)
        }
    }

    //Makes updates to the recycler view
    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val charNameView: TextView = itemView.findViewById(R.id.charName)
        private val charClassView: TextView = itemView.findViewById(R.id.charClass)
        private val charLevelView: TextView = itemView.findViewById(R.id.charLevel)

        fun bind(cName: String?, cClass:String?, cLevel:Int?) {
            charNameView.text = cName
            charClassView.text = cClass
            charLevelView.text = cLevel.toString()
        }
        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_character_sheet, parent, false)
                return CharacterViewHolder(view)
            }
        }
    }

    class CharacterComparator : DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.characterName == newItem.characterName
        }
    }
}