package com.vettodos.model.domain.entities.produto;

import java.util.Objects;

public class Produto {

    private Long id;
    private Categoria categoria;
    private String nome;
    private String descricao;
    private Double minimoEmEstoque;
    private UnidadeMedida unidadeMedida;


    public Produto(Long id, Categoria categoria, String nome, String descricao, UnidadeMedida unidadeMedida, Double minimoEmEstoque) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.minimoEmEstoque = minimoEmEstoque;
    }

    public Produto(Categoria categoria, String nome, String descricao, UnidadeMedida unidadeMedida, Double minimoEmEstoque) {
        this(null, categoria, nome, descricao, unidadeMedida, minimoEmEstoque);
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getMinimoEmEstoque() {
        return this.minimoEmEstoque;
    }

    public void setMinimoEmEstoque(Double minimoEmEstoque) {
        this.minimoEmEstoque = minimoEmEstoque;
    }

    public UnidadeMedida getUnidadeMedida() {
        return this.unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Produto))  return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", categoria='" + getCategoria().getNome() + "'" +
            ", nome='" + getNome() + "'" +
            ", unidadeMedida='" + getUnidadeMedida().getNome() + "'" +
            "}";
    }
}
