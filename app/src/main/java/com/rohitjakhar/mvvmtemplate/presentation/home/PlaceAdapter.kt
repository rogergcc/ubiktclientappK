package com.rohitjakhar.mvvmtemplate.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rohitjakhar.mvvmtemplate.base.BaseViewHolder
import com.rohitjakhar.mvvmtemplate.databinding.PlaceItemBinding
import com.rohitjakhar.mvvmtemplate.domain.model.CharacterDetails


class PlaceAdapter(
    val placeNearbyDetailsAction: (placeItem: CharacterDetails) -> Unit,
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var mItemsPlace = listOf<CharacterDetails>()
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
    ) : BaseViewHolder<CharacterDetails>(binding.root) {
        override fun bind(item: CharacterDetails) {

            binding.apply {
                tvTitleNamePlace.text = item.characterName
                tvAvAdressPlace.text = item.id.toString()

                Glide.with(context).load(item.profileImageUrl)
                    .centerCrop().into(imvPhotoPlace)
            }
        }
    }
}