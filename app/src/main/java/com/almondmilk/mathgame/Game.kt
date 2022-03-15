package com.almondmilk.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Game : AppCompatActivity() {
    var random = Random()
    var number1 = 0
    var number2 = 0
    var time: TextView? = findViewById(R.id.time)
    var timer: CountDownTimer? = null
    var timer_running: Boolean? = null
    var time_left_in_milis = START_TIMER_IN_MILIS
    var userAnswer = 0
    var correctAnswer = 0
    var userScore = 0
    var userLife = 3
    var question: TextView? = findViewById(R.id.question)
    var life: TextView? = findViewById(R.id.life)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val ok: Button? = findViewById(R.id.buttonOk)
        val next: Button? = findViewById(R.id.buttonNext)
        var score: TextView? = findViewById(R.id.score)

        var answer: EditText? = findViewById(R.id.answer)
        questionChange()
        ok?.setOnClickListener{
            if (answer != null) {
                userAnswer = Integer.valueOf(answer.text.toString())
            }
            pauseTimer()
            if (userAnswer == correctAnswer) {
                userScore += 10
                score?.text = "" + userScore
                question?.text = "Congratulations your answer is correct!!!"
            } else {
                userLife -= 1
                life?.text = "" + userLife
                question?.text = "Try again :("
                while (userLife == -1) {
                    Toast.makeText(this@Game, "Game Over!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Game, End::class.java)
                    startActivity(intent)
                }
            }
        }
        next?.setOnClickListener{
            if (userLife != 0) {
                answer?.setText("")
                questionChange()
                resetTimer()
            } else {
                answer?.setText("")
                question?.setText("Game Over!")
                Toast.makeText(this@Game, "Game Over!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Game, End::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            }
        }
    }

    fun questionChange() {
        number1 = random.nextInt(100)
        number2 = random.nextInt(100)
        correctAnswer = number1 + number2
        question!!.text = "$number1 + $number2"
        startTimer()
    }

    fun startTimer() {
        timer = object : CountDownTimer(time_left_in_milis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time_left_in_milis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                timer_running = false
                pauseTimer()
                resetTimer()
                updateText()
                userLife -= 1
                life!!.text = "" + userLife
                question!!.text = "Your time is up!!!"
            }
        }.start()
        timer_running = true
    }

    fun updateText() {
        val second = (time_left_in_milis / 1000).toInt() % 60
        val time_left: String = String.format(Locale.getDefault(), "%02d", second)
        time!!.text = time_left
    }

    fun pauseTimer() {
        timer!!.cancel()
        timer_running = false
    }

    fun resetTimer() {
        time_left_in_milis = START_TIMER_IN_MILIS
        updateText()
    }

    companion object {
        private const val START_TIMER_IN_MILIS: Long = 60000
    }
}