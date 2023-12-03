package edu.uark.mobileprogramming.findfamiliar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterFeatsExtras
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterWeapons
import edu.uark.mobileprogramming.findfamiliar.Model.Characters

class AbilityListAdapter(val abilityClicked:(abilities: CharacterFeatsExtras)->Unit): ListAdapter<CharacterFeatsExtras, AbilityListAdapter.AbilityViewHolder>(AbilityComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return AbilityViewHolder.create(parent)
    }

    //Displays data and content
    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val current = getItem(position)
        //Add info here if wanted
        holder.bind(current.featName, current.featDescription)
        holder.itemView.tag = current
        holder.itemView.setOnClickListener{
            abilityClicked(holder.itemView.tag as CharacterFeatsExtras)
        }
    }

    //Makes updates to the recycler view
    class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val abilityNameView: TextView = itemView.findViewById(R.id.abilityName)
        private val abilityDescView: TextView = itemView.findViewById(R.id.abilityDescription)

        fun bind(aName: String?, aDesc: String?) {
            abilityNameView.text = aName
            abilityDescView.text = aDesc
        }
        companion object {
            fun create(parent: ViewGroup): AbilityViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ability_list, parent, false)
                return AbilityViewHolder(view)
            }
        }
    }

    class AbilityComparator : DiffUtil.ItemCallback<CharacterFeatsExtras>() {
        override fun areItemsTheSame(oldItem: CharacterFeatsExtras, newItem: CharacterFeatsExtras): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CharacterFeatsExtras, newItem: CharacterFeatsExtras): Boolean {
            return oldItem.featName == newItem.featName
        }
    }
}