package com.megha.formulajee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_attendance.*


class AttendanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        attendancetoolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        attendancetoolbar.setNavigationOnClickListener { onBackPressed() }

//        attendradio.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
//          //  radioButton  = findViewById<View>(checkedId) as RadioButton
//            Toast.makeText(baseContext, yes.getText(), Toast.LENGTH_SHORT).show()
//        }
//        )






    }
}
