package com.example.projectapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val accountTextView = findViewById<TextView>(R.id.accountTextView)
        val notificationsTextView = findViewById<TextView>(R.id.notificationsTextView)
        val logoutButton = findViewById<Button>(R.id.logoutButton)

        // For simplicity, let's just display a toast message for demonstration

        accountTextView.setOnClickListener {
            // Handle account settings
            showToast("Account settings clicked")
        }

        notificationsTextView.setOnClickListener {
            // Handle notifications settings
            showToast("Notifications settings clicked")
        }

        logoutButton.setOnClickListener {
            // Handle logout
            showToast("Logout clicked")
            navigateToLoginScreen()
        }
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        // Clear the back stack so that the user cannot navigate back to MainActivity
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Finish the current activity
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}