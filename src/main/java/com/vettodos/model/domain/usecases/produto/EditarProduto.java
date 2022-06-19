package com.vettodos.model.domain.usecases.produto;

import com.vettodos.model.domain.entities.produto.Produto;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

public class EditarProduto {
    private ProdutoDAO produtoDAO;

    public EditarProduto(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }
    
    private Notificador validaProduto(Produto produto) {
        Notificador notificador = new Notificador();
        if(Validadores.nuloOuVazio(produto.getNome())) notificador.adicionaMsg("Nome não pode ser nulo ou vazio! ");
        if(Validadores.nuloOuVazio(produto.getCategoria())) notificador.adicionaMsg("Categoria não pode ser nulo ou vazio! ");
        if(Validadores.nuloOuVazio(produto.getUnidadeMedida())) notificador.adicionaMsg("Unidade de medida não pode ser nula ou vazia!");
        if(Validadores.nuloOuVazio(produto.getDescricao())) notificador.adicionaMsg("Descrição não pode ser nula ou vazia!");
        return notificador;
    }
    
    public void editar(Produto produto) {
        Notificador notificador = validaProduto(produto);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        produtoDAO.editar(produto);
    }
}
