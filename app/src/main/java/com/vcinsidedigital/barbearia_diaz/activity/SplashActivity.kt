package com.vcinsidedigital.barbearia_diaz.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.vcinsidedigital.barbearia_diaz.R


class SplashActivity: AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.myLooper()!!).postDelayed({
            if(!isRequestPermissionFileWrite()){
                requestPermissionFileWrite()
            }else {
                finish()
                var i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
        }, 3000)
    }

    private fun requestPermissionFileWrite(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), WRITE_READ_EXTERNAL_STORAGE)
    }

    private fun isRequestPermissionFileWrite():Boolean{
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode === 1000){
            finish()
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object{
        private const val WRITE_READ_EXTERNAL_STORAGE = 1000
    }
}