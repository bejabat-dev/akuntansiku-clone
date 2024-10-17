package com.example.moliyafinance.objects

import android.content.Context
import com.example.moliyafinance.Utils
import com.example.moliyafinance.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object User {
    var userData = User("", "", "", "")
    fun getUserData(
        context: Context, onResult: (User?) -> Unit
    ) {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance().collection("Users")
        db.document(uid)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val user = querySnapshot?.toObject(User::class.java)
                onResult(user)
            }
            .addOnFailureListener { e ->
                Utils().showToast(context, e.toString())
            }
    }
}