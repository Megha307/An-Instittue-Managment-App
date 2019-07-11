package com.megha.formulajee


import android.content.Intent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler



class MainActivity : AppCompatActivity() {
    val DELAY_MILLISECONDS = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            val alertDialog = AlertDialog.Builder(this).create() //Read Update
//            alertDialog.setTitle("hi")
//            alertDialog.setMessage("this is my app")
//
//            // alertDialog.setButton("Continue..", DialogInterface.OnClickListener { dialog, which ->
//            // here you can add functions
////            })
//
//            alertDialog.show()
//        }

        val handler =  Handler()
        // handler--> postDelayed(Runnable (Interface), long delayMilliSeconds)

        val run = Runnable {
            val intent = Intent(this, MenuActivity :: class.java)
            startActivity(intent)
            finish()
        }
        handler.postDelayed(run, DELAY_MILLISECONDS)

        }
    }

