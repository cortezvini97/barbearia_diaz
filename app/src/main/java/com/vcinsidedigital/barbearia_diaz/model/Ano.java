package com.vcinsidedigital.barbearia_diaz.model;

import java.io.Serializable;

public class Ano implements Serializable
{
    private Long id;
    private Long ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }
}
