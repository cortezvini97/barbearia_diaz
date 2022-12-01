package com.vcinsidedigital.barbearia_diaz.helper;

import com.vcinsidedigital.barbearia_diaz.model.Ano;
import com.vcinsidedigital.barbearia_diaz.model.Mes;

import java.util.List;

public interface IMesDAO
{
    public List<Mes> listar(Ano ano);
    public boolean save(Mes mes);
}
