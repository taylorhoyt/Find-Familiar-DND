package edu.uark.mobileprogramming.findfamiliar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterWeapons
import edu.uark.mobileprogramming.findfamiliar.Model.Characters

class WeaponListAdapter(val weaponClicked:(weapons: CharacterWeapons)->Unit): ListAdapter<CharacterWeapons, WeaponListAdapter.WeaponViewHolder>(WeaponComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        return WeaponViewHolder.create(parent)
    }

    //Displays data and content
    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val current = getItem(position)
        //Add info here if wanted
        holder.bind(current.weaponName)
        holder.itemView.tag = current
        holder.itemView.setOnClickListener{
            weaponClicked(holder.itemView.tag as CharacterWeapons)
        }
    }

    //Makes updates to the recycler view
    class WeaponViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val weaponNameView: TextView = itemView.findViewById(R.id.abilityName)

        fun bind(wName: String?) {
            weaponNameView.text = wName
        }
        companion object {
            fun create(parent: ViewGroup): WeaponViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weapon_list, parent, false)
                return WeaponViewHolder(view)
            }
        }
    }

    class WeaponComparator : DiffUtil.ItemCallback<CharacterWeapons>() {
        override fun areItemsTheSame(oldItem: CharacterWeapons, newItem: CharacterWeapons): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CharacterWeapons, newItem: CharacterWeapons): Boolean {
            return oldItem.weaponName == newItem.weaponName
        }
    }
}