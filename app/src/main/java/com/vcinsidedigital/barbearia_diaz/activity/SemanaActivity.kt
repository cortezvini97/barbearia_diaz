package com.vcinsidedigital.barbearia_diaz.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.fragment.AgendaFragment
import com.vcinsidedigital.barbearia_diaz.fragment.LucroFragment
import com.vcinsidedigital.barbearia_diaz.fragment.DespesasFragment
import com.vcinsidedigital.barbearia_diaz.model.Mes
import com.vcinsidedigital.barbearia_diaz.model.Semana
import com.vcinsidedigital.barbearia_diaz.viewpager.VPAdapter

class SemanaActivity: AppCompatActivity()
{

    lateinit var toolbar:Toolbar
    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout:TabLayout
    lateinit var semanaAtual:Semana


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semana)


        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)

        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        if(intent.getSerializableExtra("semanaAtual") != null){
            semanaAtual = intent.getSerializableExtra("semanaAtual") as Semana
        }

        var listaFragments = ArrayList<Fragment>();
        listaFragments.add(AgendaFragment(semanaAtual))
        listaFragments.add(LucroFragment(semanaAtual))
        listaFragments.add(DespesasFragment(semanaAtual))

        var vpAdapter = VPAdapter(supportFragmentManager, lifecycle, listaFragments);
        viewPager2.adapter = vpAdapter



        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        if (semanaAtual != null) {
            actionBar?.title = semanaAtual.semana
        }

        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> { tab.text = "Agenda"}
                1 -> { tab.text = "Lucros"}
                2 -> { tab.text = "Despesas"}
            }
        }).attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}