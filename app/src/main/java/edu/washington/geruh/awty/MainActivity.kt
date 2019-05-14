package edu.washington.geruh.awty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val message = findViewById<EditText>(R.id.message)
        val timeDelay = findViewById<EditText>(R.id.minutes)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val startButton = findViewById<Button>(R.id.begin)
        val alarm: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        startButton.setOnClickListener {
            if (startButton.text.toString().toLowerCase() == "start") {
                if (phoneNumber.text.toString().isEmpty() && phoneNumber.text.toString().length < 10) {
                    Toast.makeText(this, "Please provide a valid Phone Number", Toast.LENGTH_SHORT).show()
                } else if (timeDelay.text.toString().toFloat() < 0) {
                    Toast.makeText(this, "Please provide a positive value in minutes", Toast.LENGTH_SHORT).show()
                } else if (message.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please Provide a message to send", Toast.LENGTH_SHORT).show()
                } else {
                    startButton.text = "Stop"
                    val timeInterval = timeDelay.text.toString().toLong() * 60000
                    intent.putExtra("MSG", message.text.toString())
                    intent.putExtra("PHONE_NUMBER", phoneNumber.text.toString())
                    val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
                    alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), timeInterval, pendingIntent)
                }
            } else {
                startButton.text = "Start"
                val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0,intent,PendingIntent.FLAG_CANCEL_CURRENT)
                alarm.cancel(pendingIntent)
                Toast.makeText(this, "The service has been stopped", Toast.LENGTH_LONG).show()
            }
        }

    }
}
