package com.architecture.clean.ui.detail

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.WindowManager
import com.architecture.clean.R
import com.architecture.clean.databinding.ActivityDetailBinding
import com.architecture.clean.domain.model.Food
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : DaggerAppCompatActivity() {
    private val TAG = DetailActivity::class.java.simpleName

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val food: Food= intent.getParcelableExtra("food")
        val binding : ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.item=food
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        back.setOnClickListener {
            supportFinishAfterTransition()
        }
        fab.setOnClickListener{
            Log.d(TAG,fab.tag.toString())
            when(fab.tag.toString()){

                getString(R.string.not_clicked) -> {
                    fab.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.ic_favorite_red_24dp))
                    fab.tag=getString(R.string.clicked)
                }
                getString(R.string.clicked) -> {
                    fab.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.ic_favorite_border_black_24dp))
                    fab.tag=getString(R.string.not_clicked)
                }

            }

        }
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }
}
