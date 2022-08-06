package com.example.greens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : Activity() {
    // Declare timestamp variables
    private var shiftStartTime : Long = -1
    private var totalShiftTimes : LinkedList<Long> = LinkedList()

    private var payPeriodStart : String = ""
    private var payPeriods : LinkedList<Pair<String, String>> = LinkedList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle clicked shift button
        val shiftButton: Button = findViewById<Button>(R.id.shift_button)  // Get the shift button
        shiftButton.setOnClickListener {
            shiftClicked(shiftButton)
        }

        // Handle clicked pay period button
        val payPeriodButton: Button = findViewById<Button>(R.id.pay_period_button)  // Get the pay period button
        payPeriodButton.setOnClickListener {
            payPeriodClicked(payPeriodButton)
        }

        // Settings menu button
        val settingsButton: ImageView = findViewById<ImageView>(R.id.settingsButton)  // Get the settings button
        settingsButton.setOnClickListener {
//            Settings()
            startActivity(Intent(this@MainActivity, Settings::class.java))
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

    private fun payPeriodClicked(button: Button) {
        val currentText = button.text.toString()
        val tField = findViewById<TextView>(R.id.earned_income_text)
        if(currentText == getString(R.string.start_pay_period)) {
            // Start a pay period
            val formatter = SimpleDateFormat("MM-dd-yyyy", Locale.US)
            payPeriodStart = formatter.format(Date())

            val month: String = String.format("%c%c", payPeriodStart[0], payPeriodStart[1])
            val day: String = String.format("%c%c", payPeriodStart[3], payPeriodStart[4])
            val newStr: String = getString(R.string.earned_income_since, month, day)

            tField.text = newStr
            button.setText(R.string.end_pay_period)
        } else {
            // End a pay period
            val formatter = SimpleDateFormat("MM-dd-yyyy", Locale.US)
            val payPeriodEnd = formatter.format(Date())
            val payPeriod = Pair(payPeriodStart, payPeriodEnd)
            payPeriods.add(payPeriod)

            tField.setText(R.string.no_start_date_set)
            button.setText(R.string.start_pay_period)
        }
    }
}