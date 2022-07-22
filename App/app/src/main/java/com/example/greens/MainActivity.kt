package com.example.greens

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import java.util.*


class MainActivity : Activity() {
    // Declare timestamp lists
    private var shiftStartTime : Long = -1
    private var totalShiftTimes : LinkedList<Long> = LinkedList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle clicked shift button
        val shiftButton: Button = findViewById<Button>(R.id.shift_button)  // Get the shift button
        shiftButton.setOnClickListener {
            shiftClicked(shiftButton)
        }
    }

    // Shift button clicked handler
    private fun shiftClicked(button: Button) {
        // Code here executes on main thread after user presses shift button
        val currentText = button.text.toString()
        val currentTime = System.currentTimeMillis()
        if(currentText == getString(R.string.start_shift)) {
            // Start a shift
            shiftStartTime = System.currentTimeMillis()  // Generate the shift start time
            button.setText(R.string.end_shift)  // Change button text
            println("Shift started at $currentTime")
        } else {
            // Start a shift
            val totalShiftTime = currentTime - shiftStartTime  // Calculate this shift's total time
            shiftStartTime = -1  // Reset shift start time
            totalShiftTimes.add(totalShiftTime)  // Add to list of shift times for this pay period
            button.setText(R.string.start_shift)
            println("Shift ended at $currentTime")
        }
    }
}