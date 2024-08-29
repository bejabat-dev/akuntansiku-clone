package com.example.moliyafinance.pages

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.R
import com.example.moliyafinance.models.User
import com.example.moliyafinance.navigation.Dashboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.core.UserData

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            val i = Intent(this,Dashboard::class.java)
            startActivity(i)
            finish()
        }else{
            val i = Intent(this,Login::class.java)
            startActivity(i)
            finish()
        }
    }
}