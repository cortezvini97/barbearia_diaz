package com.vcinsidedigital.barbearia_diaz.model;

import java.io.Serializable;

public class Notinha implements Serializable
{
    private String telefone;
    private String whatsapp;
    private String file;
    private boolean carimbado;
    private Agenda agenda;

    public Notinha() {
    }

    public Notinha(String telefone, String whatsapp, String file, boolean carimbado, Agenda agenda) {
        this.telefone = telefone;
        this.whatsapp = whatsapp;
        this.carimbado = carimbado;
        this.agenda = agenda;
        this.file = file;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }

    public boolean isCarimbado() {
        return carimbado;
    }

    public void setCarimbado(boolean carimbado) {
        this.carimbado = carimbado;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
