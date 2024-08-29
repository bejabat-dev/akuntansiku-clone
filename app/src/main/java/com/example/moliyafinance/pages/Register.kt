package com.example.moliyafinance.pages

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityRegisterBinding
import com.example.moliyafinance.models.showToast
import com.example.moliyafinance.navigation.Dashboard
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var bind: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initClicks()
    }

    private fun initClicks() {
        bind.register.setOnClickListener {
            val nama = bind.nama.text.toString()
            val email = bind.email.text.toString()
            val password = bind.password.text.toString()
            val repassword = bind.repassword.text.toString()
            if (email.isBlank() || password.isBlank() || repassword.isBlank() || nama.isBlank()) {
                showToast(this, "Harap penuhi semua kolom")
            } else {
                if (password.trim() != repassword.trim()) {
                    showToast(this, "Kata sandi tidak sama")
                } else {
                    register(email, password, nama)
                }
            }
        }
    }

    private fun register(email: String, password: String, nama: String) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            if (it.user != null) {
                val i = Intent(this, Dashboard::class.java)
                startActivity(i)
                finish()
            }
        }
    }

}