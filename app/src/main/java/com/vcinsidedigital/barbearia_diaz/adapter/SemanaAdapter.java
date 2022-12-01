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
import com.vcinsidedigital.barbearia_diaz.activity.AddSemanaActivity;
import com.vcinsidedigital.barbearia_diaz.activity.AnoActivity;
import com.vcinsidedigital.barbearia_diaz.activity.SemanaActivity;
import com.vcinsidedigital.barbearia_diaz.model.Ano;
import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.List;

public class SemanaAdapter extends RecyclerView.Adapter<SemanaAdapter.MyAnoHolder>
{
    private List<Semana> listSemanas;
    private Context context;
    private Activity activity;

    public SemanaAdapter(List<Semana> listSemanas, Context context, Activity activity)
    {
        this.listSemanas = listSemanas;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyAnoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.semana_adapter_item, parent, false);
        return new MyAnoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAnoHolder holder, int position) {
        Semana semana = listSemanas.get(position);
        holder.textView.setText(semana.getSemana());
    }

    @Override
    public int getItemCount() {
        return listSemanas.size();
    }

    public class MyAnoHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyAnoHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.titleSemana);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Semana semana = listSemanas.get(getAdapterPosition());
                    Intent i = new Intent(context, SemanaActivity.class);
                    i.putExtra("semanaAtual", semana);
                    activity.startActivity(i);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Semana semana = listSemanas.get(getAdapterPosition());
                    Intent i = new Intent(context, AddSemanaActivity.class);
                    i.putExtra("semanaAtual", semana);
                    activity.startActivity(i);
                    return false;
                }
            });
        }
    }
}
