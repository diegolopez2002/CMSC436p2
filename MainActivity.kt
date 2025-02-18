package com.example.project2


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
class MainActivity : AppCompatActivity() {
    private lateinit var blackjack: BlackJack
    private lateinit var cardButton1: Button
    private lateinit var cardButton2: Button
    private lateinit var cardButton3: Button
    private lateinit var cardButton4: Button
    private lateinit var totalTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var dealButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blackjack = BlackJack()

        cardButton1 = findViewById(R.id.cardButton1)
        cardButton2 = findViewById(R.id.cardButton2)
        cardButton3 = findViewById(R.id.cardButton3)
        cardButton4 = findViewById(R.id.cardButton4)
        totalTextView = findViewById(R.id.totalTextView)
        statusTextView = findViewById(R.id.statusTextView)
        dealButton = findViewById(R.id.dealButton)

        // Initialize buttons and total text view
        resetUI()

        dealButton.setOnClickListener {
            if (blackjack.gameStatus() == "PLAY") {
                dealCards()
            } else {
                resetGame()
            }
        }
    }

    private fun dealCards() {
        if (blackjack.getCardsDealt() < 4) {
            // Deal a card to the next available button based on the cards dealt
            when (blackjack.getCardsDealt()) {
                0 -> {
                    val card1 = blackjack.dealCard()
                    cardButton1.text = String.format(Locale.getDefault(), "%d", card1)
                    val card2 = blackjack.dealCard()
                    cardButton2.text = String.format(Locale.getDefault(), "%d", card2)
                }
                2 -> {
                    val card3 = blackjack.dealCard()
                    cardButton3.text = String.format(Locale.getDefault(), "%d", card3)
                }
                3 -> {
                    val card4 = blackjack.dealCard()
                    cardButton4.text = String.format(Locale.getDefault(), "%d", card4)
                }
            }

            totalTextView.text = String.format(Locale.getDefault(), "%d", blackjack.getTotal())

            // Update the game status
            statusTextView.text = blackjack.gameStatus()

            // Disable DEAL button if the game is over (after 4 cards or total >= 17)
            if (blackjack.getCardsDealt() >= 4 || blackjack.getTotal() >= 17) {

                dealButton.setOnClickListener {
                    if (blackjack.gameStatus() == "PLAY") {
                        dealCards()
                    } else {
                        resetGame()
                    }
                }

            }


        }


    }

    private fun resetGame() {
        blackjack.resetGame()
        resetUI()
        statusTextView.text = getString(R.string.status_play)
        dealButton.isEnabled = true
    }

    private fun resetUI() {
        cardButton1.text = "0"
        cardButton2.text = "0"
        cardButton3.text = "0"
        cardButton4.text = "0"
        totalTextView.text = "0"
    }
}
