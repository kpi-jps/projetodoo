package com.vettodos.model.domain.usecases.atendimento;

import com.vettodos.model.domain.entities.atendimento.Atendimento;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

public class RealizarAtendimento {

    private AtendimentoDAO atendimentoDAO;

    private Notificador validarAtendimento(Atendimento atendimento) {
        Notificador notificador = new Notificador();
        if(Validadores.nuloOuVazio(atendimento.getAnimal())) notificador.adicionaMsg("Animal não pode ser nulo");
        if(Validadores.nuloOuVazio(atendimento.getVeterinario())) notificador.adicionaMsg("Veterinário não pode ser nulo");
        return notificador;
    }

    public RealizarAtendimento(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public void cadastrar(Atendimento atendimento) {
        Notificador notificador = validarAtendimento(atendimento);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        atendimentoDAO.salvar(atendimento);
    }

}
