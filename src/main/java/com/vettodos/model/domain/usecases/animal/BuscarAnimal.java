package com.vettodos.model.domain.usecases.animal;

import java.util.List;

import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.usecases.util.EntidadeNaoEncontradaException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Validadores;

public class BuscarAnimal {
    private AnimalDAO animalDAO;

    public Animal buscarPorID(Integer id) {
        return animalDAO.buscarPorID(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado!"));
    }
    
    public BuscarAnimal(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }

    public List<Animal> buscarPorNome(String nome) {
        if(Validadores.nuloOuVazio(nome)) throw new EntradaInvalidaException("Nome nulo ou vazio! ");
        if(animalDAO.buscarPorNome(nome).isEmpty()) throw new EntidadeNaoEncontradaException("Animal não encontrado! ");
        return animalDAO.buscarPorNome(nome); 
    }

    public List<Animal> buscarPorTutor(Tutor tutor) {
        if(Validadores.nuloOuVazio(tutor)) throw new EntradaInvalidaException("Tutor não informado! ");
        if(animalDAO.buscarPorTutor(tutor).isEmpty()) throw new EntidadeNaoEncontradaException("Animal não encontrado! ");
        return animalDAO.buscarPorTutor(tutor);
    }

}
