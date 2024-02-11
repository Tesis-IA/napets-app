package com.quantumcode.napets.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quantumcode.napets.BuildConfig
import com.quantumcode.napets.R
import com.quantumcode.napets.data.model.home.Subject
import com.quantumcode.napets.databinding.ItemLayoutSubjectBinding

class HomeSubjectsAdapter : RecyclerView.Adapter<HomeSubjectsAdapter.HomeSubjectViewHolder>(){

    private val subjectList: MutableList<Subject> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeSubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_subject, parent, false)
        )

    override fun getItemCount() = subjectList.size

    override fun onBindViewHolder(holder: HomeSubjectViewHolder, position: Int) {
        holder.render(subjectList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newSubjectList: List<Subject>) {
        subjectList.clear()
        subjectList.addAll(newSubjectList)
        notifyDataSetChanged()
    }

    inner class HomeSubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemLayoutSubjectBinding.bind(view)

        fun render(subject: Subject) {
            binding.apply {
                itemSubjectTitle.text = subject.title
                itemSubjectImage.setImageResource(subject.image)
                itemSubjectImage.setColorFilter(ContextCompat.getColor(root.context, R.color.primary_color))
            }
        }
    }
}
