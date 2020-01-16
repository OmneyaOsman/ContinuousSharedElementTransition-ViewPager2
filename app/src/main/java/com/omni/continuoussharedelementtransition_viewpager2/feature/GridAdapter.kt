package com.omni.continuoussharedelementtransition_viewpager2.feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.omni.continuoussharedelementtransition_viewpager2.R
import com.omni.continuoussharedelementtransition_viewpager2.feature.grid.GridFragmentDirections
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GridViewHolder.from(
            parent
        )

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class GridViewHolder private constructor(private val rootView: View) :
        RecyclerView.ViewHolder(rootView) {

        fun bind(item: ImageData) {
            with(rootView.findViewById<ImageView>(R.id.grid_image_view))
            {
                transitionName = "${resources.getString(R.string.transition_shared_img)}_${item.id}"
                Glide.with(context)
                    .load(item.imageResource)
                    .apply(
                        RequestOptions().dontTransform()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(this)
            }

            rootView.setOnClickListener {
                navigateToPager(it, it.grid_image_view, item)
            }
        }

        private fun navigateToPager(view: View, imageView: ImageView, imageData: ImageData) {
            val direction = GridFragmentDirections
                .actionGridFragmentToImagePagerFragment(imageData.imageResource, imageData.id)

            val extras = FragmentNavigatorExtras(
                imageView to "${view.resources.getString(R.string.transition_shared_img)}_${imageData.id}"

            )
            view.findNavController().navigate(
                direction, extras
            )
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
