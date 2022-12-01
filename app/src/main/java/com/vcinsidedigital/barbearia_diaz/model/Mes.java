package com.vcinsidedigital.barbearia_diaz.model;

import java.io.Serializable;

public class Mes implements Serializable
{
    private Long id;
    private String mes;
    private Long ano;

    public Mes(){}

    public Mes(String mes, Long ano){
        this.mes = mes;
        this.ano = ano;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getMes()
    {
        return mes;
    }

    public void setMes(String mes)
    {
        this.mes = mes;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }
}