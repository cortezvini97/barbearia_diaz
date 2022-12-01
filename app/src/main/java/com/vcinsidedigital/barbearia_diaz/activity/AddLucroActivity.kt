package com.vcinsidedigital.barbearia_diaz.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.helper.LucroDao
import com.vcinsidedigital.barbearia_diaz.helper.SemanaDao
import com.vcinsidedigital.barbearia_diaz.model.Lucro
import com.vcinsidedigital.barbearia_diaz.model.Semana


class AddLucroActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener
{
    lateinit var toolbar: Toolbar
    lateinit var semanaAtual:Semana
    lateinit var lucroAtual:Lucro
    lateinit var titulo_input: TextInputEditText
    lateinit var valor_input:TextInputEditText
    lateinit var gastos_input:TextInputEditText
    lateinit var quantidade_input:TextInputEditText
    lateinit var spinner_dias: Spinner
    private var diaSelecionado: String? = null
    private var edit = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lucro)

        toolbar = findViewById(R.id.toolbarAddLucro)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        titulo_input = findViewById(R.id.text_input_tituloLucro);
        valor_input = findViewById(R.id.text_input_lucroValor);
        gastos_input = findViewById(R.id.text_input_lucroGastos);
        spinner_dias = findViewById(R.id.spinner_dias_semana);
        quantidade_input = findViewById(R.id.text_input_lucro_quantidade);

        spinner_dias = findViewById(R.id.spinner_dias_semana)

        var diasSemanas = resources.getStringArray(R.array.lista_dias)

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_item,
            diasSemanas
        )

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        spinner_dias.setAdapter(adapter);
        spinner_dias.setOnItemSelectedListener(this);

        if (intent.getSerializableExtra("lucroAtual") != null)
        {
            lucroAtual = intent.getSerializableExtra("lucroAtual") as Lucro
            titulo_input.setText(lucroAtual.titulo)
            valor_input.setText(java.lang.Double.toString(lucroAtual.valor))
            gastos_input.setText(java.lang.Double.toString(lucroAtual.gastoCorte))

            val spinnerPosition = adapter.getPosition(lucroAtual.dia)

            spinner_dias.setSelection(spinnerPosition)

            diaSelecionado = lucroAtual.dia

            quantidade_input.setText(java.lang.Long.toString(lucroAtual.quantidade))

            edit = true

        }


        if(intent.getSerializableExtra("semanaAtual") != null)
        {
            semanaAtual = intent.getSerializableExtra("semanaAtual") as Semana

        }

    }

    public fun saveLucro(view: View?)
    {
        val db = LucroDao(applicationContext)
        if(edit)
        {

            var titulo = titulo_input.text.toString()
            var valor = valor_input.getText().toString()
            var gasto = gastos_input.getText().toString()
            var quantidade = quantidade_input.getText().toString()

            if (!titulo.isEmpty() && !valor.isEmpty() && !gasto.isEmpty() && !quantidade.isEmpty() && !diaSelecionado.equals("Selecione uma Opção"))
            {
                val lucro = Lucro()
                lucro.id = lucroAtual.id
                lucro.titulo = titulo
                lucro.valor = valor.toDouble()
                lucro.gastoCorte = gasto.toDouble()
                lucro.semanaId = lucroAtual.semanaId
                lucro.dia = diaSelecionado
                lucro.quantidade = quantidade.toLong()

                if(db.atualizar(lucro))
                {
                    Toast.makeText(this, "Lucro atualizado com sucesso.", Toast.LENGTH_SHORT).show();
                    finish();
                }else
                {
                    Toast.makeText(this, "Ocorreu um erro ao atualizar, tente novamente.", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this, "Preencha todo Formulário.", Toast.LENGTH_SHORT).show();
            }

        }else
        {
            var titulo = titulo_input.text.toString()
            var valor = valor_input.getText().toString()
            var gasto = gastos_input.getText().toString()
            var quantidade = quantidade_input.getText().toString()

            if (!titulo.isEmpty() && !valor.isEmpty() && !gasto.isEmpty() && !quantidade.isEmpty() && !diaSelecionado.equals("Selecione uma Opção"))
            {
                val lucro = Lucro()
                lucro.titulo = titulo
                lucro.valor = valor.toDouble()
                lucro.gastoCorte = gasto.toDouble()
                lucro.semanaId = semanaAtual.id
                lucro.dia = diaSelecionado
                lucro.quantidade = quantidade.toLong()

                if(db.salvar(lucro))
                {
                    Toast.makeText(this, "Lucro salvo com sucesso.", Toast.LENGTH_SHORT).show();
                    finish();
                }else
                {
                    Toast.makeText(this, "Ocorreu um erro ao salvar, tente novamente.", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this, "Preencha todo Formulário.", Toast.LENGTH_SHORT).show();
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(edit) {
            getMenuInflater().inflate(R.menu.add_lucro_main, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var id = item.itemId

        if(id == R.id.action_delete_lucro){
            var lucroDao = LucroDao(applicationContext)
            if(lucroDao.deletar(lucroAtual)){
                finish()
                Toast.makeText(applicationContext, "Lucro Deletado.", Toast.LENGTH_LONG).show()
            }else
            {
                Toast.makeText(applicationContext, "Ocorreu um erro ao deletar lucro.", Toast.LENGTH_LONG).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        this.diaSelecionado = adapterView?.getItemAtPosition(i).toString();
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}