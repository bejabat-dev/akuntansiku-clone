package com.example.moliyafinance.models

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object User {
    var userData = UserModel("", "", "")
    fun getUserData(
        context: Context, onResult: (UserModel?) -> Unit
    ) {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance().collection("Users")
        db.document(uid)// Limit to 1 document
            .get()
            .addOnSuccessListener { querySnapshot ->
                val user = querySnapshot?.toObject(UserModel::class.java)
                onResult(user)
            }
            .addOnFailureListener { e ->
                showToast(context, e.toString())
            }
    }
}

data class UserModel(
    val nama: String = "",
    val email: String = "",
    val nohp: String = ""
)