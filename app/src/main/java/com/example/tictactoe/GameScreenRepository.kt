package com.example.tictactoe

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.ContentFrameLayout
import androidx.core.content.ContextCompat


interface GameScreenRepository {
    fun setPossition(gridNumber: ImageView, position: Int)
    fun setWinUI(player: Int)
    fun reset()


}


class GameScreenImpl(private val activity: Activity, private var gameModeTV: TextView, private var restartBTN: Button) : GameScreenRepository {
    private var singlePlayerGamer = false
    private var twoPlayerGamer = false

    private var xTurn = true
    private var winConditions = mutableListOf<List<String>>()
    private var xMoves = mutableListOf<String>()
    private var yMoves = mutableListOf<String>()
    private var gameFinished = false
    private var movesDone = mutableListOf<String>()
    private val root: ContentFrameLayout = activity.findViewById(android.R.id.content)


    init {

        winConditions.add(listOf("0", "1", "2"))
        winConditions.add(listOf("3", "4", "5"))
        winConditions.add(listOf("6", "7", "8"))
        winConditions.add(listOf("0", "3", "6"))
        winConditions.add(listOf("1", "4", "7"))
        winConditions.add(listOf("2", "5", "8"))
        winConditions.add(listOf("0", "4", "8"))
        winConditions.add(listOf("2", "4", "6"))

        xTurn = true
        xMoves.clear()
        yMoves.clear()
        gameFinished = false
        restartBTN.text = activity.getString(R.string.restart)

        singlePlayerGamer = activity.intent.extras?.getBoolean(GameScreenActivity.GAME_TYPE_SINGLE_PLAYER_EXTRA, false)
                ?: false
        twoPlayerGamer = activity.intent.extras?.getBoolean(GameScreenActivity.GAME_TYPE_TWO_PLAYER_EXTRA, false)
                ?: false
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

    override fun reset() {

        val intent = activity.intent
        activity.finish()
        activity.startActivity(intent)

    }

    override fun setPossition(gridNumber: ImageView, position: Int) {

        if (!gameFinished && !movesDone.contains(position.toString())) {
            movesDone.add(position.toString())
            if (xTurn) {
                gridNumber.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_close_24px))
                xMoves.add(position.toString())

            } else {
                gridNumber.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_circle_24px))
                yMoves.add(position.toString())
            }

            xTurn = !xTurn



            winConditions.forEach { winCondition ->
                var winConditionCheck = xMoves.containsAll(winCondition)
                if (winConditionCheck) {


                    val views = getViewsByTag(root, winCondition)
                    views.forEach { view ->
                        (view as ImageView).drawable.mutate().setTint(ContextCompat.getColor(activity, R.color.green))
                    }
                    gameFinished = true
                    setWinUI(1)

                }

                winConditionCheck = yMoves.containsAll(winCondition)
                if (winConditionCheck) {

                    val views = getViewsByTag(root, winCondition)
                    views.forEach { view ->
                        (view as ImageView).drawable.mutate().setTint(ContextCompat.getColor(activity, R.color.green))
                    }
                    gameFinished = true
                    setWinUI(2)

                }


            }

        }

        checkCpuMove()

    }

    /**
     * function to generate a CPu move this is where any kind of AI would help
     */

    private fun checkCpuMove() {

        //add delay to make the flow feel more natural
        Thread {
            Thread.sleep(200)
            if (singlePlayerGamer && !gameFinished && !xTurn && movesDone.size < 9) {
                var validMove = false
                var cpuMove = 0
                while (!validMove) {
                    cpuMove = (0..8).random()
                    if (!movesDone.contains(cpuMove.toString())) {
                        validMove = true
                    }
                }


                val view = getViewsByTag(root, cpuMove.toString())
                activity.runOnUiThread {
                    setPossition(view as ImageView, cpuMove)
                }


            }
        }.start()


    }

    override fun setWinUI(player: Int) {
        gameModeTV.text = activity.getString(R.string.player_won, player)
        restartBTN.text = activity.getString(R.string.play_again)
    }


    private fun getViewsByTag(root: ViewGroup, tags: List<String>): List<View> {
        val views: ArrayList<View> = ArrayList()

        val childCount = root.childCount
        for (i in 0 until childCount) {
            val child: View = root.getChildAt(i)
            if (child is ViewGroup) {
                views.addAll(getViewsByTag(child, tags))
            }
            val tagObj: Any? = child.tag
            tags.forEach { tag ->
                if (tagObj == tag) {
                    views.add(child)
                }
            }

        }
        return views
    }

    private fun getViewsByTag(root: ViewGroup, tag: String): View {
        val views = getViewsByTag(root, listOf(tag))
        return views[0]
    }


}