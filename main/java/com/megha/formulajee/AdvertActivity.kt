package com.megha.formulajee

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.webkit.MimeTypeMap
import android.widget.Toast
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_advert.*
//import android.support.test.orchestrator.junit.BundleJUnitUtils.getResult
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import com.google.android.gms.tasks.Task


class AdvertActivity : AppCompatActivity() {

    var pick_image_request = 1;
    var mstorageref: StorageReference? = null
    var mdataref: DatabaseReference? = null
    var mimageuri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert)

        adverttoolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        adverttoolbar.setNavigationOnClickListener { onBackPressed() }
        mstorageref = FirebaseStorage.getInstance().getReference("uploads")
        mdataref = FirebaseDatabase.getInstance().getReference("uploads")
        button_choose.setOnClickListener {
            openFileChooser()
        }
        button_upload.setOnClickListener {
            uploadfile()

        }
        tvupload.setOnClickListener {
            var imageitemintent = Intent(this, GetImageActivity::class.java)
            startActivity(imageitemintent)
        }


    }

    private fun openFileChooser() {
        var intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, pick_image_request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == pick_image_request && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            mimageuri = data.data
            Picasso.with(this).load(mimageuri).into(advert_image)
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    fun getfileExtension(uri: Uri): String? {
        var contentres = getContentResolver()
        var mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(contentres.getType(uri))
    }

    fun uploadfile() {
        if (mimageuri != null) {

            var fileref = mstorageref!!.child("${System.currentTimeMillis()}.${getfileExtension(mimageuri!!)}")
            fileref.putFile(mimageuri!!)
                    .addOnSuccessListener {
                        var handler = Handler()
                        var run = Runnable {
                            progress_bar.progress = 0
                        }
                        handler.postDelayed(run, 500)

                        Toast.makeText(this, "Uploaded Successfully", Toast.LENGTH_SHORT).show()

//                        val urlTask = it.getStorage().getDownloadUrl()
//                        while (!urlTask.isSuccessful());
//                        val downloadUrl = urlTask.getResult()
//                        val sdownload_url = downloadUrl.toString()


//                        val upload = Upload(mEditTextFileName.getText().toString().trim(),
//                                taskSnapshot.getDownloadUrl().toString())


                        var upload = Upload(et_filename.text.trim().toString(), it.downloadUrl.toString())
                        var uploadid = mdataref!!.push().key.toString()
                        mdataref!!.child(uploadid).setValue(upload)

                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener {
                        var progress = (100 * it.bytesTransferred / it.totalByteCount)
                        progress_bar.progress = progress.toInt()
                    }
        } else {
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show()
        }
    }
}
