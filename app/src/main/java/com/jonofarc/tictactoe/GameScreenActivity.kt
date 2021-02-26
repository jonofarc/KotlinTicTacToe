package com.jonofarc.tictactoe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game_screen.*

class GameScreenActivity : AppCompatActivity() {


    companion object {
        val GAME_TYPE_SINGLE_PLAYER_EXTRA =
            GameScreenActivity::class.java.simpleName + "_GAME_TYPE_SINGLE_PLAYER_EXTRA"
        val GAME_TYPE_TWO_PLAYER_EXTRA =
            GameScreenActivity::class.java.simpleName + "_GAME_TYPE_TWO_PLAYER_EXTRA"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        setUI()
    }

    private fun setUI() {

        val mainActivityRepository = GameScreenImpl(this@GameScreenActivity, gameModeTV, restartBTN)
        grid0.setOnClickListener { mainActivityRepository.setPossition(grid0, 0) }
        grid1.setOnClickListener { mainActivityRepository.setPossition(grid1, 1) }
        grid2.setOnClickListener { mainActivityRepository.setPossition(grid2, 2) }
        grid3.setOnClickListener { mainActivityRepository.setPossition(grid3, 3) }
        grid4.setOnClickListener { mainActivityRepository.setPossition(grid4, 4) }
        grid5.setOnClickListener { mainActivityRepository.setPossition(grid5, 5) }
        grid6.setOnClickListener { mainActivityRepository.setPossition(grid6, 6) }
        grid7.setOnClickListener { mainActivityRepository.setPossition(grid7, 7) }
        grid8.setOnClickListener { mainActivityRepository.setPossition(grid8, 8) }
        restartBTN.setOnClickListener { mainActivityRepository.reset() }

    }
}