package com.vettodos.model.domain.entities.atendimento;

import java.time.LocalDateTime;
import java.util.Objects;

import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.entities.individuo.Veterinario;

public class Atendimento {

    private Long id;
    private LocalDateTime data;
    private Animal animal;
    private Veterinario veterinario;
    private String queixa;
    private String diagnostico;
    private String receituario;



    public Atendimento(Long id, LocalDateTime data, Animal animal, Veterinario veterinario, String queixa, String diagnostico, String receituario) {
        this.id = id;
        this.data = data;
        this.animal = animal;
        this.veterinario = veterinario;
        this.queixa = queixa;
        this.diagnostico = diagnostico;
        this.receituario = receituario;
    }

    public Atendimento(Animal animal, Veterinario veterinario, String queixa, String diagnostico, String receituario) {
        this(null, LocalDateTime.now(), animal, veterinario, queixa, diagnostico, receituario);
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Animal getAnimal() {
        return animal;
    }
    
    public Veterinario getVeterinario() {
        return veterinario;
    }

    public String getQueixa() {
        return queixa;
    }

    public void setQueixa(String queixa) {
        this.queixa = queixa;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getReceituario() {
        return receituario;
    }

    public void setReceituario(String receituario) {
        this.receituario = receituario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Atendimento)) return false;
        Atendimento atendimento = (Atendimento) o;
        return Objects.equals(id, atendimento.id) && Objects.equals(data, atendimento.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{" +
            "id='" + getId() + "'" +
            "data='" + getData() + "'" +
            ", animal='" + getAnimal().getNome() + "'" +
            ", veterinario='" + getVeterinario().getNome() + "'" +
            "}";
    }
}
