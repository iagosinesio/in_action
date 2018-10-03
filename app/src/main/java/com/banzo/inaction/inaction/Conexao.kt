package com.banzo.inaction.inaction

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object Conexao {
    private var firebaseAuth: FirebaseAuth? = null
    private var authStateListener: FirebaseAuth.AuthStateListener? = null
    var firebaseUser: FirebaseUser? = null
        private set

    fun getFirebaseAuth(): FirebaseAuth? {
        if (firebaseAuth == null) {
            inicializarFirebaseAuth()
        }
        return firebaseAuth
    }

    private fun inicializarFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                firebaseUser = user

            }
        }
        firebaseAuth!!.addAuthStateListener(authStateListener!!)
    }

    fun logOut() {
        firebaseAuth!!.signOut()
    }
}
