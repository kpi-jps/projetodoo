package com.vettodos.model.domain.usecases.individuo;

import java.util.List;
import java.util.Optional;

import com.vettodos.model.domain.entities.individuo.Usuario;

public interface UsuarioDAO {

   List<Usuario> buscarPorNome(String nome);

   Optional<Usuario> buscarPorEmail(String email);

   void salvar(Usuario usuario, String senha);

   void editar(Usuario usuario);

   void trocarSenha(Long id, String novaSenha);

   boolean autenticar(String email, String senha);
   /*
   Esse método só será implementado quando a persistência ocorrer em banco de dados
   void editarSenha(String email, String senha);
   */

   
    
}
