package com.example.tictactoe

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

interface GameScreenRepository {
    abstract fun setPossition(gridNumber: ImageView, possition: Int)


}


class GameScreenImpl(private val activity: Activity, gameModeTV: TextView) : GameScreenRepository {
    private var xTurn = true

    init {

        val singlePlayerGamer = activity.intent.extras?.getBoolean(GameScreenActivity.GAME_TYPE_SINGLE_PLAYER_EXTRA, false) ?: false
        val twoPlayerGamer = activity.intent.extras?.getBoolean(GameScreenActivity.GAME_TYPE_TWO_PLAYER_EXTRA, false) ?: false
        when {
            singlePlayerGamer -> {
                gameModeTV.text = activity.getString(R.string.single_player)
                Toast.makeText(activity, "single selected", Toast.LENGTH_SHORT).show()
            }
            twoPlayerGamer -> {
                gameModeTV.text = activity.getString(R.string.two_players)
                Toast.makeText(activity, "two player selected", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(activity, "no valid game selected", Toast.LENGTH_SHORT).show()
                activity.finish()
            }
        }

    }

    override fun setPossition(gridNumber: ImageView, possition: Int) {

        if(xTurn){
            gridNumber.setImageDrawable(ContextCompat.getDrawable(activity,R.drawable.ic_close_24px))

        }else{
            gridNumber.setImageDrawable(ContextCompat.getDrawable(activity,R.drawable.ic_circle_24px))
        }
        xTurn = !xTurn

    }


}