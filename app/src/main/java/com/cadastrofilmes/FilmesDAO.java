package com.cadastrofilmes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FilmesDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public FilmesDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Filme filme) {
        ContentValues values = new ContentValues();
        values.put("nomefilme", filme.getNomeFilme());
        values.put("anolancamento", filme.getAnoLancamento());
        values.put("genero", filme.getGenero());

        return banco.insert("filme", null, values);
    }

    public List<Filme> obterTodos() {
        List<Filme> alunos = new ArrayList<>();
        Cursor cursor = banco.query("filme", new String[]{"id", "nomefilme", "anolancamento", "genero"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Filme f = new Filme();
            f.setId(cursor.getInt(0));
            f.setNomeFilme(cursor.getString(1));
            f.setAnoLancamento(cursor.getString(2));
            f.setGenero(cursor.getString(3));

            alunos.add(f);
        }

        return alunos;
    }

    public void excluir(Filme filme) {
        banco.delete("filme", "id = ?", new String[]{filme.getId().toString()});
    }

    public void atualizar(Filme filme) {
        ContentValues values = new ContentValues();
        values.put("nomefilme", filme.getNomeFilme());
        values.put("anolancamento", filme.getAnoLancamento());
        values.put("genero", filme.getGenero());
        banco.update("filme", values, "id = ?", new String[]{filme.getId().toString()});
    }
}
