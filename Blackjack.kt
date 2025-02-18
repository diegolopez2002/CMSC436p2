package com.example.project2

class BlackJack {
    private var cardsDealt = 0
    private var total = 0

    fun dealCard(): Int {
        val card = (2..13).random()  // Card value between 2 and 13
        total += card
        cardsDealt++
        return card
    }

    fun getCardsDealt(): Int = cardsDealt

    fun getTotal(): Int = total

    fun gameStatus(): String {
        return when {
            total > 21 -> "LOST"
            total in 17..21 -> "WON"
            else -> "PLAY"
        }
    }

    fun resetGame() {
        cardsDealt = 0
        total = 0
    }
}
