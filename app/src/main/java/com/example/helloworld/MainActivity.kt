package com.example.helloworld

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var gameActive = true

    var activePlayer = 0

    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    var winPositions = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun tapButton(view: View) {
        var img: ImageView = view as ImageView
        var tappedImage : Int = Integer.parseInt(img.getTag().toString())
        if (!gameActive) {
            gameReset(view)
        }
        if (gameState[tappedImage] === 2) {
            gameState[tappedImage] = activePlayer
            img.translationY = -1000f
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x)
                activePlayer = 1
                tv1.text = "O's Turn - Tap to play"
            } else {
                img.setImageResource(R.drawable.o)
                activePlayer = 0
                tv1.text = "X's Turn - Tap to play"
            }
            img.animate().translationYBy(1000f).duration = 300
        }

        // Check if any player has won
        for(i in 0 until winPositions.size){
            var winPosition = winPositions[i]
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                gameState[winPosition[0]] != 2
            ) {
                // Somebody has won! - Find out who!
                var winnerStr = ""
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                } else {
                    winnerStr = "O has won";
                }
                // Update the status bar for winner announcement
                tv1.setText(winnerStr);

            }
        }
    }

    fun gameReset(view : View){
        gameActive = true
        activePlayer = 0
        for (i in 0 until gameState.size) {
            gameState[i] = 2
        }
        bt1.setImageResource(0)
        bt2.setImageResource(0)
        bt3.setImageResource(0)
        bt4.setImageResource(0)
        bt5.setImageResource(0)
        bt6.setImageResource(0)
        bt7.setImageResource(0)
        bt8.setImageResource(0)
        bt9.setImageResource(0)

        tv1.setText("X's Turn - Tap to play")
    }

    fun checkArray(x: Array<Array<Int>>): Boolean {


        return false;


    }
}

