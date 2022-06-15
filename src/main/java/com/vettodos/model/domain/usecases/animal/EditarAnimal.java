package com.vettodos.model.domain.usecases.animal;

import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

public class EditarAnimal {

    private AnimalDAO animalDAO;

    public EditarAnimal(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }
    
    private Notificador validaAnimal(Animal animal) {
        Notificador notificador = new Notificador();
        if(Validadores.nuloOuVazio(animal.getNome())) notificador.adicionaMsg("Nome não pode ser nulo ou vazio! ");
        if(Validadores.nuloOuVazio(animal.getTutor())) notificador.adicionaMsg("Tutor não pode ser nulo ou vazio! ");
        if(Validadores.nuloOuVazio(animal.getEspecie())) notificador.adicionaMsg("Espécie não pode ser nula ou vazia!");
        if(Validadores.nuloOuVazio(animal.getRaca())) notificador.adicionaMsg("Raça não pode ser nula ou vazia!");
        return notificador;
    }

    public void editar(Animal animal) {
        Notificador notificador = validaAnimal(animal);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        animalDAO.editar(animal);
    }
}
