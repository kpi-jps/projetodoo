package com.vettodos.application.repository.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vettodos.model.domain.entities.individuo.Credencial;
import com.vettodos.model.domain.entities.individuo.Usuario;
import com.vettodos.model.domain.entities.individuo.Veterinario;
import com.vettodos.model.domain.usecases.individuo.UsuarioDAO;
import com.vettodos.model.domain.usecases.util.Encripitador;



public class SQLUsuarioDAO implements UsuarioDAO {

    private Usuario retornaUsuario(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String telefone = rs.getString("telefone");
        String credencial = rs.getString("credencial");
        int status = rs.getInt("status");
        String registroProfissional = rs.getString("registro_profissional");
        if(credencial.equals(Credencial.VETERINARIO.getNomeCredencial()) && registroProfissional == null) {
            if(status == 0) return new Veterinario(id, nome, email, telefone, false, registroProfissional);
            return new Veterinario(id, nome, email, telefone, true, registroProfissional);
        } 
        if(status == 0) return new Usuario(id, nome, email, telefone, false);
        return  new Usuario(id, nome, email, telefone, true);

    }

    @Override
    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE nome = ?;";
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = retornaUsuario(rs);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email LIKE ?;";
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuario = retornaUsuario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(usuario);
    }

    @Override
    public void salvar(Usuario usuario, String senha) {
        String sql = "INSERT INTO usuario("+
            "email, hash_senha, nome, telefone, credencial, registro_profissional)" + 
            "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, Encripitador.encripitar(senha));
            ps.setString(3, usuario.getNome());
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getCredencial().getNomeCredencial());
            if(usuario instanceof Veterinario) ps.setString(6, ((Veterinario)usuario).getRegistroProfissional());
            else ps.setNull(6, Types.VARCHAR);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    @Override
    public void editar(Usuario usuario) {
        String sql = "UPDATE usuario SET email = ?, nome = ?, "+
            "telefone = ?, credencial = ?, registro_profissional = ?" + 
            "WHERE id = ?;";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getCredencial().getNomeCredencial());
            if(usuario instanceof Veterinario) ps.setString(5, ((Veterinario)usuario).getRegistroProfissional());
            else ps.setNull(5, Types.VARCHAR);
            ps.setLong(6, usuario.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    @Override
    public void trocarSenha(Long id, String novaSenha) {
        String sql = "UPDATE usuario SET hash_senha = ? WHERE id = ?;";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, Encripitador.encripitar(novaSenha));
            ps.setLong(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    @Override
    public Optional<Usuario> autenticar(String email, String senha) {
        Usuario usuario = null;
        String hashSenha = null;
        String sql = "SELECT * FROM usuario WHERE email LIKE ?;";
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuario = retornaUsuario(rs);
                hashSenha = rs.getString("hash_senha");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(usuario != null && Encripitador.checarSenha(senha, hashSenha)) 
            return Optional.ofNullable(usuario);
        return Optional.empty();
    }

    
}
