package com.example.traveltaipei.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.traveltaipei.databinding.ItemAttractionBinding
import com.example.traveltaipei.viewmodel.AttractionDataUIViewmodel

class AttractionAdapter(private val onClick: (AttractionDataUIViewmodel) -> Unit) :
    ListAdapter<AttractionDataUIViewmodel, AttractionAdapter.AttractionViewHolder>(AttractionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val binding = ItemAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val attraction = getItem(position)
        holder.bind(attraction, onClick)
    }

    class AttractionViewHolder(private val binding: ItemAttractionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(attraction: AttractionDataUIViewmodel, onClick: (AttractionDataUIViewmodel) -> Unit) {
            binding.attraction = attraction
            Glide.with(binding.image.context)
                .load(attraction.image.firstOrNull()?.src)
                .into(binding.image)
            binding.root.setOnClickListener { onClick(attraction) }
            binding.executePendingBindings()
        }
    }

    class AttractionDiffCallback : DiffUtil.ItemCallback<AttractionDataUIViewmodel>() {
        override fun areItemsTheSame(oldItem: AttractionDataUIViewmodel, newItem: AttractionDataUIViewmodel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AttractionDataUIViewmodel, newItem: AttractionDataUIViewmodel): Boolean {
            return oldItem == newItem
        }
    }
}
