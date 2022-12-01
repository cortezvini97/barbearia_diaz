package com.vcinsidedigital.barbearia_diaz.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.ArrayList;
import java.util.List;

public class SemanaDao implements ISemanaDao
{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public SemanaDao(Context context)
    {
        DBHelper db = new DBHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }


    @Override
    public List<Semana> listar(Long mesId) {

        String sql = "SELECT * FROM " + DBHelper.TABELA_SEMANAS + " WHERE mesID = " + mesId + ";";

        List<Semana> listSemana = new ArrayList<>();

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()){
            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String semanaStr = c.getString(c.getColumnIndex("semana"));
            @SuppressLint("Range") Long idMes = c.getLong(c.getColumnIndex("mesID"));

            Semana semana = new Semana(id, semanaStr, idMes);
            listSemana.add(semana);

        }

        return listSemana;
    }

    @Override
    public boolean salvar(Semana semana)
    {
        ContentValues cv = new ContentValues();
        cv.put("semana", semana.getSemana());
        cv.put("mesID", semana.getMesId());

        try{
            escreve.insert(DBHelper.TABELA_SEMANAS, null, cv);
            return true;
        }catch (Exception e){
            return false;
        }


    }

    @Override
    public boolean atualizar(Semana semana)
    {
        ContentValues cv = new ContentValues();
        cv.put("semana", semana.getSemana());
        cv.put("mesID", semana.getMesId());
        String[] args = {semana.getId().toString()};
        try{
            escreve.update(DBHelper.TABELA_SEMANAS, cv, "id=?", args);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletar(Semana semana) {
        String[] args = {semana.getId().toString()};
        try {
            escreve.delete(DBHelper.TABELA_SEMANAS, "id=?", args);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
