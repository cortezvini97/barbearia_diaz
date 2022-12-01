package com.vcinsidedigital.barbearia_diaz.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.activity.AddDespesaActivity
import com.vcinsidedigital.barbearia_diaz.adapter.DespesaAdapter
import com.vcinsidedigital.barbearia_diaz.helper.DespesaDao
import com.vcinsidedigital.barbearia_diaz.model.Despesa
import com.vcinsidedigital.barbearia_diaz.model.Semana


class DespesasFragment:Fragment
{
    lateinit var despesaList: List<Despesa>
    lateinit var semanaAtual: Semana
    lateinit var fab: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    constructor(semana: Semana) {
        semanaAtual = semana
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_despesas, container, false)
        fab = view.findViewById(R.id.fab_despesas)

        recyclerView = view.findViewById(R.id.recycler_view_despesas)

        fab.setOnClickListener(View.OnClickListener {
            var i = Intent(context, AddDespesaActivity::class.java)
            i.putExtra("semanaAtual", semanaAtual)
            activity?.startActivity(i)
        })

        return view
    }

    override fun onStart() {
        super.onStart()
        carregarLista()
    }

    fun carregarLista()
    {
        //Listar
        val db = DespesaDao(context)
        despesaList = db.listar(semanaAtual)
        //Apapter
        val despesaAdapter = DespesaAdapter(despesaList, context, activity)

        //recycler view
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        recyclerView.adapter = despesaAdapter
    }
}