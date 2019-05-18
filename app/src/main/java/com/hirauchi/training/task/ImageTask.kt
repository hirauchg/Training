package com.hirauchi.training.task

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.LruCache
import android.widget.ImageView
import com.hirauchi.training.R
import org.jetbrains.anko.imageBitmap
import java.lang.ref.WeakReference

class ImageTask(val mImageView: WeakReference<ImageView>, val bitmapCache: BitmapCache) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg params: String): Bitmap? {

        return bitmapCache.getBitmap(params[0])?.also {
            return it
        } ?: run {
            val ops = BitmapFactory.Options()
            ops.inSampleSize = 8
            val bitmap = BitmapFactory.decodeFile(params[0], ops)
            return bitmap?.let {
                bitmapCache.putBitmap(params[0], bitmap)
                it
            }
        }

    }

    override fun onPostExecute(result: Bitmap?) {
        result?.let {
            mImageView.get()?.imageBitmap = it
        } ?: mImageView.get()?.setImageResource(R.drawable.file_not_exist)
    }
}

class BitmapCache {

    var mMemoryCache: LruCache<String, Bitmap>

    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8

        mMemoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return bitmap.rowBytes * bitmap.height / 1024
            }
        }
    }

    fun getBitmap(filePath: String): Bitmap? {
        return mMemoryCache.get(filePath)
    }

    fun putBitmap(filePath: String, bitmap: Bitmap) {
        val old = mMemoryCache.put(filePath, bitmap)
        if (old != null) {
            if (old.isRecycled()) {
                old.recycle()
            }
        }
    }
}