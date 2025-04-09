package com.example.kolos.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(): ViewModel() {
    var auth = Firebase.auth

    fun signIn(email: String, password: String, auth: FirebaseAuth, navController: NavController, context: Context){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Log.d("MyLog", "Sign In Successful!")
                    navController.navigate("main")
                } else {
                    Log.d("MyLog", "Sign In Failed")
                    Toast.makeText(
                        context,
                        "Sign In Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun signUp(email: String, password: String, auth: FirebaseAuth, navController: NavController, context: Context){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Log.d("MyLog", "Sign Up Successful!")
                    navController.navigate("main")
                } else {
                    Log.d("MyLog", "Sign Up Failed")
                    Toast.makeText(
                        context,
                        "Sign Up Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    fun signOut(
        auth: FirebaseAuth,
    ){
        auth.signOut()
    }

    fun deleteUser(auth: FirebaseAuth, email: String, password: String){
//        auth.currentUser?.delete()
        val credential = EmailAuthProvider.getCredential(email, password)
        auth.currentUser?.reauthenticate(credential)?.addOnCompleteListener {
            if(it.isSuccessful){
                auth.currentUser?.delete()?.addOnCompleteListener {
                    if(it.isSuccessful){
                        Log.d("MyLog", "Account was deleted!")
                    } else {
                        Log.d("MyLog", "Error: Account is not deleted")
                    }

                }
            } else{
                Log.d("MyLog", "Reautenticate error!")
            }
        }
    }

    fun updatePassword(auth: FirebaseAuth, email: String, password: String, newPassword: String){
        val credential = EmailAuthProvider.getCredential(email, password)
        auth.currentUser?.reauthenticate(credential)?.addOnCompleteListener { task ->
            if(task.isSuccessful){
                auth.currentUser?.updatePassword(newPassword)
            } else {
                Log.d("MyLog", "reauthenticate failed")
            }
        }
    }


}