package com.vettodos.model.domain.entities.individuo;

import java.util.Objects;

public class Usuario {
    private Long id; // chave primária no bd
    private String email; //atributo único no bd
    private String nome; // não pode ser nulo
    private Credencial credencial;
    //private String cpf; //tem relevância?
    private String telefone;
    private boolean status;

    
    public Usuario(String email, String nome, String telefone, boolean status) {
        this(null, email, nome, telefone, status);
    }
    
    public Usuario(Long id, String email, String nome, String telefone, boolean status) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.status = status;
        credencial = Credencial.ADMINISTRADOR;
    }

    public Long getId() {
        return this.id;
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

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public Credencial getCredencial() {
        return credencial;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "{" +
            "id='" + getId() + "'" +
            " nome='" + getNome() + "'" +
            ", status='" + isStatus() + "'" +
            ", credencial='" + getCredencial().getNomeCredencial() + "'" +
            "}";
    }

}
