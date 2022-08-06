package com.example.greens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Spinner

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTION_BAR)  // Remove the action bar
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()  // Remove the action bar
        setContentView(R.layout.activity_settings)

//        val stateSpinner = findViewById<Spinner>(R.id.state_spinner)
//        ArrayAdapter.createFromResource(this, R.array.states_array,
//            android.R.layout.simple_spinner_dropdown_item).also { adapter ->
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                stateSpinner.adapter = adapter
//        }

    }
}