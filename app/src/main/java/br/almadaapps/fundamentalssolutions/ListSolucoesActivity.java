package br.almadaapps.fundamentalssolutions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by vinicius-almada on 08/10/16.
 */

public class ListSolucoesActivity extends CommonActivity implements AdapterView.OnItemClickListener{
    public static final String KEY_SOLUCAO_CLICADA = "br.almadaapps.fundamentalssolutions.KEY_SOLUCAO_CLICADA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_solucoes);
        ListView lvSolucoes = (ListView) findViewById(R.id.lv_solucoes);
        SolucoesArrayAdapter arrayAdapter = new SolucoesArrayAdapter(this, getSolucoes());
        lvSolucoes.setAdapter(arrayAdapter);
        lvSolucoes.setOnItemClickListener(this);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = new Intent(this, CalculoActivity.class);
        it.putExtra(KEY_SOLUCAO_CLICADA, position);
        startActivity(it);
    }
}
