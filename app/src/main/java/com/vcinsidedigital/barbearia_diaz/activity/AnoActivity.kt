package com.vcinsidedigital.barbearia_diaz.activity

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.adapter.MesAdapter
import com.vcinsidedigital.barbearia_diaz.helper.MesDAO
import com.vcinsidedigital.barbearia_diaz.model.Ano
import com.vcinsidedigital.barbearia_diaz.model.Mes


class AnoActivity : AppCompatActivity()
{
    lateinit var toolbar: Toolbar
    lateinit var recyclerMes:RecyclerView
    lateinit var anoAtual:Ano
    var listaMes = ArrayList<Mes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ano)

        toolbar = findViewById(R.id.toolbarAno)
        recyclerMes = findViewById(R.id.recyclerViewMes)

        anoAtual = intent.getSerializableExtra("ano") as Ano

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        if(anoAtual != null){
            actionBar?.title = anoAtual.ano.toString()
        }

    }


    override fun onStart() {
        super.onStart()
        getMeses()
    }

    private fun getMeses(){
        var mesDAO = MesDAO(applicationContext);
        listaMes = mesDAO.listar(anoAtual) as ArrayList<Mes>;

        if(listaMes.size > 0){
            carregarListaMes()
        }else
        {
            var janeiro = Mes("Janeiro", anoAtual.ano)
            var fevereiro = Mes("Fevereiro", anoAtual.ano)
            var marco = Mes("Março", anoAtual.ano)
            var abril = Mes("Abril", anoAtual.ano)
            var maio = Mes("Maio", anoAtual.ano)
            var junho = Mes("Junho", anoAtual.ano)
            var julho = Mes("Julho", anoAtual.ano)
            var agosto = Mes("Agosto", anoAtual.ano)
            var setembro = Mes("Setembro", anoAtual.ano)
            var outubro = Mes("Outubro", anoAtual.ano)
            var novembro = Mes("Novembro", anoAtual.ano)
            var dezembro = Mes("Dezembro", anoAtual.ano)

            if (mesDAO.save(janeiro) && mesDAO.save(fevereiro) && mesDAO.save(marco) && mesDAO.save(abril) && mesDAO.save(maio) && mesDAO.save(junho) && mesDAO.save(julho) && mesDAO.save(agosto) && mesDAO.save(setembro) && mesDAO.save(outubro) && mesDAO.save(novembro) && mesDAO.save(dezembro)){
                carregarListaMes()
            }else{
                Toast.makeText(applicationContext, "Ocorreu um Erro", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun carregarListaMes(){
        //carregarLista
        var mesDAO = MesDAO(applicationContext);
        listaMes = mesDAO.listar(anoAtual) as ArrayList<Mes>;

        //ConfigurarAdapter
        var mesAdapter = MesAdapter(listaMes, this, this)
        //ConfigurarRecyclerView
        var linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerMes.layoutManager = linearLayoutManager
        recyclerMes.setHasFixedSize(true)
        recyclerMes.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        recyclerMes.adapter = mesAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}