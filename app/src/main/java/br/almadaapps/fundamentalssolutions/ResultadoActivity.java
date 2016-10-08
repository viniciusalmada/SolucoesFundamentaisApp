package br.almadaapps.fundamentalssolutions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vinicius-almada on 08/10/16.
 */
public class ResultadoActivity extends CommonActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Intent it = getIntent();
        boolean isK = it.getBooleanExtra(CalculoActivity.KEY_BOOL_IS_K, false);
        if (isK)
            ((ImageView) findViewById(R.id.iv_resultado)).setImageResource(R.drawable.result_k);
        else
            ((ImageView) findViewById(R.id.iv_resultado)).setImageResource(R.drawable.result);
        String resultado = it.getStringExtra(CalculoActivity.KEY_STRING_RESULTS);
        ((TextView) findViewById(R.id.tv_resultado)).setText(resultado);
    }

    @Override
    protected void initViews() {

    }
}
