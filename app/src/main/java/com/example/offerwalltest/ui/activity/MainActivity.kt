package com.example.offerwalltest.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.offerwalltest.R
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        try {
            Navigation.findNavController(this, R.id.navHostFragment)
                .navigate(R.id.onCreate)
        } catch (e: Exception) {
            println(e)
        }
    }

    /*override fun onResume() {
        super.onResume()
        onStart()
    }*/
}