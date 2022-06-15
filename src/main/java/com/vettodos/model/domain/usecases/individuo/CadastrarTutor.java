package com.vettodos.model.domain.usecases.individuo;

import com.vettodos.model.domain.entities.endereco.Endereco;
import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.usecases.util.EntidadeJaExistenteException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

public class CadastrarTutor {
    
    private TutorDAO tutorDAO;

    public CadastrarTutor(TutorDAO tutorDAO) {
        this.tutorDAO = tutorDAO;
    }

    private boolean tutorExiste(String cpf) {
        return (tutorDAO.buscarPorCPF(cpf).isPresent());
    }

    private boolean validaEndereco(Endereco endereco) {
        return Validadores.validaCEP(endereco.getCep()) &&
            !Validadores.nuloOuVazio(endereco.getLogradouro()) &&
            Validadores.validaNumero(endereco.getNumero());
    }
    
    private Notificador validaTutor(Tutor tutor) {
        Notificador notificador = new Notificador();
        if(!validaEndereco(tutor.getEndereco())) notificador.adicionaMsg("Endereço incompleto, é necessário preencher no mínimo CEP, logradouro e número! ");
        if(Validadores.nuloOuVazio(tutor.getNome())) notificador.adicionaMsg("Nome não pode ser nulo ou vazio! ");
        if(!Validadores.validaCPF(tutor.getCpf())) notificador.adicionaMsg("CPF inválido! ");
        return notificador;
    }
    
    public void cadastrar(Tutor tutor) {
        Notificador notificador = validaTutor(tutor);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        if(tutorExiste(tutor.getCpf())) throw new EntidadeJaExistenteException("Tutor já cadastrado!");
        tutorDAO.salvar(tutor);
    }

}
