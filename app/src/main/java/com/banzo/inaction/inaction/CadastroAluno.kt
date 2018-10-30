package com.banzo.inaction.inaction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cadastro_aluno.*

class CadastroAluno : AppCompatActivity() {


    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    private var btnSalvarAluno: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_aluno)
        iniciarComponentes()
        salvarAluno()
        inicializarFirebase()
    }

    private fun inicializarFirebase() {

        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
//        firebaseDatabase.setPersistenceEnabled(true)
        databaseReference = firebaseDatabase.reference

    }

    private fun iniciarComponentes() {
//        btnSalvarAluno = findViewById(R.id.btnSalvarAluno)
    }

    private fun salvarAluno() {
//        btnSalvarAluno!!.setOnClickListener{
//
//
//        }

    }

}
