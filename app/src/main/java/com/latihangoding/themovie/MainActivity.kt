package com.latihangoding.themovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector, View.OnClickListener {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.fcv_nav) as NavHost
        val navController = navHost.navController
        bab_main.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                bab_main.visibility = View.VISIBLE
            } else {
                bab_main.visibility = View.GONE
            }
        }

        ib_movie.setOnClickListener(this)
        ib_tv.setOnClickListener(this)
    }



    override fun onClick(view: View?) {
        when (view) {
            ib_movie -> {
                ib_movie.setColorFilter(ContextCompat.getColor(this, R.color.teal_200))
                ib_tv.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            ib_tv -> {
                ib_movie.setColorFilter(ContextCompat.getColor(this, R.color.white))
                ib_tv.setColorFilter(ContextCompat.getColor(this, R.color.teal_200))
            }
        }
    }

    override fun androidInjector() = dispatchingAndroidInjector
}