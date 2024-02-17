package com.quantumcode.napets.ui.pestDisease.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quantumcode.napets.R
import com.quantumcode.napets.data.model.pestDisease.PestDisease
import com.quantumcode.napets.databinding.ItemPestDiseaseBinding

class PestDiseaseAdapter(
    private val pestDiseaseAdapterListener: PestDiseaseAdapterListener
) : RecyclerView.Adapter<PestDiseaseAdapter.PestDiseaseViewHolder>() {

    private val pestDiseaseList = mutableListOf<PestDisease>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PestDiseaseViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_pest_disease, parent, false)
    )

    override fun getItemCount() = pestDiseaseList.size
    override fun onBindViewHolder(holder: PestDiseaseViewHolder, position: Int) {
        holder.render(pestDiseaseList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPestDiseaseList(newList: List<PestDisease>) {
        pestDiseaseList.clear()
        pestDiseaseList.addAll(newList)
        notifyDataSetChanged()
    }
    inner class PestDiseaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPestDiseaseBinding.bind(view)

        fun render(pestDisease: PestDisease) {
            binding.apply {
                binding.itemPestDiseaseName.text = pestDisease.name
                binding.itemPestDiseaseCategory.text = pestDisease.category
                Glide.with(root.context)
                    .load(pestDisease.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_user)
                    .into(itemHistoryImage)

                itemPestDiseaseCard.setOnClickListener {
                    pestDiseaseAdapterListener.onItemClick(pestDisease)
                }
            }
        }
    }
}
