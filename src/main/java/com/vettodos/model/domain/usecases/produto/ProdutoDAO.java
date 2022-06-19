package com.vettodos.model.domain.usecases.produto;

import java.util.List;

import com.vettodos.model.domain.entities.produto.Categoria;
import com.vettodos.model.domain.entities.produto.Produto;


public interface ProdutoDAO {
    
    List<Produto> buscarPorCategoria(Categoria categoria);

    void salvar(Produto produto);

    void editar(Produto produto);

}
