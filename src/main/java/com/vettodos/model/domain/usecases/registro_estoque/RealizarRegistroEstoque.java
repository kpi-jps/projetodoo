package com.vettodos.model.domain.usecases.registro_estoque;

import com.vettodos.model.domain.entities.produto.Produto;
import com.vettodos.model.domain.entities.registro_estoque.RegistroEstoque;
import com.vettodos.model.domain.entities.registro_estoque.TipoRegistro;
import com.vettodos.model.domain.usecases.produto.ProdutoDAO;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;

public class RealizarRegistroEstoque {
    
    private RegistroEstoqueDAO registroEstoqueDAO;

    public RealizarRegistroEstoque(RegistroEstoqueDAO registroEstoqueDAO) {
        this.registroEstoqueDAO = registroEstoqueDAO;
    }

    private Notificador validaRegistro(RegistroEstoque registroEstoque) {
        Notificador notificador = new Notificador();
        Double total = registroEstoqueDAO.totalEmEstoque(registroEstoque.getProduto().getId());
        if(total == 0 && registroEstoque.getTipoRegistro() == TipoRegistro.SAIDA) notificador.adicionaMsg("Retirada não autorizada, estoque igual a zero! ");
        if(registroEstoque.getQuantidade() == 0) notificador.adicionaMsg("A quantidade não pode ser igual a zero! ");
        if(registroEstoque.getTipoRegistro() == TipoRegistro.SAIDA && total + registroEstoque.getQuantidade() < registroEstoque.getProduto().getMinimoEmEstoque() ) 
            notificador.adicionaMsg("Retirada não permitida para garantir o mínimo em estoque! ");
        return notificador;
    }

    public Double getTotalEmEstoque(Long idProduto) {
        return registroEstoqueDAO.totalEmEstoque(idProduto);
    }
    
    public void registrar(RegistroEstoque registroEstoque) {
        Notificador notificador = validaRegistro(registroEstoque);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        registroEstoqueDAO.registroEstoque(registroEstoque);
    }
}
