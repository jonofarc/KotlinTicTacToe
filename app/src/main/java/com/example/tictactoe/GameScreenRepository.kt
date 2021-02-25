package com.example.tictactoe

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.ContentFrameLayout
import androidx.core.content.ContextCompat


interface GameScreenRepository {
    abstract fun setPossition(gridNumber: ImageView, possition: Int)
    abstract fun setWinUI(player: Int)


}


class GameScreenImpl(private val activity: Activity, gameModeTV: TextView) : GameScreenRepository {
    private var xTurn = true
    private var winConditions = mutableListOf<List<Int>>()
    private var xMoves = mutableListOf<Int>()
    private var yMoves = mutableListOf<Int>()
    private var gameFinished = false
    
    private var gameModeTV = gameModeTV 


    init {

        winConditions.add(listOf(0, 1, 2))
        winConditions.add(listOf(3, 4, 5))
        winConditions.add(listOf(6, 7, 8))
        winConditions.add(listOf(0, 3, 6))
        winConditions.add(listOf(1, 4, 7))
        winConditions.add(listOf(2, 5, 8))
        winConditions.add(listOf(0, 4, 8))
        winConditions.add(listOf(2, 4, 6))

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

        if(!gameFinished){

            if(xTurn){
                gridNumber.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_close_24px))
                xMoves.add(possition)

            }else{
                gridNumber.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_circle_24px))
                yMoves.add(possition)
            }

            xTurn = !xTurn


            val root: ContentFrameLayout = activity.findViewById(android.R.id.content)
            winConditions.forEach{ winCondition ->
                var winConditionCheck = xMoves.containsAll(winCondition)
                if(winConditionCheck){


                    val views = getViewsByTag(root, winCondition)
                    views.forEach { view ->
                        (view as ImageView).drawable.mutate().setTint(ContextCompat.getColor(activity, R.color.green))
                    }
                    gameFinished = true
                    setWinUI(1)

                }

                winConditionCheck = yMoves.containsAll(winCondition)
                if(winConditionCheck){

                    val views = getViewsByTag( root, winCondition)
                    views.forEach { view ->
                        (view as ImageView).drawable.mutate().setTint(ContextCompat.getColor(activity, R.color.green))
                    }
                    gameFinished = true
                    setWinUI(2)

                }

            }

        }






    }

    override fun setWinUI(player: Int) {
        gameModeTV.text = activity.getString(R.string.player_won, player)
    }

    private fun getViewsByTag(root: ViewGroup, tags: List<Int>): List<View> {
        val views: ArrayList<View> = ArrayList()

        val childCount = root.childCount
        for (i in 0 until childCount) {
            val child: View = root.getChildAt(i)
            if (child is ViewGroup) {
                views.addAll(getViewsByTag(child, tags))
            }
            val tagObj: Any? = child.tag
            tags.forEach{ tag ->
                if (tagObj == tag.toString()) {
                    views.add(child)
                }
            }

        }
        return views
    }




}