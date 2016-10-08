package br.almadaapps.fundamentalssolutions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vinicius-almada on 08/10/16.
 */

public class SolucoesArrayAdapter extends ArrayAdapter<Solucao> {
    private Context context;
    private List<Solucao> objects;

    public SolucoesArrayAdapter(Context context, List<Solucao> objects) {
        super(context, 0, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Solucao s = objects.get(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_solucoes_with_text, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.tvDescricao = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(s.getId());
        holder.tvDescricao.setText(s.getDescription());

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView tvDescricao;
    }
}
