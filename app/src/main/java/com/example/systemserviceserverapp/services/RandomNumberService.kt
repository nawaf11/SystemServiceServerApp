package com.example.systemserviceserverapp.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.util.Log
import androidx.core.os.bundleOf
import java.util.Date

private const val TAG = "RandomNumberService56"

class RandomNumberService : Service() {


    companion object {
        const val MESSAGE_REQUEST_GET_RANDOM_NUMBER = 1
    }


    private val mMessenger: Messenger = Messenger(RandomNumberServiceHandler())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand Called")

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {

        Log.d(TAG, "onBind Called, with intent = ${intent.toString()}")

        return mMessenger.binder
    }

    internal class RandomNumberServiceHandler : Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message) {
            Log.d(TAG, "handleMessage Called, with msg.what = ${msg.what}")

            when(msg.what) {

                MESSAGE_REQUEST_GET_RANDOM_NUMBER -> {
                    handleRandomNumberRequest(msg)
                }

            }

        }

        private fun handleRandomNumberRequest(msg : Message?) {
            Log.d(TAG, "handleRandomNumberRequest Called")

            val messageSendRandomNumber = Message.obtain(null, MESSAGE_REQUEST_GET_RANDOM_NUMBER)
            messageSendRandomNumber.data =
                bundleOf(Pair("randomNumberResult", generateRandomNumber()))

            try {
                msg?.replyTo?.send(messageSendRandomNumber)
            } catch (e : RemoteException) {
                e.printStackTrace()
            }

        }

    }

}

private fun generateRandomNumber() : String {
    Log.d(TAG, "generateRandomNumber Called")
    val randomNum = (1 until 99).random()
    val dateTimeText = android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", Date())

    return "$randomNum  created at: $dateTimeText"
}