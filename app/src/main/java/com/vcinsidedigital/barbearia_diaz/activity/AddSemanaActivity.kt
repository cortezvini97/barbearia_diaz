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
import com.vcinsidedigital.barbearia_diaz.helper.SemanaDao
import com.vcinsidedigital.barbearia_diaz.model.Mes
import com.vcinsidedigital.barbearia_diaz.model.Semana

class AddSemanaActivity : AppCompatActivity(){

    lateinit var toolbar: Toolbar
    lateinit var textInputEditText:TextInputEditText
    lateinit var mesAtual:Mes
    lateinit var semanaAtual: Semana
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_semana)

        toolbar = findViewById(R.id.toolbarAddSemanaActivity)
        textInputEditText = findViewById(R.id.textInputSemana)

        setSupportActionBar(toolbar)

        if(intent.getSerializableExtra("mesAtual") != null){
            mesAtual = intent.getSerializableExtra("mesAtual") as Mes
        }

        if (intent.getSerializableExtra("semanaAtual") != null){
            semanaAtual = intent.getSerializableExtra("semanaAtual") as Semana

            if(semanaAtual != null){
                textInputEditText.setText(semanaAtual.semana)
            }

            edit = true
        }

        var actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    public fun clickSave(view:View){
        if(edit){
            var text = textInputEditText.text.toString()
            if (text != null || text != "") {
                var semanaDao = SemanaDao(applicationContext)
                var semana = Semana(semanaAtual.id, text, semanaAtual.mesId)
                if(semanaDao.atualizar(semana)){
                    finish()
                    Toast.makeText(applicationContext, "Semana Atualizada.", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(
                        applicationContext,
                        "Ocorreu um erro ao Atualizar Semana.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }else {
                Toast.makeText(
                    applicationContext,
                    "Você Precisa Preencher o campo.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else {
            var text = textInputEditText.text.toString()

            if (!text.isEmpty()) {
                var semanaDao = SemanaDao(applicationContext)
                var semana = Semana()
                semana.semana = text;
                semana.mesId = mesAtual.id

                if (semanaDao.salvar(semana)) {
                    finish()
                    Toast.makeText(applicationContext, "Semana Salva.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Ocorreu um erro ao Salvar Semana.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Você Precisa Preencher o campo.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        if(edit) {
            getMenuInflater().inflate(R.menu.add_semana_main, menu);
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        var id = item.itemId

        if(id == R.id.action_delete){
            var semanaDao = SemanaDao(applicationContext)
            if(semanaDao.deletar(semanaAtual)){
               finish()
               Toast.makeText(applicationContext, "Semana Deletada.", Toast.LENGTH_LONG).show()
            }else
            {
                Toast.makeText(applicationContext, "Ocorreu um erro ao deletar Semana.", Toast.LENGTH_LONG).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}