package com.vettodos.model.domain.usecases.animal;

import java.util.List;
import java.util.Optional;

import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.entities.individuo.Tutor;

public interface AnimalDAO {
    
    List<Animal> buscarTodos();

    List<Animal> buscarPorNome(String nome);

    List<Animal> buscarPorTutor(Tutor tutor);

    Optional <Animal> buscarPorID(Integer id);

    void salvar(Animal animal);

    void editar(Animal animal);

}
