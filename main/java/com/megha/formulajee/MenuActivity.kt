package com.megha.formulajee

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_menu.*
import android.net.ConnectivityManager


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        menutoolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        menutoolbar.setNavigationOnClickListener { onBackPressed() }
        isOnline()
        objectivecard.setOnClickListener {
            var addintent = Intent(this, StudentDetail::class.java)
            startActivity(addintent)
            finish()
        }

        eventcard.setOnClickListener {
            var smsintent = Intent(this, MessageActivity::class.java)
            startActivity(smsintent)

        }

        videocard.setOnClickListener {
            var aboutintent = Intent(this, AboutActivity::class.java)
            startActivity(aboutintent)

        }

        membercard.setOnClickListener {
            var viewintent = Intent(this, ViewstudentActivity::class.java)
            startActivity(viewintent)

        }

        contactcard.setOnClickListener {
            var videointent = Intent(this, VideoActivity::class.java)
            startActivity(videointent)

        }
        upcomingcard.setOnClickListener {
            var attendintent = Intent(this, AttendanceActivity::class.java)
            startActivity(attendintent)

        }
        uploadcard.setOnClickListener {
            var uploadintent = Intent(this, AdvertActivity::class.java)
            startActivity(uploadintent)

        }

    }

    private fun isOnline(): Boolean {
        val conMgr = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo


        if (netInfo == null || !netInfo.isConnected || !netInfo.isConnected) {


            val builder1 = AlertDialog.Builder(this)
            builder1.setMessage("Please turn On your Internet")
            builder1.setCancelable(true)

            builder1.setPositiveButton(
                    "Ok"
            ) { dialog, id -> startActivityForResult(Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 0); }
            // startActivityForResult( Intent(android.provider.Settings.ACTION_WIFI_SETTINGS),2);
//            builder1.setNegativeButton(
//                    "Cancel"
//            ) { dialog, id -> dialog.cancel() }

            val alert11 = builder1.create()
            alert11.show()
            return false
        }
        return true
    }


}
