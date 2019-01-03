package yams.yams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    private lateinit var playerLayout: LinearLayout
    private var nbrPlayer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerLayout = findViewById(R.id.playerLayout)
        onAddPlayer(null)
        onAddPlayer(null)
    }

    fun onAddPlayer(view: View?){
        val newPlayer = View.inflate(this, R.layout.player_item, null) as TextInputLayout

        if (nbrPlayer < 5){
        nbrPlayer++
        newPlayer.hint = "Joueur " + nbrPlayer
        playerLayout.addView(newPlayer, playerLayout.childCount - 1)
        }
    }

    fun onNext(view: View?) {
        val listPlayer = arrayListOf<Player>()
        val intent = Intent(this, GameActivity::class.java)
        var item: TextInputLayout
        val bundle = Bundle()
        var i: Int = 0

        while (i < (playerLayout.childCount - 1)){
            item = playerLayout.getChildAt(i) as TextInputLayout
            if (!item.editText?.text.isNullOrEmpty())
                listPlayer.add(Player(item.editText?.text.toString()))
            i++
        }
        bundle.putParcelableArrayList("Players", listPlayer)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
