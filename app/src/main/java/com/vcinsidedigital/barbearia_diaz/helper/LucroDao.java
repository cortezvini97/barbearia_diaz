package com.vcinsidedigital.barbearia_diaz.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vcinsidedigital.barbearia_diaz.model.Lucro;
import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.ArrayList;
import java.util.List;

public class LucroDao implements ILucroDao
{

    private SQLiteDatabase le;
    private SQLiteDatabase escreve;

    public LucroDao(Context context)
    {
        DBHelper db = new DBHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Lucro lucro)
    {
        ContentValues cv = new ContentValues();
        cv.put("dia", lucro.getDia());
        cv.put("titulo", lucro.getTitulo());
        cv.put("valor", lucro.getValor());
        cv.put("gasto", lucro.getGastoCorte());
        cv.put("semana_id", lucro.getSemanaId());
        cv.put("quantidade", lucro.getQuantidade());

        try
        {
            escreve.insert(DBHelper.TABELA_LUCROS, null, cv);
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public boolean atualizar(Lucro lucro)
    {
        ContentValues cv = new ContentValues();
        cv.put("dia", lucro.getDia());
        cv.put("titulo", lucro.getTitulo());
        cv.put("valor", lucro.getValor());
        cv.put("gasto", lucro.getGastoCorte());
        cv.put("semana_id", lucro.getSemanaId());
        cv.put("quantidade", lucro.getQuantidade());

        String[] args = {lucro.getId().toString()};

        try
        {
            escreve.update(DBHelper.TABELA_LUCROS, cv, "id=?", args);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deletar(Lucro lucro)
    {
        String[] args = {lucro.getId().toString()};

        try {
            escreve.delete(DBHelper.TABELA_LUCROS, "id=?", args);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Lucro> listAllByDia(Semana semana, String dia_value)
    {
        List<Lucro> listLucro = new ArrayList<Lucro>();

        String dia = "'"+dia_value+"'";

        String sql = "SELECT * FROM " + DBHelper.TABELA_LUCROS + " WHERE semana_id = " + semana.getId() + " AND dia = " + dia + ";";

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext())
        {
            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String titulo = c.getString(c.getColumnIndex("titulo"));
            @SuppressLint("Range") Double valor = c.getDouble(c.getColumnIndex("valor"));
            @SuppressLint("Range") Double gasto = c.getDouble(c.getColumnIndex("gasto"));
            @SuppressLint("Range") Long semanaID = c.getLong(c.getColumnIndex("semana_id"));
            @SuppressLint("Range") String diaAtual = c.getString(c.getColumnIndex("dia"));
            @SuppressLint("Range") Long quantidade = c.getLong(c.getColumnIndex("quantidade"));

            Lucro lucro = new Lucro();
            lucro.setId(id);
            lucro.setTitulo(titulo);
            lucro.setValor(valor);
            lucro.setGastoCorte(gasto);
            lucro.setSemanaId(semanaID);
            lucro.setDia(diaAtual);
            lucro.setQuantidade(quantidade);
            listLucro.add(lucro);

        }

        return listLucro;
    }
}
