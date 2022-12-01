package com.vcinsidedigital.barbearia_diaz.model;

import java.io.Serializable;

public class Lucro implements Serializable
{

    private Long id;
    private String titulo;
    private double valor;
    private double gastoCorte;
    private String dia;
    private Long semanaId;
    private Long quantidade;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Long getSemanaId() {
        return semanaId;
    }

    public void setSemanaId(Long semanaId) {
        this.semanaId = semanaId;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public double getGastoCorte() {
        return gastoCorte;
    }

    public void setGastoCorte(double gastoCorte) {
        this.gastoCorte = gastoCorte;
    }

    public double getTotal()
    {
        double total_gastos = this.gastoCorte * this.quantidade;
        double total_lucro = this.valor * this.quantidade;

        return  total_lucro - total_gastos;
    }
}
