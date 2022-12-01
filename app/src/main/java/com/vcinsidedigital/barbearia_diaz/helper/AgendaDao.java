package com.vcinsidedigital.barbearia_diaz.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vcinsidedigital.barbearia_diaz.model.Agenda;
import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.ArrayList;
import java.util.List;

public class AgendaDao implements IAgendaDao{

    private SQLiteDatabase le;
    private SQLiteDatabase escreve;

    public AgendaDao(Context context){
        DBHelper db = new DBHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public List<Agenda> listar(Semana semana)
    {
        List<Agenda> list = new ArrayList<Agenda>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_AGENDA + " WHERE semana_id = " + semana.getId() + ";";

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext())
        {
            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") Long semana_id = c.getLong(c.getColumnIndex("semana_id"));
            @SuppressLint("Range") String dia = c.getString(c.getColumnIndex("dia"));
            @SuppressLint("Range") String data = c.getString(c.getColumnIndex("data"));
            @SuppressLint("Range") double preco = c.getDouble(c.getColumnIndex("preco"));
            @SuppressLint("Range") String hora = c.getString(c.getColumnIndex("hora"));
            @SuppressLint("Range") String obs = c.getString(c.getColumnIndex("obs"));
            @SuppressLint("Range") String cliente = c.getString(c.getColumnIndex("cliente"));

            Agenda agenda = new Agenda();
            agenda.setId(id);
            agenda.setDia(dia);
            agenda.setData(data);
            agenda.setHora(hora);
            agenda.setPreco(preco);
            agenda.setObs(obs);
            agenda.setSemanaId(semana_id);
            agenda.setCliente(cliente);

            list.add(agenda);
        }

        return list;
    }

    @Override
    public boolean save(Agenda agenda)
    {
        ContentValues cv = new ContentValues();
        cv.put("dia", agenda.getDia());
        cv.put("data", agenda.getData());
        cv.put("preco", agenda.getPreco());
        cv.put("obs", agenda.getObs());
        cv.put("semana_id", agenda.getSemanaId());
        cv.put("cliente", agenda.getCliente());
        cv.put("hora", agenda.getHora());

        try{
            escreve.insert(DBHelper.TABELA_AGENDA, null, cv);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean atualizar(Agenda agenda)
    {
        ContentValues cv = new ContentValues();
        cv.put("dia", agenda.getDia());
        cv.put("data", agenda.getData());
        cv.put("preco", agenda.getPreco());
        cv.put("obs", agenda.getObs());
        cv.put("semana_id", agenda.getSemanaId());
        cv.put("cliente", agenda.getCliente());
        cv.put("hora", agenda.getHora());

        String[] args = {agenda.getId().toString()};

        try{
            escreve.update(DBHelper.TABELA_AGENDA, cv, "id=?", args);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean deletar(Agenda agenda) {
        String[] args = {agenda.getId().toString()};
        try{
            escreve.delete(DBHelper.TABELA_AGENDA, "id=?", args);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
