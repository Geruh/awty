package edu.washington.geruh.awty

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.telephony.SmsManager
import android.widget.Toast
import java.util.jar.Manifest

class AlarmReceiver : BroadcastReceiver() {

    var contextAll: Context? = null
    var intentAll: Intent? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        contextAll = context
        intentAll = intent
        val msg = intent!!.getStringExtra("MSG")
        val num = intent!!.getStringExtra("PHONE_NUMBER")
        val phoneNumber = "(" + num.substring(0, 3) + ") " + num.substring(3, 6) + "-" + num.substring(6)
        val toastMsg = "Texting " + phoneNumber + ":\n" + msg
        Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show()
        composeSmsMessage(msg, num, context)
    }

    fun composeSmsMessage(message: String, number: String, activity: Context?) {
//        if(ContextCompat.checkSelfPermission(contextAll!!, android.Manifest.permission.SEND_SMS)
//            != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(contextAll as MainActivity, arrayOf(android.Manifest.permission.SEND_SMS), 1)
//        } else {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(
                number,
                null,
                message,
                null,
                null
            )

    }
}