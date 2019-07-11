package com.megha.formulajee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView



class StudentAdapter(val mctx: Context,val layoutresId:Int,val StudentList:List<Student> )
    :ArrayAdapter<Student>(mctx,layoutresId,StudentList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflator:LayoutInflater=LayoutInflater.from(mctx)
        val view:View=layoutinflator.inflate(layoutresId,null)

        val textname=view.findViewById<TextView>(R.id.textname)
        val textbatch=view.findViewById<TextView>(R.id.textbatch)
        val textschool=view.findViewById<TextView>(R.id.textschoolname)
        val fathername=view.findViewById<TextView>(R.id.textfathername)
        val fathercontact=view.findViewById<TextView>(R.id.textfathercontact)
        val mothername=view.findViewById<TextView>(R.id.textmothername)
        val mothercontact=view.findViewById<TextView>(R.id.textmothercontact)
        val address=view.findViewById<TextView>(R.id.textaddress)
       // val sms=view.findViewById<TextView>(R.id.textsms)

        val student=StudentList[position]

        textname.text=student.name
        textbatch.text=student.batch
        textschool.text=student.schoolname
        fathername.text=student.fathername
        fathercontact.text=student.fathercontact
        mothername.text=student.mothername
        mothercontact.text=student.mothercontact
        address.text=student.address
      //  sms.text=student.radiogrp.toString()

        return view

    }
}