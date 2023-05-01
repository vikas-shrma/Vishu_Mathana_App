package kkn.vishu.mathana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kkn.vishu.mathana.ui.HomeScreen

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init(){

        Handler().postDelayed({

            startActivity(Intent(this,HomeScreen::class.java))
            finish()
        },10)
    }
}