package com.almondmilk.mathgame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSub = findViewById<Button>(R.id.buttonSub)
        val buttonMulti = findViewById<Button>(R.id.buttonMulti)
        buttonAdd.setOnClickListener{
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
            finish()
        }
        buttonSub.setOnClickListener { }
        buttonMulti.setOnClickListener { }
    }
}