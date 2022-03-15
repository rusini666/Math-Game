package com.almondmilk.mathgame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class End : AppCompatActivity() {

    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        val playAgain = findViewById<Button>(R.id.playagain)
        val exit = findViewById<Button>(R.id.exit)
        val result = findViewById<TextView>(R.id.result)
        val intent = intent
        score = intent.getIntExtra("score", 0)
        val userScore = score.toString()
        result.text = "Your score: $userScore"
        playAgain.setOnClickListener{
            val intent = Intent(this@End, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        exit.setOnClickListener { finish() }
    }
}