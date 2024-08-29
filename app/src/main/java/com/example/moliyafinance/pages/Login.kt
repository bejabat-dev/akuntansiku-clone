package com.example.moliyafinance.pages

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityLoginBinding
import com.example.moliyafinance.models.LoadingDialog
import com.example.moliyafinance.models.showToast
import com.example.moliyafinance.navigation.Dashboard
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var bind: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initClicks()
    }

    private fun initClicks() {
        bind.login.setOnClickListener {
            val email = bind.email.text.toString()
            val password = bind.password.text.toString()
            if (email.isBlank() || password.isBlank()) {
                showToast(this, "Harap penuhi semua kolom")
            } else {
                login(email,password)
            }
        }
        bind.register.setOnClickListener {
            val i = Intent(this, Register::class.java)
            startActivity(i)
        }
    }

    private fun login(email: String, password: String) {
        LoadingDialog.showDialog(this,"Sedang masuk")
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            if (it.user != null) {
                val i = Intent(this, Dashboard::class.java)
                startActivity(i)
                finish()
            }
        }.addOnFailureListener { e ->
            run {
                LoadingDialog.dialog.dismiss()
                showToast(this, e.toString())
            }
        }
    }
}