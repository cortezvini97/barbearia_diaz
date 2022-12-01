package com.vcinsidedigital.barbearia_diaz.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.activity.AddLucroActivity
import com.vcinsidedigital.barbearia_diaz.adapter.LucroAdapter
import com.vcinsidedigital.barbearia_diaz.helper.CalcularLucro
import com.vcinsidedigital.barbearia_diaz.helper.LucroDao
import com.vcinsidedigital.barbearia_diaz.model.Lucro
import com.vcinsidedigital.barbearia_diaz.model.Semana
import java.text.NumberFormat
import java.util.*


class LucroFragment :Fragment
{

    lateinit var fab: FloatingActionButton

    lateinit var semanaAtual: Semana

    lateinit var recyclerView_domingo: RecyclerView
    lateinit var recyclerView_segunda:RecyclerView
    lateinit var recyclerView_terca:RecyclerView
    lateinit var recyclerView_quarta:RecyclerView
    lateinit var recyclerView_quinta:RecyclerView
    lateinit var recyclerView_sexta:RecyclerView
    lateinit var recyclerView_sabado:RecyclerView

    lateinit var listLucrosDomingo: ArrayList<Lucro>
    lateinit var listLucrosSegunda: ArrayList<Lucro>
    lateinit var listLucrosTerca: ArrayList<Lucro>
    lateinit var listLucrosQuarta: ArrayList<Lucro>
    lateinit var listLucrosQuinta: ArrayList<Lucro>
    lateinit var listLucrosSexta: ArrayList<Lucro>
    lateinit var listLucrosSabado: ArrayList<Lucro>

    lateinit var textViewLucroTotal: TextView



    constructor(semanaAtual: Semana)
    {
        this.semanaAtual = semanaAtual
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_lucro, container, false)
        fab = view.findViewById(R.id.fab_lucro)

        fab.setOnClickListener(View.OnClickListener {
            var i = Intent(activity?.applicationContext, AddLucroActivity::class.java)
            i.putExtra("semanaAtual", semanaAtual)
            startActivity(i)
        })

        recyclerView_domingo = view.findViewById(R.id.recycler_domingo)
        recyclerView_segunda = view.findViewById(R.id.recycler_segunda)
        recyclerView_terca = view.findViewById(R.id.recycler_terca)
        recyclerView_quarta = view.findViewById(R.id.recycler_quarta)
        recyclerView_quinta = view.findViewById(R.id.recycler_quinta)
        recyclerView_sexta = view.findViewById(R.id.recycler_sexta)
        recyclerView_sabado = view.findViewById(R.id.recycler_sabado)

        textViewLucroTotal = view.findViewById(R.id.text_lucro_total_valor)

        return view
    }

    override fun onStart() {
        super.onStart()
        carregarListas()
        calcular()
    }

    fun carregarListas(){
        //Listas
        val db = LucroDao(context)
        listLucrosDomingo = db.listAllByDia(semanaAtual, "Domingo") as ArrayList<Lucro>
        listLucrosSegunda = db.listAllByDia(semanaAtual, "Segunda") as ArrayList<Lucro>
        listLucrosTerca = db.listAllByDia(semanaAtual, "Terça") as ArrayList<Lucro>
        listLucrosQuarta = db.listAllByDia(semanaAtual, "Quarta") as ArrayList<Lucro>
        listLucrosQuinta = db.listAllByDia(semanaAtual, "Quinta") as ArrayList<Lucro>
        listLucrosSexta = db.listAllByDia(semanaAtual, "Sexta") as ArrayList<Lucro>
        listLucrosSabado = db.listAllByDia(semanaAtual, "Sábado") as ArrayList<Lucro>

        //adapter
        val adapterLucroDomingo = LucroAdapter(listLucrosDomingo, context, activity)
        val adaterLucroSegunda = LucroAdapter(listLucrosSegunda, context, activity)
        val adapterLucroTerca = LucroAdapter(listLucrosTerca, context, activity)
        val adapterLucroQuarta = LucroAdapter(listLucrosQuarta, context, activity)
        val adapterLucroQuinta = LucroAdapter(listLucrosQuinta, context, activity)
        val adapterLucroSexta = LucroAdapter(listLucrosSexta, context, activity)
        val adapterLucroSabado = LucroAdapter(listLucrosSabado, context, activity)


        val layoutManagerDomingo: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView_domingo.layoutManager = layoutManagerDomingo
        recyclerView_domingo.setHasFixedSize(true)
        recyclerView_domingo.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayout.VERTICAL
            )
        )
        recyclerView_domingo.adapter = adapterLucroDomingo

        val layoutManagerSegunda: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView_segunda.layoutManager = layoutManagerSegunda
        recyclerView_segunda.setHasFixedSize(true)
        recyclerView_segunda.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        recyclerView_segunda.adapter = adaterLucroSegunda

        val layoutManagerTerca: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView_terca.layoutManager = layoutManagerTerca
        recyclerView_terca.setHasFixedSize(true)
        recyclerView_terca.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        recyclerView_terca.adapter = adapterLucroTerca

        val layoutManagerQuarta: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView_quarta.layoutManager = layoutManagerQuarta
        recyclerView_quarta.setHasFixedSize(true)
        recyclerView_quarta.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        recyclerView_quarta.adapter = adapterLucroQuarta


        val layoutManagerQuinta: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView_quinta.layoutManager = layoutManagerQuinta
        recyclerView_quinta.setHasFixedSize(true)
        recyclerView_quinta.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        recyclerView_quinta.adapter = adapterLucroQuinta

        val layoutManagerSexta: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView_sexta.layoutManager = layoutManagerSexta
        recyclerView_sexta.setHasFixedSize(true)
        recyclerView_sexta.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        recyclerView_sexta.adapter = adapterLucroSexta

        val layoutManagerSabado: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView_sabado.layoutManager = layoutManagerSabado
        recyclerView_sabado.setHasFixedSize(true)
        recyclerView_sabado.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        recyclerView_sabado.adapter = adapterLucroSabado
    }

    public fun calcular(){
        val totalDomingo: Double = CalcularLucro(listLucrosDomingo).calcular()
        val totalSegunda: Double = CalcularLucro(listLucrosSegunda).calcular()
        val totalTerca: Double = CalcularLucro(listLucrosTerca).calcular()
        val totalQuarta: Double = CalcularLucro(listLucrosQuarta).calcular()
        val totalQuinta: Double = CalcularLucro(listLucrosQuinta).calcular()
        val totalSexta: Double = CalcularLucro(listLucrosSexta).calcular()
        val totalSabado: Double = CalcularLucro(listLucrosSabado).calcular()

        val total = totalDomingo + totalSegunda + totalTerca + totalQuarta + totalQuinta + totalSexta + totalSabado


        if (total < 0) {
            textViewLucroTotal.setTextColor(this.resources.getColor(R.color.red))
            textViewLucroTotal.setText(NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(total).replace("-R$", "- R$ "))
        } else {
            textViewLucroTotal.setTextColor(this.resources.getColor(R.color.white))
            textViewLucroTotal.setText(NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(total).replace("R$", " R$ "))
        }
    }

}