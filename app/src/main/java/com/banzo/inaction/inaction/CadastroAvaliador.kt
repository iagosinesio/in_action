package com.banzo.inaction.inaction

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth

class CadastroAvaliador : AppCompatActivity() {
    private var editEmail: EditText? = null
    private var editSenha: EditText? = null
    private var btnRegistrar: Button? = null
    private var btnVoltar: Button? = null
    private var auth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        iniciarComponentes()
        eventoClicks()
    }

    private fun eventoClicks() {
        btnVoltar!!.setOnClickListener { finish() }
        btnRegistrar!!.setOnClickListener {
            val email = editEmail!!.text.toString().trim { it <= ' ' }
            val senha = editSenha!!.text.toString().trim { it <= ' ' }
            criarUser(email, senha)
        }

    }

    private fun criarUser(email: String, senha: String) {
        auth!!.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this@CadastroAvaliador) { task ->
                    if (task.isSuccessful) {
                        alert("Usuário cadastrado com sucesso")
                        val i = Intent(this@CadastroAvaliador, Perfil::class.java)
                        startActivity(i)
                        finish()

                    } else {
                        alert("Erro ao cadastrar")
                    }
                }
    }

    private fun alert(msg: String) {
        Toast.makeText(this@CadastroAvaliador, msg, Toast.LENGTH_SHORT).show()
    }

    private fun iniciarComponentes() {
        editEmail = findViewById(R.id.editEmailCadastro)
        editSenha = findViewById(R.id.editSenhaCadastro)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnVoltar = findViewById(R.id.btnVoltar)

    }

    override fun onStart() {
        super.onStart()
        auth = Conexao.getFirebaseAuth()
    }
}
