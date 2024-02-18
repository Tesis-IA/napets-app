package com.quantumcode.napets.ui.history.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quantumcode.napets.R
import com.quantumcode.napets.data.model.history.History
import com.quantumcode.napets.databinding.ItemHistoryBinding
import com.quantumcode.napets.utils.getFormattedDate

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val historyList = mutableListOf<History>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
    )

    override fun getItemCount() = historyList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.render(history = historyList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHistoryList(newList: List<History>) {
        historyList.clear()
        historyList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemHistoryBinding.bind(view)

        fun render(history: History) {
            binding.apply {
                itemHistoryDate.text = history.createdAt.getFormattedDate("dd, MMM yyyy")
                itemHistoryName.text = history.diagnostic
                Glide.with(root.context)
                    .load(history.image.firstOrNull())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_user)
                    .into(itemHistoryImage)
            }
        }
    }
}
