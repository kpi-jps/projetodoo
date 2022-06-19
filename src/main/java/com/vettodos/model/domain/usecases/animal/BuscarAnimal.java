package com.vettodos.model.domain.usecases.animal;

import java.util.List;

import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.usecases.util.EntidadeNaoEncontradaException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Validadores;

public class BuscarAnimal {
    private AnimalDAO animalDAO;
    
    public BuscarAnimal(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }

    public List<Animal> buscarPorNome(String nomeAnimal) {
        if(Validadores.nuloOuVazio(nomeAnimal)) throw new EntradaInvalidaException("Nome nulo ou vazio! ");
        List<Animal> animais = animalDAO.buscarPorNome(nomeAnimal);
        if(animais.isEmpty()) throw new EntidadeNaoEncontradaException("Animal não encontrado! ");
        return animais; 
    }

    public List<Animal> buscarPorTutor(String nomeTutor) {
        if(Validadores.nuloOuVazio(nomeTutor)) throw new EntradaInvalidaException("Tutor não informado! ");
        List<Animal> animais = animalDAO.buscarPorTutor(nomeTutor);
        if(animais.isEmpty()) throw new EntidadeNaoEncontradaException("Animal não encontrado! ");
        return animais;
    }

}
