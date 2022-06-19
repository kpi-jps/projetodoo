package com.vettodos.model.domain.usecases.animal;

import java.util.List;
import java.util.Optional;

import com.vettodos.model.domain.entities.animal.Animal;

public interface AnimalDAO {
    

    List<Animal> buscarPorNome(String nomeAnimal);

    List<Animal> buscarPorTutor(String nomeTutor);

    void salvar(Animal animal);

    void editar(Animal animal);

}
