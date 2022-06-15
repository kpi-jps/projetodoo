package com.vettodos.model.domain.usecases.individuo;

import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Validadores;

public class EditarSenhaUsuario {

    private UsuarioDAO usuarioDAO;

    public EditarSenhaUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void editar(Long id, String novaSenha) {
        if(Validadores.nuloOuVazio(novaSenha)) throw new EntradaInvalidaException("A nova senha não pode ser nula ou vazia! ");
        usuarioDAO.trocarSenha(id, novaSenha);
    }

}
