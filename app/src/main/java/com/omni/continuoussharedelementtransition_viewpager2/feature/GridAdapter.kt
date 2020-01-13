package com.omni.continuoussharedelementtransition_viewpager2.feature

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.omni.continuoussharedelementtransition_viewpager2.R
import kotlinx.android.synthetic.main.grid_image_list_item.view.*

class GridAdapter : ListAdapter<ImageData, GridAdapter.GridViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImageData>() {
            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean =
                oldItem == newItem
        }
    }

    init {
        submitList(DataGenerator.list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GridViewHolder.from(
            parent
        )

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        getItem(position)?.let {
            Log.d("onBindViewHolder", it.toString())
            holder.bind(it)
        }
    }

    class GridViewHolder private constructor(private val rootView: View) :
        RecyclerView.ViewHolder(rootView) {

        init {
            rootView.setOnClickListener {
                TODO("do action")
            }
        }

        fun bind(item: ImageData) {
            rootView.grid_image_view
                .let {
                    Glide.with(it.context)
                        .load(item.imageResource)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                        )
                        .into(it)
                }
        }


        companion object {
            fun from(parent: ViewGroup): GridViewHolder =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.grid_image_list_item, parent, false)
                    .let {
                        GridViewHolder(
                            it
                        )
                    }
        }
    }
}
