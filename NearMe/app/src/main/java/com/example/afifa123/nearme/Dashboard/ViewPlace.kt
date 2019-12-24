package com.example.afifa123.nearme.Dashboard

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.afifa123.nearme.Common.Common
import com.example.afifa123.nearme.Model.MyPlaces
import com.example.afifa123.nearme.Model.PlaceDetail
import com.example.afifa123.nearme.R
import com.example.afifa123.nearme.Remote.IGoogleApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view_place.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ViewPlace : AppCompatActivity() {

    lateinit var mServices: IGoogleApiService
    var mPlace:PlaceDetail?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_place)

        //init service
        mServices = Common.googleApiService

        //ser empty for all text
        place_name_text_view.text = ""
        place_address_text_view.text = ""
        opening_hour_text_view.text = ""

        show_on_map_button.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mPlace!!.result!!.url))
            startActivity(mapIntent)
        }

        //load photos
        if (Common.currentResult!!.photos != null && Common.currentResult!!.photos!!.size>0 )
            Picasso.with(this)
                .load(getPhotoOfPlace(Common.currentResult!!.photos!![0].photo_reference!!,1000))
                .into(view_place_image_view)

        //load open hours
        if (Common.currentResult!!.opening_hours != null)
            opening_hour_text_view.text = "Open now: "+Common.currentResult!!.opening_hours!!.open_now
        else
            opening_hour_text_view.visibility = View.GONE

        //use service to fetch address and name
        mServices.getDetailPlace(getPlaceDetaiUrl(Common.currentResult!!.place_id))
            .enqueue(object : Callback<PlaceDetail>{
                override fun onFailure(call: Call<PlaceDetail>, t: Throwable) {
                    Toast.makeText(baseContext,""+t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<PlaceDetail>, response: Response<PlaceDetail>) {
                    mPlace = response.body()

                    place_name_text_view.text = mPlace!!.result!!.name
                    place_address_text_view.text =mPlace!!.result!!.formatted_address

                }

            })
        
    }

    private fun getPlaceDetaiUrl(placeId: String?): String {
        val url = StringBuilder("https://maps.googleapis.com/maps/api/place/details/json")
        url.append("?place_id=$placeId")
        url.append("&key=AIzaSyALnOpjo1MqEMlV-s90YIWphbbq6txmFgM")
        return url.toString()

    }

    private fun getPhotoOfPlace(photoReference: String?, maxWidth: Int): String {
        val url = StringBuilder("https://maps.googleapis.com/maps/api/place/photo")
        url.append("?maxwidth=$maxWidth")
        url.append("&photoreference=$photoReference")
        url.append("&key=AIzaSyALnOpjo1MqEMlV-s90YIWphbbq6txmFgM")
        return url.toString()
    }

}
