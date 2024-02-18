package com.quantumcode.napets.ui.diagnosis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quantumcode.napets.R
import com.quantumcode.napets.databinding.ItemDiagnosisBinding

class DiagnosisInfoAdapter(
    private val diagnosisList: List<String>
): RecyclerView.Adapter<DiagnosisInfoAdapter.DiagnosisInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiagnosisInfoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_diagnosis, parent, false)
    )

    override fun getItemCount() = diagnosisList.size

    override fun onBindViewHolder(holder: DiagnosisInfoViewHolder, position: Int) {
        holder.render(diagnosisList[position])
    }

    inner class DiagnosisInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemDiagnosisBinding.bind(view)

        fun render(info: String) {
            binding.itemDiagnosisText.text = info
        }
    }
}
