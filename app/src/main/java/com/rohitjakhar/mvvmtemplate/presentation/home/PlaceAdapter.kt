package com.rohitjakhar.mvvmtemplate.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rohitjakhar.mvvmtemplate.base.BaseViewHolder
import com.rohitjakhar.mvvmtemplate.data.remote.dto.GetDataDto
import com.rohitjakhar.mvvmtemplate.databinding.PlaceItemBinding


class PlaceAdapter(
    val placeNearbyDetailsAction: (placeItem: GetDataDto) -> Unit,
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var mItemsPlace = listOf<GetDataDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            PlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = UpcomingPlacesViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            placeNearbyDetailsAction(mItemsPlace[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is UpcomingPlacesViewHolder -> holder.bind(mItemsPlace[position])
        }
    }

    override fun getItemCount(): Int = mItemsPlace.size

    private inner class UpcomingPlacesViewHolder(
        val binding: PlaceItemBinding,
        val context: Context,
    ) : BaseViewHolder<GetDataDto>(binding.root) {
        override fun bind(item: GetDataDto) {

            binding.apply {
//                tvTitleNamePlace.text = item.name
//                tvAvAdressPlace.text = item.vicinity

                Glide.with(context).load(item.photo)
                    .centerCrop().into(imvPhotoPlace)
            }
        }
    }
}