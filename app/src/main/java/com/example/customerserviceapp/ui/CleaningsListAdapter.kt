package com.example.customerserviceapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.customerserviceapp.R
import com.example.customerserviceapp.model.CleaningS

class CleaningsListAdapter(
    private val onItemClickListener: (CleaningS) -> Unit
): ListAdapter<CleaningS, CleaningsListAdapter.CleaningViewHolder> (WORD_COMPERATION) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleaningViewHolder {
        return CleaningViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CleaningViewHolder, position: Int) {
        val cleaningS = getItem(position)
        holder.bind(cleaningS)
        holder.itemView.setOnClickListener {
            onItemClickListener(cleaningS)
        }
    }

    class CleaningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nametextView: TextView = itemView.findViewById(R.id.nametextView)
        private val addresstextView: TextView = itemView.findViewById(R.id.addresstextView)
        private val typetextView: TextView = itemView.findViewById(R.id.typetextView)

        fun bind(cleaningS: CleaningS?) {
            nametextView.text = cleaningS?.name
            addresstextView.text = cleaningS?.address
            typetextView.text = cleaningS?.type

        }

        companion object {
            fun create(parent: ViewGroup): CleaningViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_cleanings, parent, false)
                return CleaningViewHolder(view)
            }
        }
    }
    companion object{
        private val WORD_COMPERATION = object : DiffUtil.ItemCallback<CleaningS>(){
            override fun areContentsTheSame(oldItem: CleaningS, newItem: CleaningS): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: CleaningS, newItem: CleaningS): Boolean {
                return oldItem.id == newItem.id
            }
        }
}
}