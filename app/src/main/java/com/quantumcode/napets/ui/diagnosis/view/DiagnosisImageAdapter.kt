package com.quantumcode.napets.ui.diagnosis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quantumcode.napets.R
import com.quantumcode.napets.databinding.ItemDiagnosisImageBinding

class DiagnosisImageAdapter(
    private val diagnosisImagesList: List<String>
) : RecyclerView.Adapter<DiagnosisImageAdapter.DiagnosisImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiagnosisImageViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_diagnosis_image, parent, false)
    )

    override fun getItemCount() = diagnosisImagesList.size

    override fun onBindViewHolder(holder: DiagnosisImageViewHolder, position: Int) {
        holder.render(diagnosisImagesList[position])
    }

    inner class DiagnosisImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemDiagnosisImageBinding.bind(view)

        fun render(image: String) {
            Glide.with(binding.root.context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.itemDiagnosisImage)
        }
    }
}
