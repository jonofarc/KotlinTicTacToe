package com.example.tictactoe

import android.app.Activity
import android.content.Intent
import android.widget.Toast

interface MainActivityRepository {
    fun singlePlayerGame()
    fun twoPlayerGame()
}


class MainActivityImpl(private val activity: Activity) : MainActivityRepository {

    override fun singlePlayerGame() {
        val intent = Intent(activity, GameScreenActivity::class.java)
        intent.putExtra(GameScreenActivity.GAME_TYPE_SINGLE_PLAYER_EXTRA, true)
        activity.startActivity(intent)
    }

    override fun twoPlayerGame() {
        val intent = Intent(activity, GameScreenActivity::class.java)
        intent.putExtra(GameScreenActivity.GAME_TYPE_TWO_PLAYER_EXTRA, true)
        activity.startActivity(intent)
    }

}