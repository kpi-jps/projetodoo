package com.vettodos.model.domain.usecases.produto;

import java.util.List;

import com.vettodos.model.domain.entities.produto.Categoria;
import com.vettodos.model.domain.entities.produto.Produto;
import com.vettodos.model.domain.usecases.util.EntidadeNaoEncontradaException;

public class BuscarProduto {

    private ProdutoDAO produtoDAO;

    public BuscarProduto(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }


    public List<Produto> buscarPorCategoria(Categoria categoria) {
        if(produtoDAO.buscarPorCategoria(categoria).isEmpty()) throw new EntidadeNaoEncontradaException("Produto não encontrado! ");
        return produtoDAO.buscarPorCategoria(categoria);
    }

}
