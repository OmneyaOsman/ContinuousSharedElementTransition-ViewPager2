package com.omni.continuoussharedelementtransition_viewpager2.pager

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.omni.continuoussharedelementtransition_viewpager2.DataGenerator
import com.omni.continuoussharedelementtransition_viewpager2.ImageData
import com.omni.continuoussharedelementtransition_viewpager2.R
import kotlinx.android.synthetic.main.pager_image_list_item.view.*


class ImagePagerAdapter(
    private val list: List<ImageData> = DataGenerator.list
    , private val onImageReady: ((Int) -> Unit?)
) :
    RecyclerView.Adapter<ImagePagerAdapter.PagerAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PagerAdapter.from(parent)

    override fun onBindViewHolder(holder: PagerAdapter, position: Int) {
        list[position].let {
          val  listener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onImageReady(position)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onImageReady(position)
                    return false
                }
            }
            holder.bind(it , listener)
        }
    }

    override fun getItemCount(): Int =
        list.size

    class PagerAdapter private constructor(private val rootView: View) :
        RecyclerView.ViewHolder(rootView) {

        fun bind(
            item: ImageData,
            listener: RequestListener<Drawable>
        ) {
            rootView.findViewById<ImageView>(R.id.pager_imageView_item)?.run {
                transitionName = "${resources.getString(R.string.transition_shared_img)}_${item.id}"

                Glide.with(rootView.context)
                    .load(item.imageResource).dontTransform()
                    .apply(
                        RequestOptions()
                            .error(R.drawable.ic_broken_image)
                    )
                    .addListener(listener)
                    .into(rootView.pager_imageView_item)
            }
            rootView.tag = item.id -1


        }

        companion object {
            fun from(parent: ViewGroup): PagerAdapter =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.pager_image_list_item, parent, false)
                    .let {
                        PagerAdapter(it)
                    }
        }
    }
}



