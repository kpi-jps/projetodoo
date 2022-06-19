package com.vettodos.model.domain.usecases.individuo;

import java.util.List;

import com.vettodos.model.domain.entities.individuo.Usuario;
import com.vettodos.model.domain.usecases.util.EntidadeNaoEncontradaException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Validadores;


public class BuscarUsuario {

    private UsuarioDAO usuarioDAO;

    public BuscarUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario buscarPorEmail(String email) {
        if(!Validadores.validaEmail(email)) throw new EntradaInvalidaException("Email inválido! ");
        return usuarioDAO.buscarPorEmail(email)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado! "));
    }

    public List<Usuario> buscarPorNome(String nome) {
        if(Validadores.nuloOuVazio(nome)) throw new EntradaInvalidaException("Nome nulo ou vazio! ");
        List<Usuario> usuarios = usuarioDAO.buscarPorNome(nome); 
        if(usuarios.isEmpty()) throw new EntidadeNaoEncontradaException("Usuário não encontrado! ");
        return usuarios;
    }
     
}
