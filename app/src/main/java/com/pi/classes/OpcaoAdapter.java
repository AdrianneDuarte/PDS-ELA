package com.pi.classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OpcaoAdapter extends ArrayAdapter<String> {
    private List<String> opcoes;

    public OpcaoAdapter(Context context, List<String> opcoes) {
        super(context, android.R.layout.simple_list_item_single_choice, opcoes);
        this.opcoes = opcoes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String opcao = opcoes.get(position);
        OpcaoHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_single_choice, parent, false);
            holder = new OpcaoHolder((TextView) convertView.findViewById(android.R.id.text1));
            convertView.setTag(holder);
        } else {
            holder = (OpcaoHolder) convertView.getTag();

        }
        holder.setText(opcao);


        return convertView;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return opcoes.get(position);
    }
}
