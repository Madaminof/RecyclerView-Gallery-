package com.example.recyclerview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ImageViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val imageView: ImageView = findViewById(R.id.imageViewDetail)
        val imageName: TextView = findViewById(R.id.textView)

        // Intent orqali Uri va name olish
        val imageUriString = intent.getStringExtra("image_uri")
        val name = intent.getStringExtra("name")

        // Uri null bo'lmasa, Glide orqali rasmni yuklash
        imageUriString?.let {
            val imageUri = Uri.parse(it)
            Glide.with(this).load(imageUri).into(imageView)
        }

        imageName.text = name ?: "Noma'lum rasm"

        // Share Intent (rasmni ijtimoiy tarmoqlarga ulash)
        val shareFab: FloatingActionButton = findViewById(R.id.fabShare)
        shareFab.setOnClickListener {
            imageUriString?.let {
                val uri = Uri.parse(it)

                // Intent yaratish (rasmni ulashish)
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "image/*"
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Mana, shunday rasmni ko'ring: $name")

                try {
                    startActivity(Intent.createChooser(shareIntent, "Rasmni ulashish"))
                } catch (e: Exception) {
                    Toast.makeText(this, "Ijtimoiy tarmoqni ulashish uchun ilova topilmadi.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
