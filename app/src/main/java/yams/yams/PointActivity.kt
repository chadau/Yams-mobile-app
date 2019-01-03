package yams.yams

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.alert
import org.jetbrains.anko.customView
import org.jetbrains.anko.editText

class PointActivity : AppCompatActivity() {

     private var des: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point)
    }


    fun full(view: View?){
        sendResult(25)
    }

    fun brelan(view: View?){
        sendResult(10)
    }

    fun grandSuite(view: View?){
        sendResult(45)
    }

    fun petiteSuite(view: View?){
        sendResult(30)
    }

    fun yams(view: View?){
        sendResult(50)
    }

    private fun sendResult(score: Int){
        val intent = Intent()
        intent.putExtra("score", score)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
