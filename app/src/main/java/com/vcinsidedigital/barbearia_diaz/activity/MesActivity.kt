package com.vcinsidedigital.barbearia_diaz.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.adapter.SemanaAdapter
import com.vcinsidedigital.barbearia_diaz.helper.SemanaDao
import com.vcinsidedigital.barbearia_diaz.model.Ano
import com.vcinsidedigital.barbearia_diaz.model.Mes
import com.vcinsidedigital.barbearia_diaz.model.Semana
import java.util.ArrayList

class MesActivity: AppCompatActivity()
{
    lateinit var toolbar: Toolbar
    lateinit var mesAtual:Mes
    lateinit var recyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton

    var listaSemanas = ArrayList<Semana>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes)

        toolbar = findViewById(R.id.toolbarMesActivity)
        recyclerView = findViewById(R.id.recyclerMes)
        floatingActionButton = findViewById(R.id.fabSemanaAdd)

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        mesAtual = intent.getSerializableExtra("mesAtual") as Mes

        if(mesAtual != null){
            actionBar?.title = mesAtual.mes
        }


        floatingActionButton.setOnClickListener(View.OnClickListener {
            var i = Intent(this, AddSemanaActivity::class.java)
            i.putExtra("mesAtual", mesAtual)
            startActivity(i)
        })


    }

    override fun onStart() {
        super.onStart()
        carregarLista()
    }

    private fun carregarLista()
    {
        //carregarLista
        var semanaDao = SemanaDao(applicationContext)
        listaSemanas = semanaDao.listar(mesAtual.id) as ArrayList<Semana>
        //ConfigurarAdapter
        var semanaAdapter = SemanaAdapter(listaSemanas, this, this)
        //ConfigurarRecyclerView
        var linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = semanaAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}