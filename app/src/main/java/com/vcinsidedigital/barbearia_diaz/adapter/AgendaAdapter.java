package com.vcinsidedigital.barbearia_diaz.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vcinsidedigital.barbearia_diaz.R;
import com.vcinsidedigital.barbearia_diaz.activity.AddAgendaActivity;
import com.vcinsidedigital.barbearia_diaz.activity.NotinhaActivity;
import com.vcinsidedigital.barbearia_diaz.model.Agenda;
import com.vcinsidedigital.barbearia_diaz.model.Notinha;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder>
{
    private List<Agenda> list;
    private Context context;
    private Activity activity;

    public AgendaAdapter(List<Agenda> list, Context context, Activity activity)
    {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_adapter_item, parent, false);
        return new AgendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position)
    {
        Agenda agenda = list.get(position);
        holder.text_dia.setText(agenda.getDia());
        holder.text_data.setText(agenda.getData());
        holder.text_cliente.setText("Cliente: " + agenda.getCliente());
        holder.text_hora.setText("Hora: " + agenda.getHora());
        holder.text_preco.setText("Preço: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(agenda.getPreco()).replace("R$", "R$ "));
        holder.text_obs.setText("Obs: " + agenda.getObs());
    }

    @Override
    public int getItemCount()
    {
        return this.list.size();
    }

    public class AgendaViewHolder extends RecyclerView.ViewHolder
    {
        private TextView text_dia, text_data, text_cliente, text_hora, text_preco, text_obs;
        public AgendaViewHolder(@NonNull View itemView) {
            super(itemView);
            text_dia = itemView.findViewById(R.id.text_dia);
            text_data = itemView.findViewById(R.id.text_data);
            text_cliente = itemView.findViewById(R.id.text_cliente);
            text_hora = itemView.findViewById(R.id.text_hora);
            text_preco = itemView.findViewById(R.id.text_preco);
            text_obs = itemView.findViewById(R.id.text_obs);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Agenda agenda = list.get(getAdapterPosition());

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    View mView = activity.getLayoutInflater().inflate(R.layout.dialog_form_notinha, null);

                    EditText telCliente, whatsappCliente;
                    Button confirm, cancel;
                    confirm = mView.findViewById(R.id.confirm);
                    cancel = mView.findViewById(R.id.cancel);
                    telCliente = mView.findViewById(R.id.telCliente);
                    whatsappCliente = mView.findViewById(R.id.whatsappCliente);


                    builder.setView(mView);

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String telefone = telCliente.getText().toString();
                            String whatsapp = whatsappCliente.getText().toString();

                            if(!telefone.isEmpty() && !whatsapp.isEmpty()){
                               dialog.dismiss();
                                Notinha notinha = new Notinha();
                                notinha.setTelefone(telefone);
                                notinha.setWhatsapp(whatsapp);
                                notinha.setCarimbado(false);
                                notinha.setAgenda(agenda);
                                Intent i = new Intent(context, NotinhaActivity.class);
                                i.putExtra("notinha", notinha);
                                activity.startActivity(i);
                            }else {
                                Toast.makeText(context, "Você precisa preencher o fomulário", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });


                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Agenda agenda = list.get(getAdapterPosition());
                    Intent i = new Intent(context, AddAgendaActivity.class);
                    i.putExtra("agendaAtual", agenda);
                    activity.startActivity(i);
                    return false;
                }
            });
        }
    }
}
