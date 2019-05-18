package com.hirauchi.training.activity

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.hirauchi.training.R
import com.hirauchi.training.common.Constants
import com.hirauchi.training.fragment.AddOrEditRecordFragment
import com.hirauchi.training.model.Record
import org.jetbrains.anko.toast
import java.io.BufferedInputStream
import java.io.FileNotFoundException

class AddOrEditRecordActivity : BaseActivity(), AddOrEditRecordFragment.OnAddOrEditRecordListener {

    private val REQUEST_CODE_PERMISSION_CAMERA = 100
    private val REQUEST_CODE_PERMISSION_STORAGE = 101
    private val REQUEST_CODE_CAMERA = 200

    private val JPG = ".jpg"
    private val MEDIA_TYPE = "image/jpeg"
    private var mAddOrEditRecordFragment = AddOrEditRecordFragment()

    lateinit var mImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.add_or_edit_title)

        val bundle = Bundle()
        bundle.putSerializable(Constants.KEY_TRAINING_ID, intent.getIntExtra(Constants.KEY_TRAINING_ID, 0))
        bundle.putSerializable(Constants.KEY_RECORD, intent.getSerializableExtra(Constants.KEY_RECORD) as? Record)
        mAddOrEditRecordFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.Container, mAddOrEditRecordFragment).commit()
    }

    override fun clickedImage() {
        checkPermission()
    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), REQUEST_CODE_PERMISSION_CAMERA)
        } else if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION_STORAGE)
        } else {
            startActivityForResult(getCameraIntent(), REQUEST_CODE_CAMERA)
        }
    }

    private fun getCameraIntent(): Intent {
        val fileName = System.currentTimeMillis().toString() + JPG

        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, fileName)
        values.put(MediaStore.Images.Media.MIME_TYPE, MEDIA_TYPE)
        mImageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!

        val cameraIntent = Intent()
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri)
        return cameraIntent
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION_CAMERA -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermission()
                } else {
                    toast(getString(R.string.add_or_edit_not_camera_parmission))
                }
            }
            REQUEST_CODE_PERMISSION_STORAGE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(getCameraIntent(), REQUEST_CODE_CAMERA)
                } else {
                    toast(getString(R.string.add_or_edit_not_storage_parmission))
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            changeUriToByte(mImageUri)
            getContentResolver().delete(mImageUri, null, null)
        }
    }

    private fun changeUriToByte(result: Uri) {
        try {
            val stream = contentResolver.openInputStream(result)
            if(stream != null) {
                val state = Environment.getExternalStorageState()

                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    val bitmap = BitmapFactory.decodeStream(BufferedInputStream(stream))
                    onRecieveBitmap(bitmap)
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace();
        }
    }

    private fun onRecieveBitmap(bitmap: Bitmap) {
        mAddOrEditRecordFragment.setImage(bitmap)
    }
}