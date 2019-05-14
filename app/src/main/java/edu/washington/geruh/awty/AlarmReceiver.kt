package edu.washington.geruh.awty

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val msg = intent!!.getStringExtra("MSG")
        val num = intent!!.getStringExtra("PHONE_NUMBER")
        val phoneNumber = "(" + num.substring(0, 3) + ") " + num.substring(3, 6) + "-" + num.substring(6)
        val toastMsg = "Texting " + phoneNumber + "\n" + msg
        Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show()
    }
}