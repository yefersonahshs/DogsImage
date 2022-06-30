package com.example.dogsimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.dogsimage.databinding.ActivityMainBinding
import com.example.dogsimage.service.RandonImageService
import com.example.dogsimage.service.RetrofitGenerator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitGenerator.getInstance()
        val randomImageService = retrofit.create(RandonImageService::class.java)

        loadRandomImage(randomImageService, binding.image)
        binding.Button.setOnClickListener {
            loadRandomImage(randomImageService, binding.image)
        }


    }


    private fun loadRandomImage(
        randomImageService: RandonImageService,
        image: ImageView?
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = randomImageService.randomDogImage()
            if (response.isSuccessful) {
                val randomDogImageDto = response.body()
                Log.d("Developer", "Response:  $randomDogImageDto")
                runOnUiThread {
                    Picasso.get().load(randomDogImageDto?.message).into(image)
                }
            }
        }
    }
}
