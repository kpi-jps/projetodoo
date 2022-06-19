package com.vettodos.model.domain.entities.registro_estoque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.vettodos.model.domain.entities.produto.Produto;

public class RegistroEstoque {
    
    private Long id;
    private Produto produto;
    private LocalDateTime data;
    private Double quantidade;
    private TipoRegistro tipoRegistro;


    public RegistroEstoque( Produto produto, Double quantidade, TipoRegistro tipoRegistro) {
        id = null;
        data = LocalDateTime.now();
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipoRegistro = tipoRegistro;
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
        if(tipoRegistro == TipoRegistro.ENTRADA) return Math.abs(this.quantidade);
        return (-1.0) * Math.abs(this.quantidade);
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public TipoRegistro getTipoRegistro() {
        return this.tipoRegistro;
    }

    public void setTipoRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
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
            ", tipo='" + getTipoRegistro().getTipo() + "'" +
            "}";
    }

}
