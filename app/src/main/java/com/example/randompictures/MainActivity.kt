package com.example.randompictures

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
import com.example.randompictures.databinding.ActivityMainBinding
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), PlantAdapter.Listener {
    lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()
    private val adapter = PlantAdapter(this)
    private val imageIdList = listOf(   R.drawable.letter_a,
                                        R.drawable.letter_b,
                                        R.drawable.letter_c )
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
//        getConnected()
        dataModel.messageForActivity.observe(this@MainActivity, {
            // updates when data changed by fragment
            binding.textView2.text = it
        })
//        binding.textView2.setOnClickListener {
//            dataModel.messageForFragment.value = "Hello from Activity"
//        }
    }

    private fun init() {
        binding.apply {
            //rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter
            buttonAdd.setOnClickListener {
                if (index > imageIdList.size-1) index = 0
                val plant = Plant(imageIdList[index], "Letter $index")
                adapter.addPlant(plant)
                index++
            }
        }
    }

//    private fun getConnected() {
//        // Instantiate the RequestQueue.
//        val queue = Volley.newRequestQueue(this)
//        val url = "https://www.google.com"
//
//        // Request a string response from the provided URL.
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                    response ->
//                // Display the first 500 characters of the response string.
//                binding.textView2.text = "Response is: ${response.substring(0, 2000)}"
//                // Toast.makeText(applicationContext, response.substring(0, 500), Toast.LENGTH_LONG).show()
//            },
//            {
//                binding.textView2.text = "That didn't work!"
//                //Toast.makeText(applicationContext, "That didn't work!", Toast.LENGTH_LONG).show()
//            }
//        )
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest)
//    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun sendGet(): String {
        val url = URL("http://www.google.com/")

        with(url.openConnection() as HttpURLConnection) {
            val requestMethod = "GET"  // optional default is GET

            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    println(line)
                }
            }

        }
        return url.openConnection().inputStream.bufferedReader().lines().toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(plant: Plant) {
        // Toast.makeText(this, "Pressed ${plant.title}", Toast.LENGTH_LONG).show()
        supportFragmentManager.beginTransaction().replace(R.id.place_holder, BlankFragment.newInstance()).commit()
//        dataModel.messageForFragment.value = plant.title
        dataModel.messageForFragment.value = sendGet()
//        startActivity(Intent(this, BlankFragment::class.java).apply{
//            putExtra("item", plant)
//        })
    }

}