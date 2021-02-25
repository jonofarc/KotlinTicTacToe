package com.example.tictactoe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GameScreenActivity : AppCompatActivity() {


    companion object {
        val GAME_TYPE_SINGLE_PLAYER_EXTRA = GameScreenActivity::class.java.simpleName+"_GAME_TYPE_SINGLE_PLAYER_EXTRA"
        val GAME_TYPE_TWO_PLAYER_EXTRA = GameScreenActivity::class.java.simpleName+"_GAME_TYPE_TWO_PLAYER_EXTRA"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        setUI()
    }

    private fun setUI() {
        val mainActivityRepository = GameScreenImpl(this@GameScreenActivity)
    }
}