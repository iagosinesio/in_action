package com.banzo.inaction.inaction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.ListView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var editNome: EditText
    lateinit var editEmail: EditText
    lateinit var listV_dados: ListView
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editNome = findViewById(R.id.editNome)
        editEmail = findViewById(R.id.editEmail)
        listV_dados = findViewById(R.id.listV_dados)

        inicializarFirebase()

    }

    private fun inicializarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference
    }
// o codigo abaixo cria o menu na tela principal do projeto. Esse meno foi criado com icones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        lateinit var p: Pessoa
        var id: Int
        id = item!!.itemId
        if (id == R.id.menu_novo){
            p.uid = (UUID.randomUUID().toString())
            p.nome = (editNome.text.toString())
            p.email = (editEmail.text.toString())
            databaseReference.child("Pessoa").child(p.uid).setValue(p)
            limparCampos()
        }
        return true
    }

    private fun limparCampos() {
        editEmail.setText("")
        editNome.setTag("")
    }
}
