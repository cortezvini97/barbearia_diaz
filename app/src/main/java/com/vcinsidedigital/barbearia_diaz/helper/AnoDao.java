package com.vcinsidedigital.barbearia_diaz.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vcinsidedigital.barbearia_diaz.model.Ano;

import java.util.ArrayList;
import java.util.List;

public class AnoDao implements IAnoDao {

    SQLiteDatabase escreve;
    SQLiteDatabase le;
    public AnoDao(Context context)
    {
        DBHelper db = new DBHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public List<Ano> listar()
    {
        List<Ano> listAno = new ArrayList<Ano>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_ANOS + ";";

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()){

            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") Long ano = c.getLong(c.getColumnIndex("ano"));

            Ano anoObj = new Ano();
            anoObj.setId(id);
            anoObj.setAno(ano);
            listAno.add(anoObj);
        }

        return listAno;
    }

    @Override
    public boolean salvar(Ano ano) {
        ContentValues cv = new ContentValues();
        cv.put("ano", ano.getAno());

        try{
            escreve.insert(DBHelper.TABELA_ANOS, null, cv);
            Log.i("INFO", "Sucesso ao Salvar Ano!");
            return true;
        }catch (Exception e){
            Log.i("INFO", "Erro ao Salvar Ano " + e.getMessage());
            return false;
        }
    }


    @Override
    public Ano getAno(Long anoAtual) {
        String sql = "SELECT * FROM " + DBHelper.TABELA_ANOS + " WHERE ano = " + anoAtual + ";";
        Cursor c = le.rawQuery(sql, null);

        Ano anoObj = new Ano();

        while (c.moveToNext()){

            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") Long ano = c.getLong(c.getColumnIndex("ano"));

            anoObj.setId(id);
            anoObj.setAno(ano);
        }

        return anoObj;
    }
}
