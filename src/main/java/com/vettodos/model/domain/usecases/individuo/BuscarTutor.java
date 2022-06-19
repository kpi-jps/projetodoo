package com.vettodos.model.domain.usecases.individuo;

import java.util.List;

import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.usecases.util.EntidadeNaoEncontradaException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Validadores;



public class BuscarTutor {

    private TutorDAO tutorDAO;

    public BuscarTutor(TutorDAO tutorDAO) {
        this.tutorDAO = tutorDAO;
    }
    public Tutor buscarPorCPF(String cpf) {
        if(!Validadores.validaCPF(cpf)) throw new EntradaInvalidaException("CPF inválido! ");
        return tutorDAO.buscarPorCPF(cpf)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Tutor não encontrado! "));
    }

    public List<Tutor> buscarPorNome(String nome) {
        if(Validadores.nuloOuVazio(nome)) throw new EntradaInvalidaException("Nome nulo ou vazio! ");
        List<Tutor> tutores = tutorDAO.buscarPorNome(nome);
        if(tutores.isEmpty()) throw new EntidadeNaoEncontradaException("Tutor não encontrado! ");
        return tutores;
    }
}
