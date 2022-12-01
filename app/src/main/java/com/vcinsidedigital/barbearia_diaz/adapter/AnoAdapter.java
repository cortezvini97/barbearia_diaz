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
import com.vcinsidedigital.barbearia_diaz.activity.AnoActivity;
import com.vcinsidedigital.barbearia_diaz.model.Ano;

import java.util.List;

public class AnoAdapter extends RecyclerView.Adapter<AnoAdapter.MyAnoHolder>
{
    private List<Ano> listAnos;
    private Context context;
    private Activity activity;

    public AnoAdapter(List<Ano> listaAnos, Context context, Activity activity)
    {
        this.listAnos = listaAnos;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyAnoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ano_adapter_item_list, parent, false);
        return new MyAnoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAnoHolder holder, int position) {
        Ano ano = listAnos.get(position);
        holder.textView.setText(ano.getAno().toString());
    }

    @Override
    public int getItemCount() {
        return listAnos.size();
    }

    public class MyAnoHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyAnoHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.anoTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ano ano = listAnos.get(getAdapterPosition());
                    Intent i = new Intent(context, AnoActivity.class);
                    i.putExtra("ano", ano);
                    activity.startActivity(i);
                }
            });
        }
    }
}
