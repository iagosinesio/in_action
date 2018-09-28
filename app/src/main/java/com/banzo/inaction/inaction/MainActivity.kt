package com.banzo.inaction.inaction

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var editNome: EditText
    lateinit var editEmail: EditText
    lateinit var listV_dados: ListView
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var listPessoas: MutableList<Pessoas>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editNome = findViewById(R.id.editNome)
        editEmail = findViewById(R.id.editEmail)
        listV_dados = findViewById(R.id.listV_dados)
        listPessoas = mutableListOf()
        val pessoaArrayAdapter = ArrayAdapter<Pessoas>
        inicializarFirebase()
        eventoDatabase()

    }

    private fun eventoDatabase() {
        databaseReference.child("").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    for (h in p0.children){
                        val pessoa = h.getValue(Pessoas::class.java)
                        listPessoas.add(pessoa!!)
                    }
                    val
                }
            }
        } )
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

        var id: Int
        id = item!!.itemId
        if (id == R.id.menu_novo){
            var p = Pessoas()
            p.uid=(UUID.randomUUID().toString())
            p.nome = (editNome.text.toString())
            p.email = (editEmail.text.toString())
            databaseReference.child("Pessoa").child(p.uid).setValue(p)
            limparCampos()
        }
        return true
    }

    private fun limparCampos() {
        editEmail.setText("")
        editNome.setText("")
    }
}
