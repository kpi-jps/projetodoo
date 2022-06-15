package com.vettodos.model.domain.usecases.individuo;

import java.util.List;
import java.util.Optional;

import com.vettodos.model.domain.entities.individuo.Tutor;

public interface TutorDAO {

    List<Tutor> buscarPorNome(String nome);

    Optional<Tutor> buscarPorCPF(String cpf);

    void salvar(Tutor usuario);

    void editar(Tutor usuario);
}
