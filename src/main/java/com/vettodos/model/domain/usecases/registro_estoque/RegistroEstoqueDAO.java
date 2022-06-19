package com.vettodos.model.domain.usecases.registro_estoque;


import com.vettodos.model.domain.entities.registro_estoque.RegistroEstoque;


public interface RegistroEstoqueDAO {

    void registroEstoque(RegistroEstoque registro);

    Double totalEmEstoque(Long idProduto);

}

