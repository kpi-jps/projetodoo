package com.vettodos.model.domain.usecases.animal;


import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.usecases.util.EntidadeJaExistenteException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;


public class CadastrarAnimal {
    
    private AnimalDAO animalDAO;

    public CadastrarAnimal(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }
    
    private boolean animalExiste(Animal animal) {
        if(animalDAO.buscarPorNome(animal.getNome()).contains(animal)) return true;
        return false;
    }

    private Notificador validaAnimal(Animal animal) {
        Notificador notificador = new Notificador();
        if(Validadores.nuloOuVazio(animal.getNome())) notificador.adicionaMsg("Nome não pode ser nulo ou vazio! ");
        if(Validadores.nuloOuVazio(animal.getTutor())) notificador.adicionaMsg("Tutor não pode ser nulo ou vazio! ");
        if(Validadores.nuloOuVazio(animal.getEspecie())) notificador.adicionaMsg("Espécie não pode ser nula ou vazia!");
        if(Validadores.nuloOuVazio(animal.getRaca())) notificador.adicionaMsg("Raça não pode ser nula ou vazia!");
        return notificador;
    }
    
    public void cadastrar(Animal animal) {
        Notificador notificador = validaAnimal(animal);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        if(animalExiste(animal)) throw new EntidadeJaExistenteException("Animal já cadastrado!");
        animalDAO.salvar(animal);
    }
}
