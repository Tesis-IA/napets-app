package com.quantumcode.napets.ui.cropsTips.view.adapter

import android.annotation.SuppressLint
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quantumcode.napets.R
import com.quantumcode.napets.data.model.cropsTips.CropsTips
import com.quantumcode.napets.databinding.ItemCropsTipsBinding
import com.quantumcode.napets.ui.utils.parseColor

class CropsTipsAdapter(
    private val cropsTipsAdapterListener: CropsTipsAdapterListener
) : RecyclerView.Adapter<CropsTipsAdapter.CropsTipsViewHolder>() {

    private val cropsTipsList = mutableListOf<CropsTips>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CropsTipsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_crops_tips, parent, false)
    )

    override fun getItemCount() = cropsTipsList.size

    override fun onBindViewHolder(holder: CropsTipsViewHolder, position: Int) {
        holder.render(cropsTipsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCropsTipsList(newList: List<CropsTips>) {
        cropsTipsList.clear()
        cropsTipsList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class CropsTipsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCropsTipsBinding.bind(view)

        fun render(cropsTips: CropsTips) {
            binding.apply {
                itemCropsTipsCard.setCardBackgroundColor(cropsTips.background.parseColor())
                itemCropsTipsName.text = cropsTips.name
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    itemCropsTipsBgShape.background.colorFilter = BlendModeColorFilter(cropsTips.shapeBackground.parseColor(), BlendMode.SRC_ATOP)
                } else {
                    @Suppress("DEPRECATION") itemCropsTipsBgShape.background.setColorFilter(cropsTips.shapeBackground.parseColor(), PorterDuff.Mode.SRC_ATOP)
                }
                Glide.with(root.context)
                    .load(cropsTips.image)
                    .placeholder(R.drawable.ic_my_crops)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemCropsTipsImage)
            }
        }
    }
}