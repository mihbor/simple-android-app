package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var answer = 0
    var gameOver = false
    var guesses = 0

    fun generateAnswer() {
        answer = Random.nextInt(1, 25)
    }

    fun startOver(view: View) {
        gameOver = false
        findViewById<Button>(R.id.buttonSubmit).isEnabled = true
        guesses = 0
        generateAnswer()
        findViewById<TextView>(R.id.answer).text = "??"
        findViewById<TextView>(R.id.textView).text = "Guess 1 to 25"
        findViewById<EditText>(R.id.editTextGuess).text.clear()
    }
    fun submit(view: View) {
        val guess = getUserGuess()
        if (guess !in 1..25) {
            findViewById<TextView>(R.id.textView).text = "Guess must be 1 to 25"
        } else {
            guesses++
            if (guess == answer) {
                gameOver = true
                findViewById<Button>(R.id.buttonSubmit).isEnabled = false
                findViewById<TextView>(R.id.textView).text = "Correct! Guess(es): $guesses"
                findViewById<TextView>(R.id.answer).text = answer.toString()
            } else findViewById<TextView>(R.id.textView).text =
                if (guess!! < answer) "Too low!" else "Too high!"
        }
    }

    fun getUserGuess() = findViewById<TextView>(R.id.editTextGuess).text.toString().toIntOrNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateAnswer()
    }
}