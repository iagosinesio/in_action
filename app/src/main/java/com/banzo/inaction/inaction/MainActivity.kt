package com.banzo.inaction.inaction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
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
    lateinit var pessoaSelecionada: Pessoa
    lateinit var adapterPessoas: ArrayAdapter<Pessoas>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editNome = findViewById(R.id.editNome)
        editEmail = findViewById(R.id.editEmail)
        listV_dados = findViewById(R.id.listV_dados)
        listPessoas = mutableListOf()
        adapterPessoas = ArrayAdapter<Pessoas>(this@MainActivity,
                android.R.layout.simple_list_item_1, listPessoas)


        inicializarFirebase()
        eventoDatabase()


        listV_dados.setOnItemClickListener{parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            pessoaSelecionada = parent?.getItemAtPosition(position) as Pessoa
            editNome.setText(pessoaSelecionada.nome)
            editEmail.setText(pessoaSelecionada.email)

        }

        listV_dados.setOnItemLongClickListener{adapterView: AdapterView<*>, view: View, position: Int, id: Long->
            val item = adapterPessoas.getItem(position)
            Toast.makeText(this, "Me pegou safado", Toast.LENGTH_LONG).show()
            true
        }
    }

    private fun eventoDatabase() {
        databaseReference.child("Pessoa").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                listPessoas.clear()
                for (h in p0.children){
                    val pessoa = h.getValue(Pessoas::class.java)
                    listPessoas.add(pessoa!!)
                }

                listV_dados.adapter = adapterPessoas


            }
        } )
    }

    private fun inicializarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.setPersistenceEnabled(true)
        databaseReference = firebaseDatabase.reference
    }
// o codigo abaixo cria o menu na tela principal do projeto. Esse meno foi criado com icones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId
        if (id == R.id.menu_novo){
            val p = Pessoa()
            p.uid=(UUID.randomUUID().toString())
            p.nome = (editNome.text.toString())
            p.email = (editEmail.text.toString())
            databaseReference.child("Pessoa").child(p.uid).setValue(p)
            limparCampos()
            return true
        }else if (id == R.id.menu_update){
            val pessoa = Pessoa()
            pessoa.uid = (pessoaSelecionada.uid)
            pessoa.email = (editEmail.text.toString().trim())
            pessoa.nome = (editNome.text.toString().trim())
            databaseReference.child("Pessoa").child(pessoa.uid).setValue(pessoa)
            limparCampos()
            return true
        }else if (id == R.id.menu_delete){
            val pessoa = Pessoa()
            pessoa.uid = (pessoaSelecionada.uid)
            databaseReference.child("Pessoa").child(pessoa.uid).removeValue()
            limparCampos()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun limparCampos() {
        editEmail.setText("")
        editNome.setText("")
    }
}
