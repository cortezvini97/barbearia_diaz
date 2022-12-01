package com.vcinsidedigital.barbearia_diaz.helper;

import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.List;

public interface ISemanaDao
{
    public List<Semana> listar(Long mesId);
    public boolean salvar(Semana semana);
    public boolean atualizar(Semana semana);
    public boolean deletar(Semana semana);
}
