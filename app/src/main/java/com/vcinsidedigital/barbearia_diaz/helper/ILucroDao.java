package com.vcinsidedigital.barbearia_diaz.helper;

import com.vcinsidedigital.barbearia_diaz.model.Lucro;
import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.List;

public interface ILucroDao
{
    public boolean salvar(Lucro lucro);
    public boolean atualizar(Lucro lucro);
    public boolean deletar(Lucro lucro);
    public List<Lucro> listAllByDia(Semana semana, String dia);
}
