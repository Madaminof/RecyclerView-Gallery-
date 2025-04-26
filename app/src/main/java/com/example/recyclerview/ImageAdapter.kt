package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(
    private val imageList: List<ImageItem>, // List of images
    private val onClick: (ImageItem) -> Unit // Lambda to handle click event
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    // Create a new view holder and inflate the item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ImageViewHolder(view)
    }

    // Bind data to the view holder at a specific position
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageItem = imageList[position]
        holder.bind(imageItem) // Bind data to the view holder
    }

    // Get the size of the image list
    override fun getItemCount(): Int = imageList.size

    // ViewHolder class to hold individual image view
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView) // Image view to display the image

        // Bind the image data to the image view
        fun bind(imageItem: ImageItem) {
            // Use Glide to load image from URI into the image view
            Glide.with(itemView.context)
                .load(imageItem.imageUri) // Load the image URI
                .into(imageView) // Set the image into the ImageView

            // Set click listener on the item view
            itemView.setOnClickListener {
                onClick(imageItem) // Trigger the click event handler (open the image in a viewer)
            }
        }
    }
}
