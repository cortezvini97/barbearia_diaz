package com.vcinsidedigital.barbearia_diaz.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.vcinsidedigital.barbearia_diaz.model.Despesa;
import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.ArrayList;
import java.util.List;

public class DespesaDao implements IDespesaDao
{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public DespesaDao(Context context)
    {
        DBHelper db = new DBHelper(context);
        this.escreve = db.getWritableDatabase();
        this.le = db.getReadableDatabase();
    }


    @Override
    public boolean salvar(Despesa despesa)
    {
        ContentValues cv = new ContentValues();
        cv.put("titulo", despesa.getNome());
        cv.put("valor", despesa.getValor());
        cv.put("semana_id", despesa.getSemanaId());

        try
        {
            escreve.insert(DBHelper.TABELA_DESPESAS, null, cv);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean update(Despesa despesa)
    {
        ContentValues cv = new ContentValues();
        cv.put("titulo", despesa.getNome());
        cv.put("valor", despesa.getValor());
        cv.put("semana_id", despesa.getSemanaId());

        String[] args = {despesa.getId().toString()};

        try
        {
            escreve.update(DBHelper.TABELA_DESPESAS, cv, "id=?", args);
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public boolean deletar(Despesa despesa)
    {
        String[] args = {despesa.getId().toString()};

        try
        {
            escreve.delete(DBHelper.TABELA_DESPESAS, "id=?", args);
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public List<Despesa> listar(Semana semana)
    {
        List<Despesa> despesaList = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_DESPESAS + " WHERE semana_id = " + semana.getId() + ";";

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext())
        {
            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String titulo = c.getString(c.getColumnIndex("titulo"));
            @SuppressLint("Range") Double valor = c.getDouble(c.getColumnIndex("valor"));
            @SuppressLint("Range") Long semana_id = c.getLong(c.getColumnIndex("semana_id"));

            Despesa despesa = new Despesa();
            despesa.setId(id);
            despesa.setNome(titulo);
            despesa.setValor(valor);
            despesa.setSemanaId(semana_id);

            despesaList.add(despesa);

        }

        return despesaList;
    }
}
