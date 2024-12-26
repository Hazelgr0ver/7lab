package ru.egorrublev.edibleornot1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var buttonEdible: Button
    private lateinit var buttonInedible: Button
    private lateinit var textViewScore: TextView

    private var correctAnswers = 0
    private var incorrectAnswers = 0

    private val items: List<Pair<Int, Boolean>> = listOf(
        Pair(R.drawable.food01, true),
        Pair(R.drawable.food02, true),
        Pair(R.drawable.food03, true),
        Pair(R.drawable.food04, true),
        Pair(R.drawable.food05, true),
        Pair(R.drawable.food06, true),
        Pair(R.drawable.food07, true),
        Pair(R.drawable.food08, true),
        Pair(R.drawable.food09, true),
        Pair(R.drawable.food10, true),
        Pair(R.drawable.food11, true),
        Pair(R.drawable.food12, true),
        Pair(R.drawable.food13, true),
        Pair(R.drawable.food14, true),
        Pair(R.drawable.food15, true),
        Pair(R.drawable.food16, true),
        Pair(R.drawable.food17, true),
        Pair(R.drawable.food18, true),
        Pair(R.drawable.food19, true),
        Pair(R.drawable.food20, true),
        Pair(R.drawable.sport01, false),
        Pair(R.drawable.sport02, false),
        Pair(R.drawable.sport03, false),
        Pair(R.drawable.sport04, false),
        Pair(R.drawable.sport05, false),
        Pair(R.drawable.sport06, false),
        Pair(R.drawable.sport07, false),
        Pair(R.drawable.sport08, false),
        Pair(R.drawable.sport09, false),
        Pair(R.drawable.sport10, false),
        Pair(R.drawable.sport11, false),
        Pair(R.drawable.sport12, false),
        Pair(R.drawable.sport13, false),
        Pair(R.drawable.sport14, false),
        Pair(R.drawable.sport15, false),
        Pair(R.drawable.sport16, false),
        Pair(R.drawable.sport17, false),
        Pair(R.drawable.sport18, false),
        Pair(R.drawable.sport19, false),
        Pair(R.drawable.sport20, false),
    )

    private var currentImageIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        buttonEdible = findViewById(R.id.buttonEdible)
        buttonInedible = findViewById(R.id.buttonInedible)
        textViewScore = findViewById(R.id.textViewScore)

        if (savedInstanceState != null) {
            // Восстанавливаем состояние
            correctAnswers = savedInstanceState.getInt("correctAnswers", 0)
            incorrectAnswers = savedInstanceState.getInt("incorrectAnswers", 0)
            currentImageIndex = savedInstanceState.getInt("currentImageIndex", -1)
            if (currentImageIndex != -1) {
                val (imageResId, _) = items[currentImageIndex]
                imageView.setImageResource(imageResId)
            }
        } else {
            loadNewImage()
        }

        buttonEdible.setOnClickListener {
            checkAnswer(true)
        }

        buttonInedible.setOnClickListener {
            checkAnswer(false)
        }

        updateScore()
    }

    private fun loadNewImage() {
        currentImageIndex = Random.nextInt(items.size)
        val (imageResId, _) = items[currentImageIndex]
        imageView.setImageResource(imageResId)
    }

    private fun checkAnswer(isEdible: Boolean) {
        if (currentImageIndex == -1) return

        val correctAnswer = items[currentImageIndex].second

        if (isEdible == correctAnswer) {
            correctAnswers++
        } else {
            incorrectAnswers++
        }

        updateScore()
        loadNewImage()
    }

    private fun updateScore() {
        textViewScore.text = getString(R.string.correct_answers, correctAnswers) + ", " + getString(R.string.incorrect_answers, incorrectAnswers)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем состояние
        outState.putInt("correctAnswers", correctAnswers)
        outState.putInt("incorrectAnswers", incorrectAnswers)
        outState.putInt("currentImageIndex", currentImageIndex)
    }
}