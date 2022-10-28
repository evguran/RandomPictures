package com.example.randompictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randompictures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = PlantAdapter()
    private val imageIdList = listOf(   R.drawable.letter_a,
                                        R.drawable.letter_b,
                                        R.drawable.letter_c )
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
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
}