package com.megha.formulajee

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_message.*
import com.katepratik.msg91api.MSG91
import android.provider.ContactsContract
import android.content.Intent
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import android.content.DialogInterface
import android.support.v7.app.AlertDialog


@Suppress("DEPRECATION")
class MessageActivity : AppCompatActivity() {

var PICK_CONTACT=1
    var MY_PERMISSIONS_REQUEST_READ_CONTACTS=2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        messagetoolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        messagetoolbar.setNavigationOnClickListener { onBackPressed() }




        pickcontact.setOnClickListener {

                val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                startActivityForResult(intent, PICK_CONTACT)
            }



            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "You have already granted this permission!",
                        Toast.LENGTH_SHORT).show()
            } else {
                requestStoragePermission()
            }







        send.setOnClickListener {

            val msg91 = MSG91("277627Ag1oF3K07qm5ce3a410")
           val msg91Debug = MSG91("277627Ag1oF3K07qm5ce3a410", true)

            val validate = msg91Debug.validate()
            val message=messagetext.text
val contactnum=number.text
           val balanceTransactional = msg91.getBalance("4")
            msg91.composeMessage("FORMUL", "${message}")
            msg91.to("${contactnum}")
           var sendStatus = msg91.send()

//7048942252
//fjmpst2019



          //  val mobileNumbers = ArrayList<String>()








//     mobileNumbers.add("${mothercontacts}")
     //mobileNumbers.add("9811632539")
//     mobileNumbers.add("1234567890")
//     mobileNumbers.add("1234567890")
//     msg91.composeMessage("ABCDEF", "Your Message")
    //msg91.to(mobileNumbers)
     //var sendStatus = msg91.send()

        }



    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PICK_CONTACT)  {
            if (grantResults.size> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {

            AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to send SMS to your contacts")
                    .setPositiveButton("ok", DialogInterface.OnClickListener { _, _ ->
                        ActivityCompat.requestPermissions(this,
                                arrayOf(Manifest.permission.READ_CONTACTS), PICK_CONTACT)
                    })
                    .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
                    .create().show()

        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS), PICK_CONTACT)
        }
    }


    public override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)

        when (reqCode) {
            PICK_CONTACT -> if (resultCode == Activity.RESULT_OK) {
                val contactData = data!!.data
                val c = managedQuery(contactData, null, null, null, null)
                if (c.moveToFirst()) {
                    val id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID))

                    val hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                    if (hasPhone.equals("1", ignoreCase = true)) {
                        val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null)
                        phones!!.moveToFirst()
                        val cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        Toast.makeText(getApplicationContext(), cNumber, Toast.LENGTH_SHORT).show();
                        number.text=cNumber
                       // setCn(cNumber)
                    }

                }
            }
        }
    }
}

//     for(j in 1..mobileNumbers.size){
////         var numbercontact=student.mothercontact
//         numbercontact[j]
//     }
