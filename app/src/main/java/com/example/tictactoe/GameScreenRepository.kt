package com.example.tictactoe

import android.app.Activity
import android.content.Intent
import android.widget.Toast

interface GameScreenRepository {
    fun setUI()

}


class GameScreenImpl(private val activity: Activity) : GameScreenRepository {

    init {
        var extras = activity.intent.extras;
        Toast.makeText(activity, "game selected", Toast.LENGTH_SHORT).show()
    }

    override fun setUI() {




    }


}