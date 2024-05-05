package com.example.projectapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
//import android.app.AlarmManager
//import android.app.PendingIntent
//import android.content.Context
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var remindersList: ArrayList<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var editText: EditText
    private lateinit var listView: ListView
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText

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
        dateEditText = findViewById(R.id.dateEditText)
        timeEditText = findViewById(R.id.timeEditText)

        val addButton = findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            val reminder = editText.text.toString().trim()
            val date = dateEditText.text.toString().trim()
            val time = timeEditText.text.toString().trim()

            if (reminder.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
                // Concatenate reminder text, date, and time
                val reminderWithDateTime = "$reminder - Date: $date, Time: $time"
                remindersList.add(reminderWithDateTime)
                arrayAdapter.notifyDataSetChanged()
                editText.text.clear()
                dateEditText.text.clear()
                timeEditText.text.clear()

                /*scheduleReminder(reminderWithDateTime)*/
            } else {
                Toast.makeText(this, "Please enter a reminder, date, and time", Toast.LENGTH_SHORT).show()
            }
        }

        // Set click listeners for date and time EditText fields
        dateEditText.setOnClickListener {
            showDatePicker()
        }

        timeEditText.setOnClickListener {
            showTimePicker()
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
    fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Set selected date to dateEditText
                dateEditText.setText("$year-${month + 1}-$dayOfMonth")
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                // Set selected time to timeEditText
                timeEditText.setText("$hourOfDay:$minute")
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    /*private fun scheduleReminder(reminderText: String) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ReminderBroadcastReceiver::class.java)
        intent.putExtra("reminderText", reminderText)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val calendar = Calendar.getInstance()
        // Set the reminder time (10 seconds after the current time)
        calendar.add(Calendar.SECOND, 10)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
    }*/
}
