package com.vcinsidedigital.barbearia_diaz.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.model.Notinha
import com.vcinsidedigital.digitalsignature.DigitalSignature


class AssinarActivity : AppCompatActivity()
{
    lateinit var notinha: Notinha
    lateinit var digitalSignature: DigitalSignature
    lateinit var btnSave:Button
    lateinit var btnClear:Button
    lateinit var btnCancelar:Button
    lateinit var imageAssinatura:ImageButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assinar)

        notinha = intent.getSerializableExtra("notinha") as Notinha

        digitalSignature = findViewById(R.id.digitalSignature)
        btnSave = findViewById(R.id.btn_save)
        btnClear = findViewById(R.id.btn_apagar)
        btnCancelar = findViewById(R.id.btn_cancelar)



        btnCancelar.setOnClickListener(View.OnClickListener {
            finish()
            var i = Intent(applicationContext, NotinhaActivity::class.java)
            i.putExtra("notinha", notinha)
            startActivity(i)
        })

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true )
        {
            override fun handleOnBackPressed() {
                finish()
                var i = Intent(applicationContext, NotinhaActivity::class.java)
                i.putExtra("notinha", notinha)
                startActivity(i)
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)

        digitalSignature?.setOnToSignListener(object: DigitalSignature.OnToSignListener{
            override fun onStartSignature() {

            }

            override fun onSigned() {
                btnSave?.isEnabled = true
                btnClear?.isEnabled = true
            }

            override fun onClear() {
                btnSave?.isEnabled = false
                btnClear?.isEnabled = false
            }

            override fun onSave(tmpFile: String?) {
                notinha.file = tmpFile
                finish()
                var i = Intent(applicationContext, NotinhaActivity::class.java)
                i.putExtra("notinha", notinha)
                startActivity(i)
            }

        })


        btnSave?.setOnClickListener {
            digitalSignature?.save(applicationContext)
        }


        btnClear?.setOnClickListener {
            digitalSignature?.clear()
        }



    }
}