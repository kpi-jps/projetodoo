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

    private Notificador validaUsuario(Usuario usuarioAnterior, Usuario usuarioNovo) {
        Notificador notificador = new Notificador();
        if(!Validadores.nuloOuVazio(usuarioNovo.getTelefone()) && !Validadores.validaNumero(usuarioNovo.getTelefone())) notificador.adicionaMsg("Telefone inválido! ");
        if(Validadores.nuloOuVazio(usuarioNovo.getNome())) notificador.adicionaMsg("Nome não pode ser nulo ou vazio! ");
        if(!Validadores.validaEmail(usuarioNovo.getEmail())) notificador.adicionaMsg("E-mail precisa ser válido! ");
        if(usuarioNovo instanceof Veterinario) {
            Veterinario veterinario = (Veterinario) usuarioNovo;
            if(Validadores.nuloOuVazio(veterinario.getRegistroProfissional())) 
                notificador.adicionaMsg("Registro profissional não pode ser nulo ou vazio! ");
        }
        return notificador;
    }

    public void editar(Usuario usuarioAnterior, Usuario usuarioNovo) {
        if(emailExiste(usuarioAnterior.getEmail(), usuarioNovo.getEmail())) 
            throw new EntidadeJaExistenteException("E-mail já cadastrado por outro usuário! ");
        Notificador notificador = validaUsuario(usuarioAnterior, usuarioNovo);
        if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
        usuarioDAO.editar(usuarioNovo);
    }
}
