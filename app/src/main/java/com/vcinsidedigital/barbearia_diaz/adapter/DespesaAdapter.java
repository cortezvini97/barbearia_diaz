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

import com.vcinsidedigital.barbearia_diaz.R;
import com.vcinsidedigital.barbearia_diaz.activity.AddDespesaActivity;
import com.vcinsidedigital.barbearia_diaz.model.Despesa;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.MyDispesaViewHolder>
{

    private List<Despesa> despesaList;
    private Context context;
    private Activity activity;

    public DespesaAdapter(List<Despesa> despesaList, Context context, Activity activity)
    {
        this.despesaList = despesaList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyDispesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.despesa_item_adapter, parent, false);
        return new MyDispesaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDispesaViewHolder holder, int position)
    {
        Despesa dispesa = despesaList.get(position);
        holder.titulo_despesa.setText(dispesa.getNome());
        holder.valor_despesa.setText("Valor: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(dispesa.getValor()).replace("R$", " R$ "));
    }

    @Override
    public int getItemCount() {
        return this.despesaList.size();
    }

    public class MyDispesaViewHolder extends RecyclerView.ViewHolder
    {
        TextView titulo_despesa, valor_despesa;

        public MyDispesaViewHolder(@NonNull View itemView)
        {
            super(itemView);
            titulo_despesa = itemView.findViewById(R.id.text_despesa);
            valor_despesa = itemView.findViewById(R.id.text_valor_despesa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Despesa despesa = despesaList.get(getAdapterPosition());
                    Intent i = new Intent(context, AddDespesaActivity.class);
                    i.putExtra("despesaAtual", despesa);
                    activity.startActivity(i);
                }
            });
        }
    }
}
