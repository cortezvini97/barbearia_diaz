package com.vcinsidedigital.barbearia_diaz.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vcinsidedigital.barbearia_diaz.R;
import com.vcinsidedigital.barbearia_diaz.activity.AddAgendaActivity
import com.vcinsidedigital.barbearia_diaz.adapter.AgendaAdapter
import com.vcinsidedigital.barbearia_diaz.helper.AgendaDao
import com.vcinsidedigital.barbearia_diaz.model.Agenda
import com.vcinsidedigital.barbearia_diaz.model.Semana


class AgendaFragment : Fragment
{

    lateinit var fab:FloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var lista:ArrayList<Agenda>

    private lateinit var semanaAtual:Semana;


    constructor(semanaAtual:Semana)
    {
        this.semanaAtual = semanaAtual
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_agenda, container, false);
        fab = view.findViewById(R.id.fabAgenda)
        recyclerView = view.findViewById(R.id.recyclerViewAgenda)

        fab.setOnClickListener(View.OnClickListener {
            var i = Intent(activity?.applicationContext, AddAgendaActivity::class.java)
            i.putExtra("semanaAtual", semanaAtual)
            startActivity(i)
        })

        return view
    }

    override fun onStart() {
        super.onStart()
        carregarLista()
    }

    private fun carregarLista(){
        //carregarLista
        var agendaDao = AgendaDao(activity?.applicationContext)
        lista = agendaDao.listar(semanaAtual) as ArrayList<Agenda>
        //ConfigurarAdapter
        var agendaAdapter = AgendaAdapter(lista, activity, activity)
        //
        var linearLayoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(activity?.applicationContext, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = agendaAdapter
    }


}