package com.cadastrofilmes;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FilmeAdapter extends BaseAdapter {

    private List<Filme> filmes;
    private Activity activity;

    public FilmeAdapter(Activity activity, List<Filme> alunos) {
        this.activity = activity;
        this.filmes = alunos;
    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Object getItem(int i) {
        return filmes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return filmes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView nomeFilme = v.findViewById(R.id.txt_nomeFIlme);
        TextView anoLancamento = v.findViewById(R.id.txt_anoLancamento);
        TextView genero = v.findViewById(R.id.txt_genero);

        Filme f = filmes.get(i);
        nomeFilme.setText(f.getNomeFilme());
        anoLancamento.setText(f.getAnoLancamento());
        genero.setText(f.getGenero());

        return v;
    }
}
