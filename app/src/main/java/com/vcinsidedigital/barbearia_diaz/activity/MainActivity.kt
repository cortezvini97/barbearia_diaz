package com.vcinsidedigital.barbearia_diaz.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.adapter.AnoAdapter
import com.vcinsidedigital.barbearia_diaz.helper.AnoDao
import com.vcinsidedigital.barbearia_diaz.helper.DBHelper
import com.vcinsidedigital.barbearia_diaz.model.Ano
import java.util.*

class MainActivity : AppCompatActivity()
{
    lateinit var toolbar: Toolbar

    lateinit var recyclerView: RecyclerView

    var listaAnos = ArrayList<Ano>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbarMainActivity)
        recyclerView = findViewById(R.id.recyclerViewAno)

        setSupportActionBar(toolbar)

    }

    override fun onStart() {
        super.onStart()
        getYear()
    }

    private fun getYear()
    {
        var date = Date();
        var calendar = Calendar.getInstance()
        calendar.time = date
        var currentYear = calendar.get(Calendar.YEAR).toString()

        var anoDao = AnoDao(applicationContext)
        var ano = anoDao.getAno(currentYear.toLong())

        if(ano.ano != null){
            listarAnos()
        }else {
            saveAno(currentYear.toLong())
        }
    }

    private fun saveAno(ano:Long){
        var anoObj = Ano()
        anoObj.ano = ano
        var anoDao = AnoDao(applicationContext)
        if(anoDao.salvar(anoObj)){
            listarAnos()
        }
    }

    private fun listarAnos(){
        //carregarLista
        var anoDao = AnoDao(applicationContext)
        listaAnos = anoDao.listar() as ArrayList<Ano>

        //ConfigurarAdapter
        var anoAdapter = AnoAdapter(listaAnos, this, this)
        //ConfigurarRecyclerView
        var linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = anoAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if(id == R.id.action_settings){
            if(DBHelper.deleteDatabse(applicationContext)){
                finish()
                var i = Intent(applicationContext, SplashActivity::class.java)
                startActivity(i)

            }
        }

        return super.onOptionsItemSelected(item)

    }
}