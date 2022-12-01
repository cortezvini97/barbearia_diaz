package com.vcinsidedigital.barbearia_diaz.helper;

import com.vcinsidedigital.barbearia_diaz.model.Despesa;
import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.List;

public interface IDespesaDao
{
    public boolean salvar(Despesa despesa);
    public boolean update(Despesa despesa);
    public boolean deletar(Despesa despesa);
    public List<Despesa> listar(Semana semana);
}
