package com.vcinsidedigital.barbearia_diaz.helper;


import com.vcinsidedigital.barbearia_diaz.model.Agenda;
import com.vcinsidedigital.barbearia_diaz.model.Semana;

import java.util.List;

public interface IAgendaDao
{
    public List<Agenda> listar(Semana semana);
    public boolean save(Agenda agenda);
    public boolean atualizar(Agenda agenda);
    public boolean deletar(Agenda agenda);
}