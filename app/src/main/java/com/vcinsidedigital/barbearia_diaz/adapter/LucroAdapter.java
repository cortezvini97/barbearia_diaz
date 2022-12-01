package com.vcinsidedigital.barbearia_diaz.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vcinsidedigital.barbearia_diaz.activity.AddLucroActivity;
import com.vcinsidedigital.barbearia_diaz.model.Lucro;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.vcinsidedigital.barbearia_diaz.R;

public class LucroAdapter extends RecyclerView.Adapter<LucroAdapter.LucroHolder>
{

    private Context context;
    private Activity activity;
    private List<Lucro> list;

    public LucroAdapter(List<Lucro> list, Context context, Activity activity)
    {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public LucroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lucro_item_adapter, parent, false);
        return new LucroHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LucroHolder holder, int position) {
        Lucro lucro = list.get(position);
        holder.titulo.setText(lucro.getTitulo());
        holder.lucro.setText("Lucro: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(lucro.getValor()).replace("R$", " R$ "));
        holder.gastos.setText("Gastos: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(lucro.getGastoCorte()).replace("R$", " R$ "));
        holder.quantidade.setText("Quantidade: " + lucro.getQuantidade().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LucroHolder extends RecyclerView.ViewHolder
    {
        TextView titulo, lucro, gastos, quantidade;
        public LucroHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.text_view_tituloLucro);
            lucro = itemView.findViewById(R.id.text_view_lucro);
            gastos = itemView.findViewById(R.id.text_view_gastos);
            quantidade = itemView.findViewById(R.id.text_view_quantidade);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Lucro lucro = list.get(getAdapterPosition());
                    Intent i = new Intent(context, AddLucroActivity.class);
                    i.putExtra("lucroAtual", lucro);
                    activity.startActivity(i);
                }
            });
        }
    }
}
