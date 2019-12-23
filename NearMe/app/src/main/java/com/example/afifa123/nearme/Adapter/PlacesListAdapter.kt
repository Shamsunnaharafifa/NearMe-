package com.example.afifa123.nearme.Adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.afifa123.nearme.Model.MyPlaces
import com.example.afifa123.nearme.R
import org.jetbrains.anko.imageBitmap


class PlacesListAdapter(private val results: ArrayList<MyPlaces>):RecyclerView.Adapter<PlacesListAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_place_row_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(results[position])
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItems(results: MyPlaces){
            val place_image_view: ImageView = itemView.findViewById(R.id.place_image_view)
            val name_text_view: TextView = itemView.findViewById(R.id.name_text_view)

            val results = results.results?.get(position)
            name_text_view.text = results?.name
            Glide.with(itemView.context).load(results?.photos).into(place_image_view)
            /*val photo = ImageRequestAsk().execute(result?.icon).get()
            place_image_view.setImageBitmap(photo)*/

        }
    }

    private  class ImageRequestAsk : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg params: String): Bitmap? {
            try {
                val inputStream = java.net.URL(params[0]).openStream()
                return BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                return null
            }

        }

    }

}