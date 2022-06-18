package com.vettodos.model.domain.entities.individuo;

import java.util.Objects;

import com.vettodos.model.domain.entities.endereco.Endereco;

public class Tutor {

    private Long id; //chave primário no bd
    private String nome;
    private String cpf; 
    private String email;
    private String telefone;
    private Endereco endereco;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tutor(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Tutor( String nome, String cpf, String email, String telefone, Endereco endereco) {
        this(null, nome, cpf, email, telefone, endereco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return id.equals(tutor.id) && nome.equals(tutor.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", endereço='" + getEndereco().getLogradouro() + ", " +
            getEndereco().getNumero() + " - CEP: " +
            getEndereco().getCep()  + "'" +
            " }";
    }

}
