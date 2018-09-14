package com.architecture.clean.ui.login

import android.os.Bundle
import android.view.WindowManager
import com.architecture.clean.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import com.architecture.clean.ui.home.MainActivity
import android.content.Intent



class LoginActivity : DaggerAppCompatActivity() {
    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        start.setOnClickListener{
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
        }
    }
}