package yams.yams

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class GameActivity : AppCompatActivity() {

    companion object {
        val REQUEST_CODE = 1
    }
    private var tour = 1
    private var playerInGame = 0
    private  lateinit var players: ArrayList<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val bundle = intent.extras
        val tmp : java.util.ArrayList<Player>? = bundle?.getParcelableArrayList("Players")
        players = tmp as kotlin.collections.ArrayList<Player>
        findViewById<TextView>(R.id.playerName).text = players[playerInGame].name
        findViewById<TextView>(R.id.score).text = players[playerInGame].score.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun nextTour(){
        val tourText = findViewById<TextView>(R.id.tour)

        tour++
        tourText.text = "Tour$tour"
    }

    private fun playerTours(): Boolean {
        val finish: Boolean
        if (playerInGame >= (players.size - 1)) {
            playerInGame = 0
            finish = true
        }
        else {
            playerInGame++
            finish = false
        }
        findViewById<TextView>(R.id.playerName).text = players[playerInGame].name
        findViewById<TextView>(R.id.score).text = players[playerInGame].score.toString()
        return (finish)
    }

    private fun endGame(){
        val button = findViewById<Button>(R.id.buttonPlay)
        findViewById<TextView>(R.id.playerName).text = ""
        findViewById<TextView>(R.id.tour).text = getString(R.string.end_game).capitalize()
        findViewById<TextView>(R.id.score).text = ""
        button.text = getString(R.string.again)
        button.setOnClickListener { v -> finish() }
    }

    fun calculPoint(view: View?){
        val intent = Intent(this, PointActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    fun onNext(view: View?){

        if (playerTours()) {
            if (tour == 13)
                endGame()
            else
                nextTour()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val result : Int = data?.getIntExtra("score", 0) as Int
                players[playerInGame].score += result
                findViewById<TextView>(R.id.score).text = players[playerInGame].score.toString()
            }
        }
    }
}
