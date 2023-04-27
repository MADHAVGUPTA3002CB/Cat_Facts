package com.example.cat_facts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var data : JSONArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadfact()
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
                val randomNumber = Random.nextInt(data!!.length())
                val jsonObject = data!!.getJSONObject(randomNumber)
                val fact = jsonObject.getString("fact")
                val fact_view : TextView = findViewById(R.id.fact_text)
                fact_view.text = fact
            },
            {
                Toast.makeText(this, "Something went wrong \n ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            })
        queue.add(jsonObjectRequest)
        }
    fun nextfact(view: View) {
        loadfact()
    }
}