package com.cadastrofilmes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioCadastro extends AppCompatActivity {
    private EditText nomeFilme;
    private EditText anoLancamento;
    private EditText genero;
    private FilmesDAO dao;
    private Filme filme = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        nomeFilme = findViewById(R.id.edit_nomeFilme);
        anoLancamento = findViewById(R.id.edit_anoLancamento);
        genero = findViewById(R.id.edit_genero);
        dao = new FilmesDAO(this);

        Intent intent = getIntent();

        if (intent.hasExtra("filme")) {
            filme = (Filme)intent.getSerializableExtra("filme");
            nomeFilme.setText(filme.getNomeFilme());
            anoLancamento.setText(filme.getAnoLancamento());
            genero.setText(filme.getGenero());
        }
    }

    public void salvar(View view) {
        if (filme == null) {
            filme = new Filme();
            filme.setNomeFilme(nomeFilme.getText().toString());
            filme.setAnoLancamento(anoLancamento.getText().toString());
            filme.setGenero(genero.getText().toString());
            long id = dao.inserir(filme);
            Toast.makeText(this, "Filme inserido com sucesso!, id: " + id, Toast.LENGTH_SHORT).show();
        }

        else {
            filme.setNomeFilme(nomeFilme.getText().toString());
            filme.setAnoLancamento(anoLancamento.getText().toString());
            filme.setGenero(genero.getText().toString());
            dao.atualizar(filme);
            Toast.makeText(this, "Os dados do filme foram atualizados com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    public void listar(View view) {
        Intent intent = new Intent(getApplicationContext(), ListaFilmes.class);
        startActivity(intent);
    }
}