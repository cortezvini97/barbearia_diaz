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
import com.vcinsidedigital.barbearia_diaz.activity.MesActivity;
import com.vcinsidedigital.barbearia_diaz.activity.SemanaActivity;
import com.vcinsidedigital.barbearia_diaz.model.Mes;

import java.util.List;

public class MesAdapter extends RecyclerView.Adapter<MesAdapter.MesMyViewHolder>
{

    private List<Mes> mesList;
    private Activity activity;
    private Context context;

    public MesAdapter(List<Mes> lista, Activity activity, Context context){
        this.mesList = lista;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public MesMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mes_adapter_item_list, parent, false);
        return new MesMyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesMyViewHolder holder, int position) {
        Mes mes = mesList.get(position);
        holder.mes.setText(mes.getMes());
    }

    @Override
    public int getItemCount() {
        return mesList.size();
    }

    public class MesMyViewHolder extends RecyclerView.ViewHolder{

        TextView mes;

        public MesMyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Mes mesAtual = mesList.get(getAdapterPosition());
                    Intent i = new Intent(context, MesActivity.class);
                    i.putExtra("mesAtual", mesAtual);
                    activity.startActivity(i);
                }
            });
            mes = itemView.findViewById(R.id.mestext);
        }
    }
}
