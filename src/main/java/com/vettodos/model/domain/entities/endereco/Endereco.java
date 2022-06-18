package com.vettodos.model.domain.entities.endereco;

import java.util.Objects;

public class Endereco {
    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private Cidade cidade;
    private Estado estado;
    private String complemento;

    public Endereco(Long id, String logradouro, String cep, String numero, Cidade cidade, Estado estado, String complemento) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
    }

    public Endereco(String logradouro, String cep, String numero, Cidade cidade, Estado estado, String complemento) {
        this(null, logradouro, cep, numero, cidade, estado, complemento);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "{" +
            " logradouro='" + getLogradouro() + "'" +
            ", cep='" + getCep() + "'" +
            ", numero='" + getNumero() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado().getSigla() + "'" +
            ", complemento='" + getComplemento() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }




}
