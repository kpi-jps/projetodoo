package com.vettodos.model.domain.entities.produto;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistroEstoque {
    
    private Long id;
    private Produto produto;
    private LocalDateTime data;
    private Double quantidade;

    public RegistroEstoque (Produto produto, Double quantidade) {
        data = LocalDateTime.now(); 
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RegistroEstoque)) return false;
        RegistroEstoque registroEstoque = (RegistroEstoque) o;
        return Objects.equals(id, registroEstoque.id) && Objects.equals(data, registroEstoque.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        return "{" +
            " produto='" + getProduto().getNome() + "'" +
            ", data='" + getData() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            "}";
    }

}
