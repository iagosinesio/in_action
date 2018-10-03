package com.banzo.inaction.inaction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Perfil : AppCompatActivity() {
    private var txtEmailPerfil: TextView? = null
    private var txtIdPerfil: TextView? = null
    private var btnLogout: Button? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        inicializarComponentes()
        eventoClick()
    }

    private fun eventoClick() {
        btnLogout!!.setOnClickListener {
            Conexao.logOut()
            finish()
        }
    }

    private fun inicializarComponentes() {
        txtEmailPerfil = findViewById(R.id.txtEmailPerfil)
        txtIdPerfil = findViewById(R.id.txtIdPerfil)
        btnLogout = findViewById(R.id.btnLogout)
    }

    override fun onStart() {
        super.onStart()
        auth = Conexao.getFirebaseAuth()
        user = Conexao.firebaseUser
        verificarUser()
    }

    private fun verificarUser() {
        if (user == null) {
            finish()
        } else {
            txtEmailPerfil!!.text = "Email: " + user!!.email!!
            txtIdPerfil!!.text = "ID: " + user!!.uid
        }
    }
}
