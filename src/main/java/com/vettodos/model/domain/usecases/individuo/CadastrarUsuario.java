package com.vettodos.model.domain.usecases.individuo;

import com.vettodos.model.domain.entities.individuo.Usuario;
import com.vettodos.model.domain.entities.individuo.Veterinario;
import com.vettodos.model.domain.usecases.util.EntidadeJaExistenteException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

public class CadastrarUsuario {

    private UsuarioDAO usuarioDAO;

    public CadastrarUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    private boolean emailExiste(String email) {
        return !usuarioDAO.buscarPorEmail(email).isEmpty();
    }

    private Notificador validaUsuario(Usuario usuario) {
        Notificador notificador = new Notificador();
        if(Validadores.nuloOuVazio(usuario.getNome())) notificador.adicionaMsg("Nome não pode ser nulo ou vazio! ");
        if(!Validadores.validaEmail(usuario.getEmail())) notificador.adicionaMsg("E-mail inválido! ");
        if(usuario instanceof Veterinario) {
            Veterinario veterinario = (Veterinario) usuario;
            if(!Validadores.validaNumero(veterinario.getRegistroProfissional())) 
                notificador.adicionaMsg("Registro profissional inválido! ");
        }
        return notificador;
    }

    public void cadastrar(Usuario usuario, String hashSenha) {
        Notificador notificador = validaUsuario(usuario);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        if(emailExiste(usuario.getEmail())) throw new EntidadeJaExistenteException("E-mail já cadastrado por outro usuário! ");
        usuarioDAO.salvar(usuario, hashSenha);
    }

}
