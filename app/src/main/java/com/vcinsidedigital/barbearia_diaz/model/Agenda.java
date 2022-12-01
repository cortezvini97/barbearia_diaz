package com.vcinsidedigital.barbearia_diaz.model;

import java.io.Serializable;

public class Agenda implements Serializable
{
    private Long id;
    private Long semanaId;
    private String dia;
    private String data;
    private String hora;
    private String cliente;
    private double preco;
    private String obs;

    public Agenda() {
    }

    public Agenda(Long id, Long semanaId, String dia, String data, String hora, String cliente, double preco, String obs) {
        this.id = id;
        this.semanaId = semanaId;
        this.dia = dia;
        this.data = data;
        this.hora = hora;
        this.cliente = cliente;
        this.preco = preco;
        this.obs = obs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSemanaId() {
        return semanaId;
    }

    public void setSemanaId(Long semanaId) {
        this.semanaId = semanaId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
