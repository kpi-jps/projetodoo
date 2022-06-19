package com.vettodos.model.domain.entities.animal;

import java.time.LocalDate;
import java.util.Objects;

import com.vettodos.model.domain.entities.individuo.Tutor;


public class Animal {

    private Long id;
    private String nome;
    private Especie especie;
    private Raca raca;
    private Porte porte;
    private String sexo;
    private Double peso;
    private int anoNascimento;
    private Boolean status;
    private Tutor tutor;


    public Animal(Long id, String nome, Especie especie, Raca raca, Porte porte, String sexo, Double peso, int anoNascimento, Boolean status, Tutor tutor) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.sexo = sexo;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.status = status;
        this.tutor = tutor;
    }

    public Animal(String nome, Especie especie, Raca raca, Porte porte, String sexo, Double peso, int anoNascimento, Boolean status, Tutor tutor) {
        this(null, nome, especie, raca, porte, sexo, peso, anoNascimento, status, tutor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getanoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimenoInteger) {
        this.anoNascimento = anoNascimenoInteger;
    }

    public Integer calculaIdade () {
        return anoNascimento - LocalDate.now().getYear();
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id.equals(animal.id) && nome.equals(animal.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", especie='" + getEspecie().getNomeEspecie() + "'" +
            ", raca ='" + getRaca().getNomeRaca() + "'" +
            ", tutor='" + getTutor().getNome() + "'" +
            "}";
    }
}

    
    
