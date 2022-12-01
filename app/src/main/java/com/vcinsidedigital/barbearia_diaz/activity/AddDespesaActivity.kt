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
import com.vcinsidedigital.barbearia_diaz.helper.DespesaDao
import com.vcinsidedigital.barbearia_diaz.model.Despesa
import com.vcinsidedigital.barbearia_diaz.model.Semana


class AddDespesaActivity: AppCompatActivity()
{

    lateinit var toolbar: Toolbar

    lateinit var semanaAtual: Semana
    lateinit var despesaAtual: Despesa
    lateinit var text_input_titulo_despesa: TextInputEditText
    lateinit var text_input_valor_despesa:TextInputEditText
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_despesa)

        toolbar = findViewById(R.id.add_despesa_toolbar)

        text_input_titulo_despesa = findViewById(R.id.text_input_titluo_despesa)
        text_input_valor_despesa = findViewById(R.id.text_input_valor_despesa)



        if(intent.getSerializableExtra("despesaAtual") != null){
            despesaAtual = intent.getSerializableExtra("despesaAtual") as Despesa
            text_input_titulo_despesa.setText(despesaAtual.nome)
            text_input_valor_despesa.setText(despesaAtual.valor.toString())
            edit = true
        }


        if(intent.getSerializableExtra("semanaAtual") != null){
            semanaAtual = intent.getSerializableExtra("semanaAtual") as Semana
        }

        setSupportActionBar(toolbar)

        var actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun saveDespesa(view:View)
    {
        val db = DespesaDao(applicationContext)
        if(edit)
        {
            var titulo_despesa = text_input_titulo_despesa.getText().toString();
            var valor_despesa = text_input_valor_despesa.getText().toString();

            if(!titulo_despesa.isEmpty() && !valor_despesa.isEmpty())
            {
                val despesa = Despesa()
                despesa.id = despesaAtual.id
                despesa.nome = titulo_despesa
                despesa.valor = valor_despesa.toDouble()
                despesa.semanaId = despesaAtual.semanaId

                if (db.update(despesa)) {
                    finish()
                    Toast.makeText(this, "Despesa atualizada com sucesso.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "Ocorreu um erro ao atualizar, tente novamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }else{
                Toast.makeText(
                    this,
                    "Você precisa preencher todos campos.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }else {
            var titulo_despesa = text_input_titulo_despesa.getText().toString();
            var valor_despesa = text_input_valor_despesa.getText().toString();

            if(!titulo_despesa.isEmpty() && !valor_despesa.isEmpty())
            {
                val despesa = Despesa()
                despesa.nome = titulo_despesa
                despesa.valor = valor_despesa.toDouble()
                despesa.semanaId = semanaAtual.id

                if (db.salvar(despesa)) {
                    finish()
                    Toast.makeText(this, "Despesa salva com sucesso.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "Ocorreu um erro ao salvar, tente novamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }else{
                Toast.makeText(
                    this,
                    "Você precisa preencher todos campos.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        if(edit){
            menuInflater.inflate(R.menu.add_despesa_main, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var id = item.itemId

        if(id == R.id.action_delete_despesa){
            var db = DespesaDao(applicationContext)
            if (db.deletar(despesaAtual)){
                finish()
                Toast.makeText(applicationContext, "Despesa deletada com sucesso.", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "Ocorreu um erro ao deletar a despesa.", Toast.LENGTH_LONG).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}