package com.example.tictactoe

//import kotlinx.android.synthetic.main.activity_main.*

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUI()
    }

    private fun setUI() {
        val mainActivityRepository = MainActivityImpl(this@MainActivity)

        singlePlaterBTN.setOnClickListener {
            mainActivityRepository.singlePlayerGame()
        }
        twoPlayersBTN.setOnClickListener {
            mainActivityRepository.twoPlayerGame()
        }


    }
}