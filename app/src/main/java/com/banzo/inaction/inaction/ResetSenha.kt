package com.banzo.inaction.inaction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetSenha : AppCompatActivity() {
    private var editResetEmail: EditText? = null
    private var btnResetSenha: Button? = null

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_senha)
        iniciarComponentes()
        eventoClick()
    }

    private fun eventoClick() {
        btnResetSenha!!.setOnClickListener {
            val email = editResetEmail!!.text.toString().trim { it <= ' ' }
            resetSenha(email)
        }
    }

    private fun resetSenha(email: String) {
        auth!!.sendPasswordResetEmail(email)
                .addOnCompleteListener(this@ResetSenha) { task ->
                    if (task.isSuccessful) {
                        alert("Um email foi enviado para alterar a senha")
                        finish()
                    } else {
                        alert("Email não encontrado")
                    }
                }
    }

    private fun alert(s: String) {
        Toast.makeText(this@ResetSenha, s, Toast.LENGTH_SHORT).show()
    }

    private fun iniciarComponentes() {
        editResetEmail = findViewById(R.id.editResetEmail)
        btnResetSenha = findViewById(R.id.btnResetSenha)

    }

    override fun onStart() {
        super.onStart()
        auth = Conexao.getFirebaseAuth()
    }
}
