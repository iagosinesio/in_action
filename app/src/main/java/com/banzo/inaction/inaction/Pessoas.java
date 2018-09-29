package com.banzo.inaction.inaction;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pessoas {
    private String uid;
    private String nome;
    private String email;

    private List<Pessoa> listPessoas = new ArrayList<>();
    private ArrayAdapter<Pessoa> pessoaArrayAdapter;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome;
    }


}
