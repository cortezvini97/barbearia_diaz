package com.vcinsidedigital.barbearia_diaz.helper;

import com.vcinsidedigital.barbearia_diaz.model.Ano;
import com.vcinsidedigital.barbearia_diaz.model.Mes;

import java.util.List;

public interface IAnoDao
{
    public List<Ano> listar();
    public boolean salvar(Ano ano);
    public Ano getAno(Long ano);
}
