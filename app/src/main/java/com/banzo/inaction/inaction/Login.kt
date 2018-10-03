package com.banzo.inaction.inaction

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private var editEmail: EditText? = null
    private var editSenha: EditText? = null
    private var btnLogar: Button? = null
    private var btnNovo: Button? = null
    private var txtResetSenha: TextView? = null

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inicializarComponentes()
        eventosClicks()
    }

    private fun eventosClicks() {
        btnNovo!!.setOnClickListener {
            val i = Intent(applicationContext, Cadastro::class.java)
            startActivity(i)
        }
        btnLogar!!.setOnClickListener {
            val email = editEmail!!.text.toString().trim { it <= ' ' }
            val senha = editSenha!!.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                editEmail!!.error = "Digite seu email"
            } else if (senha.isEmpty()) {
                editSenha!!.error = "Digite sua senha"
            } else {
                login(email, senha)
            }
        }
        txtResetSenha!!.setOnClickListener {
            val i = Intent(this@Login, ResetSenha::class.java)
            startActivity(i)
        }
    }

    private fun login(email: String, senha: String) {
        auth!!.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this@Login) { task ->
            if (task.isSuccessful) {
                val i = Intent(this@Login, Perfil::class.java)
                startActivity(i)
            } else {
                alert("email ou senha invalidos")
            }
        }
    }

    private fun alert(s: String) {
        Toast.makeText(this@Login, s, Toast.LENGTH_SHORT).show()
    }

    private fun inicializarComponentes() {

        editEmail = findViewById(R.id.editLoginEmail)
        editSenha = findViewById(R.id.editLoginSenha)
        btnLogar = findViewById(R.id.btnLogar)
        btnNovo = findViewById(R.id.btnNovo)
        txtResetSenha = findViewById(R.id.txtResetSenha)

    }

    override fun onStart() {
        super.onStart()
        auth = Conexao.getFirebaseAuth()
    }
}
