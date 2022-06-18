package com.vettodos.model.domain.usecases.individuo;

import com.vettodos.model.domain.entities.individuo.Usuario;
import com.vettodos.model.domain.entities.individuo.Veterinario;
import com.vettodos.model.domain.usecases.util.EntidadeJaExistenteException;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

public class EditarUsuario {
    private UsuarioDAO usuarioDAO;

    public EditarUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    private boolean emailExiste(String emailAnterior, String emailNovo) {
        return (!usuarioDAO.buscarPorEmail(emailNovo).isEmpty() && !emailNovo.equals(emailAnterior));
    }

    private Notificador validaUsuario(Usuario usuario) {
        Notificador notificador = new Notificador();
        if(!Validadores.nuloOuVazio(usuario.getTelefone()) && !Validadores.validaNumero(usuario.getTelefone())) notificador.adicionaMsg("Telefone inválido! ");
        if(Validadores.nuloOuVazio(usuario.getNome())) notificador.adicionaMsg("Nome não pode ser nulo ou vazio! ");
        if(!Validadores.validaEmail(usuario.getEmail())) notificador.adicionaMsg("E-mail inválido! ");
        if(usuario instanceof Veterinario) {
            Veterinario veterinario = (Veterinario) usuario;
            if(Validadores.nuloOuVazio(usuario.getTelefone()) || !Validadores.validaNumero(veterinario.getRegistroProfissional())) 
                notificador.adicionaMsg("Registro profissional inválido! ");
        }
        return notificador;
    }

    public void editar(Usuario usuarioAnterior, Usuario usuarioNovo) {
        if(emailExiste(usuarioAnterior.getEmail(), usuarioNovo.getEmail())) 
            throw new EntidadeJaExistenteException("E-mail já cadastrado por outro usuário! ");
        Notificador notificador = validaUsuario(usuarioNovo);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        usuarioDAO.editar(usuarioNovo);
    }
}
