package com.vettodos.model.domain.usecases.atendimento;

import java.time.LocalDate;
import java.util.List;


import com.vettodos.model.domain.entities.atendimento.Atendimento;


public interface AtendimentoDAO {

    //List<Atendimento> buscarPorAnimal(Animal animal);

    //List<Atendimento> buscarPorVeterinario(Veterinario veterinario);

    List<Atendimento> buscarPorData(LocalDate dataInicial, LocalDate dataFinal);

    void salvar(Atendimento atendimento);



    //void editar(Atendimento atendimento); //implementar depois se for necessário
}
