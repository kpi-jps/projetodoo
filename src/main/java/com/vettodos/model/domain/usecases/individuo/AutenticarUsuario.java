package com.vettodos.model.domain.usecases.individuo;

import com.vettodos.model.domain.entities.individuo.Usuario;
import com.vettodos.model.domain.usecases.util.AutenticacaoException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

public class AutenticarUsuario {
    
    private UsuarioDAO usuarioDAO;
    private BuscarUsuario buscarUsuario;

    public AutenticarUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        buscarUsuario = new BuscarUsuario(usuarioDAO);
    }

    private Notificador validaEmailESenha(String email, String senha) {
        Notificador notificador = new Notificador();
        if(Validadores.nuloOuVazio(email)) notificador.adicionaMsg("Email nulo ou vazio! ");
        if(!Validadores.validaEmail(email)) notificador.adicionaMsg("E-mail inválido! ");
        if(Validadores.nuloOuVazio(senha)) notificador.adicionaMsg("Senha nula ou vazia! ");
        return notificador;
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = null;
        Notificador notificador = validaEmailESenha(email, senha);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        try {
            usuario = buscarUsuario.buscarPorEmail(email);
        } catch (Exception e) {
            throw e;
        }
        if(!usuario.isStatus()) throw new AutenticacaoException("Usuário não ativo, acesso negado! ");
        if(!usuarioDAO.autenticar(email, senha)) throw new AutenticacaoException("Usuário não autenticado, acesso negado! ");
        return usuario;
    }

}
