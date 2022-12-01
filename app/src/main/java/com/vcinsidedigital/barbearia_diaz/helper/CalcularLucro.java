package com.vcinsidedigital.barbearia_diaz.helper;

import android.util.Log;

import com.vcinsidedigital.barbearia_diaz.model.Lucro;

import java.util.List;

public class CalcularLucro
{
    List<Lucro> list;

    public CalcularLucro(List<Lucro> list)
    {
        this.list = list;
    }

    public double calcular()
    {
        double lucroValue = 0.00;
        for (Lucro lucro: this.list)
        {
            lucroValue += lucro.getTotal();
        }
        return lucroValue;
    }

}
