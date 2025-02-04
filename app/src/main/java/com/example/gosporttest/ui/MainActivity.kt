package com.example.gosporttest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gosporttest.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.FrameLayout__FragmentHost, MainFragment.newInstance())
            .commit()
    }
}