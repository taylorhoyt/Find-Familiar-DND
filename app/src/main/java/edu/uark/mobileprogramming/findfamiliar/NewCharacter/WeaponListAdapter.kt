package edu.uark.mobileprogramming.findfamiliar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uark.mobileprogramming.findfamiliar.Model.CharacterWeapons
import kotlin.random.Random

class WeaponListAdapter(val weaponClicked:(weapons: CharacterWeapons)->Unit): ListAdapter<CharacterWeapons, WeaponListAdapter.WeaponViewHolder>(WeaponComparator()) {
    private var buttonClickListener: OnButtonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        return WeaponViewHolder.create(parent)
    }

    //Displays data and content
    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val current = getItem(position)
        var sides: Int = 20
        var modifier: Int = 3
        var total: Int = 0
        //Add info here if wanted
        holder.bind(current.weaponName)

        holder.itemView.tag = current
        holder.itemView.setOnClickListener{
            weaponClicked(holder.itemView.tag as CharacterWeapons)
        }

        holder.attackButton.setOnClickListener {
            sides = 20
            total = Random.nextInt(1, sides+1)
            total += modifier
            buttonClickListener?.onButtonClick(total)
        }
        holder.damageButton.setOnClickListener{
            sides = 8
            total = Random.nextInt(1, sides+1)
            total += modifier
            buttonClickListener?.onButtonClick(total)
        }
    }

    //Makes updates to the recycler view
    class WeaponViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val weaponNameView: TextView = itemView.findViewById(R.id.abilityName)
        val attackButton: Button = itemView.findViewById(R.id.rollButtonAcrobatics2)
        val damageButton: Button = itemView.findViewById(R.id.rollButtonAcrobatics)
        val parent: ViewParent? = itemView.parent
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
    interface OnButtonClickListener {
        fun onButtonClick(data: Int)
    }
    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        this.buttonClickListener = listener
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