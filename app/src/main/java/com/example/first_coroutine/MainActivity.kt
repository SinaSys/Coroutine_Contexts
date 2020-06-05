package com.example.first_coroutine

import android.graphics.DiscretePathEffect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {

            Log.d(TAG, "Starting Coroutine in thread ${Thread.currentThread().name}")
            val networkRequest = doNetworkCall()

            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text for textview in thread ${Thread.currentThread().name}")
                tvAnswer.text = networkRequest
            }

        }
    }

    private suspend fun doNetworkCall(): String {
        delay(3000)
        return "network Call Operation From doNetworkCall suspend function"
    }

}
