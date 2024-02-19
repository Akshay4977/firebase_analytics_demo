package com.example.newanalyticspractice

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val textView = findViewById(R.id.text) as TextView
        textView.setOnClickListener {
            temp()
        }
    }

    fun temp() {



        var bundle = Bundle()
        bundle.putString("first", "value")
        /*bundle.putString(FirebaseAnalytics.Param.METHOD, "method")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)*/
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "Main class");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
        firebaseAnalytics.logEvent("firebaseAnalytics", bundle)

        imageLog()
        Toast.makeText(this,"Event logged", Toast.LENGTH_LONG).show()
    }

    fun imageLog(){
        val params = Bundle()
        params.putString("image_name", "image_1")
        params.putString("full_text", "aksh")
        firebaseAnalytics.logEvent("share_image", params)
    }

}