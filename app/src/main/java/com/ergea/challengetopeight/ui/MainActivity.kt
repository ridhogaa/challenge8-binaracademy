package com.ergea.challengetopeight.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ergea.challengetopeight.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}