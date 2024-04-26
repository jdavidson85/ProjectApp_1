package com.example.projectapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var remindersList: ArrayList<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var editText: EditText
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
        deleteButton = findViewById(R.id.deleteButton)
        editText = findViewById(R.id.editText)
        listView = findViewById(R.id.listView)
        val settingsButton = findViewById<Button>(R.id.settingsButton)
        remindersList = ArrayList()
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, remindersList)
        listView.adapter = arrayAdapter

        addButton.setOnClickListener {
            val reminder = editText.text.toString().trim()
            if (reminder.isNotEmpty()) {
                remindersList.add(reminder)
                arrayAdapter.notifyDataSetChanged()
                editText.text.clear()
            } else {
                Toast.makeText(this, "Please enter a reminder", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            val position = listView.checkedItemPosition
            if (position != ListView.INVALID_POSITION) {
                remindersList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Reminder deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a reminder to delete", Toast.LENGTH_SHORT).show()
            }
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            // Remove the item from the list when long-clicked
            remindersList.removeAt(position)
            arrayAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Reminder deleted", Toast.LENGTH_SHORT).show()
            true // Return true to consume the long click event
        }

        settingsButton.setOnClickListener {
            // Launch SettingsActivity when the settingsButton is clicked
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


    }
}