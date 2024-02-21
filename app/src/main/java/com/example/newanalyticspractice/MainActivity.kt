package com.example.newanalyticspractice

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    var start: String = ""
    var destination: String = ""
    var customer: String = ""
    var price: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val textView = findViewById<Button>(R.id.text)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val startEdiTextView = findViewById<EditText>(R.id.start)
        val destinationEdiTextView = findViewById<EditText>(R.id.destination)
        val priceEdiTextView = findViewById<EditText>(R.id.price)
        val userEdiTextView = findViewById<EditText>(R.id.user)
        textView.setOnClickListener {
            start = startEdiTextView.text.toString()
            customer = userEdiTextView.text.toString()
            destination = destinationEdiTextView.text.toString()
            price = priceEdiTextView.text.toString()
            if (start.isNotEmpty() && destination.isNotEmpty() && customer.isNotEmpty() && price.isNotEmpty()) {
                addFlightBookLog()
            } else {
                Toast.makeText(this, "Enter valid input", Toast.LENGTH_LONG).show()
            }
        }

        button1.setOnClickListener {
            addLogForSignIn()
        }

        button2.setOnClickListener {
            initFirebase()
        }
    }


    private fun initFirebase() {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Event.VIEW_CART, "view cart")
        bundle.putString(FirebaseAnalytics.Param.CONTENT, "Main class")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Booking screen")
        firebaseAnalytics.logEvent("Custom_event_with_pre_define_parameter", bundle)

        Toast.makeText(this, "Event logged", Toast.LENGTH_LONG).show()
    }

    private fun addLogForSignIn() {
        val bundle = Bundle()
        bundle.putString("user", "John")
        bundle.putString("mobile", "9876543210")
        bundle.putString("email", "john@yopmail.com")

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
        Toast.makeText(this, "Event logged", Toast.LENGTH_LONG).show()
    }

    private fun addFlightBookLog() {
        val params = Bundle()
        params.putString("user", customer)
        params.putString("start", start)
        params.putString("destination", destination)
        params.putString("amount", price)
        Log.e("inside", "customer-->" + customer)
        firebaseAnalytics.logEvent("custom_event_for_flight_book", params)
        Toast.makeText(this, "Event logged", Toast.LENGTH_LONG).show()
    }

}