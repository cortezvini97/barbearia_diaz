package com.vcinsidedigital.barbearia_diaz.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vcinsidedigital.barbearia_diaz.model.Ano;
import com.vcinsidedigital.barbearia_diaz.model.Mes;

import java.util.ArrayList;
import java.util.List;

public class MesDAO implements IMesDAO
{
    SQLiteDatabase escreve;
    SQLiteDatabase le;
    public MesDAO(Context context)
    {
        DBHelper db = new DBHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public List<Mes> listar(Ano ano)
    {
        List<Mes> listMeses = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_MESES + " WHERE ano = " + ano.getAno() + ";";

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext())
        {
            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String mesStr = c.getString(c.getColumnIndex("mes"));
            Mes mes = new Mes();
            mes.setId(id);
            mes.setMes(mesStr);
            listMeses.add(mes);
        }

        return listMeses;
    }

    @Override
    public boolean save(Mes mes) {
        ContentValues cv = new ContentValues();
        cv.put("mes", mes.getMes());
        cv.put("ano", mes.getAno());

        try{
            escreve.insert(DBHelper.TABELA_MESES, null, cv);
            Log.i("INFO", "Sucesso ao Salvar Mes!");
            return true;
        }catch (Exception e){
            Log.i("INFO", "Erro ao Salvar Mes " + e.getMessage());
            return false;
        }
    }
}
