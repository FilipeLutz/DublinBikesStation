package com.stu25956.dublinbikesstation.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

// Helper class for Firebase authentication
object AuthHelper {
    // Firebase authentication instance
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    // Function to sign up a user with email and password
    suspend fun signUp(email: String, password: String): String? {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            null
        } catch (e: Exception) {
            e.message
        }
    }
    // Function to log in a user with email and password
    suspend fun logIn(email: String, password: String): String? {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            null
        } catch (e: Exception) {
            e.message
        }
    }
    // Function to log out a user
    fun logOut() {
        firebaseAuth.signOut()
    }
    // Function to check if a user is logged in
    fun isUserLoggedIn() = firebaseAuth.currentUser != null
    //
    suspend fun sendPasswordResetEmail(email: String): String? {
        return try {
            // Send password reset email
            firebaseAuth.sendPasswordResetEmail(email).await()
            null
            // Catch any exceptions
        } catch (e: Exception) {
            e.message
        }
    }
}