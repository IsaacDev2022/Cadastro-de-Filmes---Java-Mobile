package com.cadastrofilmes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListaFilmes extends AppCompatActivity {
    private ListView listView;
    private FilmesDAO dao;
    private List<Filme> filmes;
    private List<Filme> filmesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        listView = findViewById(R.id.lista_filmes);
        dao = new FilmesDAO(this);
        filmes = dao.obterTodos();
        filmesFiltrados.addAll(filmes);
        FilmeAdapter adaptador = new FilmeAdapter(this, filmesFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public void voltar(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarFilme(s);
                return false;
            }
        });

        return true;
    }

    public void procurarFilme(String nome) {
        filmesFiltrados.clear();

        for (Filme f : filmes) {
            if (f.getNomeFilme().toLowerCase().contains(nome.toLowerCase())) {
                filmesFiltrados.add(f);
            }
        }

        listView.invalidateViews();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        final Filme filmeExcluir = filmesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o filme?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filmesFiltrados.remove(filmeExcluir);
                        filmes.remove(filmeExcluir);
                        dao.excluir(filmeExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        final Filme filmeAtualizar = filmesFiltrados.get(menuInfo.position);
        Intent intent = new Intent(this, FormularioCadastro.class);
        intent.putExtra("filme", filmeAtualizar);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        filmes = dao.obterTodos();
        filmesFiltrados.clear();
        filmesFiltrados.addAll(filmes);
        listView.invalidateViews();
    }
}