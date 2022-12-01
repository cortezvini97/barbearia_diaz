package com.vcinsidedigital.barbearia_diaz.activity



import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.vcinsidedigital.barbearia_diaz.R
import com.vcinsidedigital.barbearia_diaz.model.Notinha
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*


class NotinhaActivity:AppCompatActivity()
{
    lateinit var toolbar: Toolbar
    lateinit var notinha: Notinha
    lateinit var textCliente:TextView
    lateinit var textOrderServico:TextView
    lateinit var textTelefone:TextView
    lateinit var textWhatsapp:TextView
    lateinit var textData:TextView
    lateinit var textServico:TextView
    lateinit var textCarimbo:TextView
    lateinit var textBtnAssinar:TextView
    lateinit var carimbar: Button
    lateinit var btnAssinar:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notinha)

        toolbar = findViewById(R.id.toolbarNotinha)

        textCliente = findViewById(R.id.clienteText)
        textOrderServico = findViewById(R.id.textOrderServico)
        textTelefone = findViewById(R.id.textTelefone)
        textWhatsapp = findViewById(R.id.textWhatsapp)
        textData = findViewById(R.id.textData)
        textServico = findViewById(R.id.textServico)
        textCarimbo = findViewById(R.id.textCarimbo)
        carimbar = findViewById(R.id.btnCarimbar)
        textBtnAssinar = findViewById(R.id.text_btn_assinar)
        btnAssinar = findViewById(R.id.imageAssinatura)

        setSupportActionBar(toolbar)

        notinha = intent.getSerializableExtra("notinha") as Notinha

        textCliente.text = "Cliente: " + notinha.agenda.cliente
        textOrderServico.text = "ORDEM DE SERVIÇO N° " + notinha.agenda.id
        textTelefone.text = "Telefone: " + notinha.telefone
        textWhatsapp.text = "Whatsapp: " + notinha.whatsapp
        textData.text = "Data: " + notinha.agenda.data + " às " + notinha.agenda.hora
        textServico.text = notinha.agenda.obs

        textCarimbo.text = "Pago"+"\n"+"${getDate()}"

        if(notinha.isCarimbado){
            carimbar.visibility = View.GONE
            textCarimbo.visibility = View.VISIBLE
        }

        carimbar.setOnClickListener(View.OnClickListener {
            notinha.isCarimbado = true
            carimbar.visibility = View.GONE
            textCarimbo.visibility = View.VISIBLE
        })


        if(notinha.file != null){
            textBtnAssinar.visibility = View.GONE
            var file = File(notinha.file)
            val filePath: String = file.getPath()
            var bitmap = BitmapFactory.decodeFile(filePath)
            btnAssinar.setImageBitmap(bitmap)
            btnAssinar.setBackgroundResource(R.color.white)
        }

        var actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        actionBar?.title = notinha.agenda.cliente


        btnAssinar.setOnClickListener(View.OnClickListener {
            finish()
            var i = Intent(applicationContext, AssinarActivity::class.java)
            i.putExtra("notinha", notinha)
            startActivity(i)
        })

    }

    fun  getDate(): String {
        var date = Date();
        var calendar = Calendar.getInstance()
        calendar.time = date
        var currentYear = calendar.get(Calendar.YEAR)
        var currentMonth = calendar.get(Calendar.MONTH) + 1;
        var currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        var dia: String? = null
        var mes: String? = null

        if(currentDay > 9){
            dia = currentDay.toString()
        }else{
            dia = "0${currentDay}"
        }

        if(currentMonth > 9){
            mes = currentMonth.toString()
        }else{
            mes = "0${currentMonth}"
        }



        return "$dia/$mes/$currentYear";
    }

    private fun takeScreenshot():Bitmap {
            var notinhav:ScrollView = findViewById(R.id.notinha_view)
            val bitmap = Bitmap.createBitmap(notinhav.getChildAt(0).getWidth(),
                notinhav.getChildAt(0).getHeight(), Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawColor(Color.WHITE)
            notinhav.draw(canvas)

           return bitmap
    }

    fun saveBitMap(image:Bitmap){
        val extStorageDirectory = Environment.getExternalStorageDirectory().toString()
        var outStream: OutputStream? = null
        val file = File(extStorageDirectory, "${notinha.agenda.cliente}.png")

        try {
            outStream = FileOutputStream(file)
            image.setHasAlpha(true)
            image.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
            var uri:Uri = Uri.parse(file.path)
            openShare(uri)
        }catch (e:Exception){
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }
    }


    fun openShare(uri:Uri){
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/png"
        shareIntent.setPackage("com.whatsapp");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        try {
            startActivity(Intent.createChooser(shareIntent, "share image using"))
        }catch (e:ActivityNotFoundException){
            val iShare = Intent(Intent.ACTION_SEND)
            iShare.putExtra(Intent.EXTRA_STREAM, uri)
            startActivity(Intent.createChooser(iShare, "share image using"))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notinha_main, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var id = item.itemId

        if(id == R.id.action_share){
            var bitmap = takeScreenshot()
            saveBitMap(bitmap)
        }

        return super.onOptionsItemSelected(item)
    }




    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}