package com.banzo.inaction.inaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {
    private TextView txtEmailPerfil, txtIdPerfil;
    private Button btnLogout;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        inicializarComponentes();
        eventoClick();
    }

    private void eventoClick() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logOut();
                finish();
            }
        });
    }

    private void inicializarComponentes() {
        txtEmailPerfil = findViewById(R.id.txtEmailPerfil);
        txtIdPerfil = findViewById(R.id.txtIdPerfil);
        btnLogout = findViewById(R.id.btnLogout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificarUser();
    }

    private void verificarUser() {
        if (user == null){
            finish();
        }else {
            txtEmailPerfil.setText("Email: "+ user.getEmail());
            txtIdPerfil.setText("ID: "+ user.getUid());        }
    }
}
