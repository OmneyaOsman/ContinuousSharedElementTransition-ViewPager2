package com.omni.continuoussharedelementtransition_viewpager2.feature.pager

//class ImagePagerAdapter(private val IMAGES:Array<ImageData> = ImageData.values()) : RecyclerView.Adapter<PagerViewHolder>() {
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PagerViewHolder =
//         LayoutInflater.from(parent.context).inflate(
//             R.layout.grid_image_list_item, parent, false
//        ).let {
//             PagerViewHolder(
//                 it
//             )
//         }
//
//    override fun getItemCount() = IMAGES.size
//
//    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
//        holder.bind(IMAGES[position])
//    }
//}
//
//class PagerViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
//    fun bind(imageData: ImageData) {
//
//        Glide.with(root.context)
//            .load(imageData.imageResource)
//            .apply(
//                RequestOptions()
//                    .placeholder(R.drawable.loading_animation)
//                    .error(R.drawable.ic_broken_image))
//            .into(root.grid_image_view)
//
//    }
//
//}