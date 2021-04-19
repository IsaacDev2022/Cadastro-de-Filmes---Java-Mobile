package com.cadastrofilmes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listar(View view) {
        Intent intent = new Intent(getApplicationContext(), ListaFilmes.class);
        startActivity(intent);
    }

    public void cadastrar(View view) {
        Intent intent = new Intent(getApplicationContext(), FormularioCadastro.class);
        startActivity(intent);
    }
}