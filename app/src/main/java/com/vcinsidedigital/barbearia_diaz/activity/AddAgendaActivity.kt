package com.vcinsidedigital.barbearia_diaz.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.helper.AgendaDao
import com.vcinsidedigital.barbearia_diaz.helper.SemanaDao
import com.vcinsidedigital.barbearia_diaz.model.Agenda
import com.vcinsidedigital.barbearia_diaz.model.Mes
import com.vcinsidedigital.barbearia_diaz.model.Semana


class AddAgendaActivity : AppCompatActivity()
{
    lateinit var toolbar: Toolbar


    lateinit var dia_input: TextInputEditText
    lateinit var data_input:TextInputEditText
    lateinit var hora_input:TextInputEditText
    lateinit var cliente_input:TextInputEditText
    lateinit var preco_input:TextInputEditText
    lateinit var obs_input:TextInputEditText
    lateinit var semanaAtual:Semana
    lateinit var agendaAtual:Agenda
    var edit = false



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_agenda)
        toolbar = findViewById(R.id.toolbarAddAgenda)

        dia_input = findViewById(R.id.text_input_dia);
        data_input = findViewById(R.id.text_input_data);
        hora_input = findViewById(R.id.text_input_hora);
        cliente_input = findViewById(R.id.text_input_cliente);
        preco_input = findViewById(R.id.text_input_preco);
        obs_input = findViewById(R.id.text_input_obs);

        setSupportActionBar(toolbar)

        if(intent.getSerializableExtra("semanaAtual") != null){
            semanaAtual = intent.getSerializableExtra("semanaAtual") as Semana
        }

        if(intent.getSerializableExtra("agendaAtual") != null){
            agendaAtual = intent.getSerializableExtra("agendaAtual") as Agenda
            edit = true
            dia_input.setText(agendaAtual.dia);
            data_input.setText(agendaAtual.data);
            hora_input.setText(agendaAtual.hora);
            cliente_input.setText(agendaAtual.cliente);
            preco_input.setText(agendaAtual.preco.toString());
            obs_input.setText(agendaAtual.obs);
        }

        var actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    public fun onSaveAgenda(view: View){
       val db = AgendaDao(applicationContext)

       if(edit){
           val dia = dia_input.text.toString()
           val data = data_input.text.toString()
           val hora = hora_input.text.toString()
           val preco = preco_input.text.toString()
           val obs = obs_input.text.toString()
           val cliente = cliente_input.text.toString()

           if (!dia.isEmpty() && !data.isEmpty() && !hora.isEmpty() && !preco.isEmpty() && !obs.isEmpty()) {

               val agenda = Agenda(agendaAtual.id, agendaAtual.semanaId, dia, data, hora, cliente, preco.toDouble(), obs)
               if(db.atualizar(agenda)){
                   finish()
                   Toast.makeText(applicationContext, "Agenda Atualizada.", Toast.LENGTH_LONG).show()
               }else{
                   Toast.makeText(applicationContext, "Ocorreu um erro ao atualizar agenda.", Toast.LENGTH_LONG).show()
               }
           }else {
               Toast.makeText(this, "Você deve preencher todos campos.", Toast.LENGTH_SHORT).show()
           }
       }else
       {
           val dia = dia_input.text.toString()
           val data = data_input.text.toString()
           val hora = hora_input.text.toString()
           val preco = preco_input.text.toString()
           val obs = obs_input.text.toString()
           val cliente = cliente_input.text.toString()

           if (!dia.isEmpty() && !data.isEmpty() && !hora.isEmpty() && !preco.isEmpty() && !obs.isEmpty()) {
               val agenda = Agenda()
               agenda.dia = dia
               agenda.data = data
               agenda.hora = hora
               agenda.preco = preco.toDouble()
               agenda.obs = obs
               agenda.semanaId = semanaAtual.id
               agenda.cliente = cliente
               if (db.save(agenda)) {
                   finish()
                   Toast.makeText(this, "Agenda salva com sucesso.", Toast.LENGTH_LONG).show()
               } else {
                   Toast.makeText(applicationContext, "Ocorreu um Erro.", Toast.LENGTH_LONG).show()
               }
           } else {
               Toast.makeText(this, "Você deve preencher todos campos.", Toast.LENGTH_SHORT).show()
           }
       }
   }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(edit){
            getMenuInflater().inflate(R.menu.add_agenda_main, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var id = item.itemId

        if(id == R.id.action_delete_agenda){
            var agendaDao = AgendaDao(applicationContext)
            if(agendaDao.deletar(agendaAtual)){
                finish()
                Toast.makeText(applicationContext, "Agenda Deletada.", Toast.LENGTH_LONG).show()
            }else
            {
                Toast.makeText(applicationContext, "Ocorreu um erro ao deletar Agenda.", Toast.LENGTH_LONG).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean
    {
        finish()
        return true
    }
}