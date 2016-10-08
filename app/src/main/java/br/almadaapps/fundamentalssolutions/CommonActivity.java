package br.almadaapps.fundamentalssolutions;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinicius-almada on 06/10/16.
 */

public abstract class CommonActivity extends AppCompatActivity {

    protected abstract void initViews();

    protected void setABVisibility(boolean toVisible) {

    }

    protected void setQLVisibility(boolean toVisible) {

    }

    protected Integer[] getIdsImages() {
        /*Integer[] ids = new Integer[15];
        ids[0] = R.drawable.d;
        ids[1] = R.drawable.drd;
        ids[2] = R.drawable.dre;
        ids[3] = R.drawable.pm;
        ids[4] = R.drawable.pmrd;
//        ids[5] = R.drawable.pmre;
        ids[6] = R.drawable.p;
        ids[7] = R.drawable.m;
        ids[8] = R.drawable.t;
        ids[9] = R.drawable.rha;
        ids[10] = R.drawable.rhb;
        ids[11] = R.drawable.rva;
        ids[12] = R.drawable.rvb;
        ids[13] = R.drawable.rra;
        ids[14] = R.drawable.rrb;
        return ids;*/
        return null;
    }

    protected List<Solucao> getSolucoes(){
        String[] ds = getResources().getStringArray(R.array.solutions_description);
        List<Solucao> aux = new ArrayList<>();
        aux.add(new Solucao(R.drawable.d, ds[0]));
        aux.add(new Solucao(R.drawable.drd,ds[1]));
        aux.add(new Solucao(R.drawable.dre,ds[2]));
        aux.add(new Solucao(R.drawable.pm,ds[3]));
        aux.add(new Solucao(R.drawable.pmrd,ds[4]));
        aux.add(new Solucao(R.drawable.pmre,ds[5]));
        aux.add(new Solucao(R.drawable.p,ds[6]));
        aux.add(new Solucao(R.drawable.m,ds[7]));
        aux.add(new Solucao(R.drawable.t,ds[8]));
        aux.add(new Solucao(R.drawable.rha,ds[9]));
        aux.add(new Solucao(R.drawable.rhb, ds[10]));
        aux.add(new Solucao(R.drawable.rva, ds[11]));
        aux.add(new Solucao(R.drawable.rvb, ds[12]));
        aux.add(new Solucao(R.drawable.rra, ds[13]));
        aux.add(new Solucao(R.drawable.rrb, ds[14]));
        return aux;
    }

    protected int[] idsResults() {
        return new int[]{
                R.drawable.rd,
                R.drawable.rdd,
                R.drawable.rde,
                R.drawable.rf,
                R.drawable.rfd,
                R.drawable.rfe,
                R.drawable.rfab,
                R.drawable.rm,
                R.drawable.rqt,
                R.drawable.rha,
                R.drawable.rhb,
                R.drawable.rva,
                R.drawable.rvb,
                R.drawable.rra,
                R.drawable.rrb,
        };
    }

    protected void showOnDialog(String resultado){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(resultado);
        builder.setTitle("Resultado");
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
