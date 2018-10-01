package com.banzo.inaction.inaction;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText editEmail, editSenha;
    private Button btnLogar, btnNovo;
    private TextView txtResetSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarComponentes();
        eventosClicks();
    }

    private void eventosClicks() {
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(i);
            }
        });
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                if (email.isEmpty()){
                    editEmail.setError("Digite seu email");
                }else if(senha.isEmpty()){
                    editSenha.setError("Digite sua senha");
                }else{
                    login(email, senha);
                }

            }
        });
        txtResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ResetSenha.class);
                startActivity(i);
            }
        });
    }

    private void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i = new Intent(Login.this, Perfil.class);
                    startActivity(i);
                }else {
                    alert("email ou senha invalidos");
                }
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
    }

    private void inicializarComponentes() {

        editEmail = findViewById(R.id.editLoginEmail);
        editSenha = findViewById(R.id.editLoginSenha);
        btnLogar = findViewById(R.id.btnLogar);
        btnNovo = findViewById(R.id.btnNovo);
        txtResetSenha = findViewById(R.id.txtResetSenha);
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
