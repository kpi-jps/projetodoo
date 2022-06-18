package com.vettodos.model.domain.usecases.individuo;

import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.usecases.util.EntidadeJaExistenteException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

public class EditarTutor {
    private TutorDAO tutorDAO;

    public EditarTutor(TutorDAO tutorDAO) {
        this.tutorDAO = tutorDAO;
    }

    private boolean tutorExiste(String cpfAnterior, String cpfNovo) {
        return (!tutorDAO.buscarPorCPF(cpfNovo).isEmpty() && !cpfNovo.equals(cpfAnterior));
    }
    
    private Notificador validaTutor(Tutor tutor) {
        Notificador notificador = new Notificador();
        if(!Validadores.nuloOuVazio(tutor.getTelefone()) && !Validadores.validaNumero(tutor.getTelefone())) notificador.adicionaMsg("Telefone inválido! ");
        if(Validadores.nuloOuVazio(tutor.getEndereco().getLogradouro())) notificador.adicionaMsg("Logradouro não pode ser nulo ou vazio! ");
        if(!Validadores.validaNumero(tutor.getEndereco().getNumero())) notificador.adicionaMsg("Número inválido! ");
        if(!Validadores.validaCEP(tutor.getEndereco().getCep())) notificador.adicionaMsg("CEP inválido! ");
        if(Validadores.nuloOuVazio(tutor.getNome())) notificador.adicionaMsg("Nome não pode ser nulo ou vazio! ");
        if(!Validadores.validaCPF(tutor.getCpf())) notificador.adicionaMsg("CPF inválido! ");
        return notificador;
    }
    
    public void editar(Tutor tutorAnterior, Tutor tutorNovo) {
        if(tutorExiste(tutorAnterior.getCpf(), tutorNovo.getCpf())) 
            throw new EntidadeJaExistenteException("CPF já cadastrado por outro tutor! ");
        Notificador notificador = validaTutor(tutorNovo);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        tutorDAO.editar(tutorNovo);
    }
}
