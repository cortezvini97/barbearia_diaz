package com.vcinsidedigital.barbearia_diaz.model;

import java.io.Serializable;

public class Semana implements Serializable
{
    private Long id;
    private String semana;
    private Long mesId;

    public Semana() {
    }

    public Semana(Long id, String semana, Long mesId) {
        this.id = id;
        this.semana = semana;
        this.mesId = mesId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public Long getMesId() {
        return mesId;
    }

    public void setMesId(Long mesId) {
        this.mesId = mesId;
    }
}
