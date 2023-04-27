package com.example.cat_facts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Math.random
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
//    private lateinit var refresh_button : Button
    var data : JSONArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadfact()
//        refresh_button.setOnClickListener {
//            loadfact()
//        }

    }

    private fun loadfact(){
        val queue = Volley.newRequestQueue(this)
        val randomchoose = Random.nextInt(1,35)
        val url = "https://catfact.ninja/facts?page=$randomchoose"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->


                data = response.getJSONArray("data")
//                var length : Int
//                data?.let {
//                    length = data!!.length()
//                }
//                val length : Int? = data.length()
                val randomNumber = Random.nextInt(data!!.length())
                val jsonObject = data!!.getJSONObject(randomNumber)
                val fact = jsonObject.getString("fact")



//                Log.d("facts" , "$data")
//                val jsonArray = data.optJSONArray("fact")


//                data = response.getJSONObject("data")
                val fact_view : TextView = findViewById(R.id.fact_text)
                fact_view.text = fact




            },
            {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

            })

        queue.add(jsonObjectRequest)
        }
    fun nextfact(view: View) {
        loadfact()
    }
}