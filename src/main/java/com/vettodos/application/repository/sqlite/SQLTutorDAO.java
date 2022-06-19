package com.vettodos.application.repository.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vettodos.model.domain.entities.endereco.Cidade;
import com.vettodos.model.domain.entities.endereco.Endereco;
import com.vettodos.model.domain.entities.endereco.Estado;
import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.usecases.individuo.TutorDAO;


public class SQLTutorDAO implements TutorDAO {

    private Tutor retornaTutor(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String cpf = rs.getString("cpf");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        String telefone = rs.getString("telefone");
        Long id_endereco = rs.getLong("id_endereco");
        String logradouro = rs.getString("logradouro");
        String cep = rs.getString("cep");
        String numero = rs.getString("numero");
        String cidade = rs.getString("cidade");
        String estado = rs.getString("estado");
        String complemento = rs.getString("complemento");
        Cidade cidadeEnun = Cidade.getCidade(cidade);
        Estado estadoEnun = Estado.getEstado(estado);
        Endereco endereco = new Endereco(id_endereco, 
            logradouro, cep, 
            numero, 
            cidadeEnun, 
            estadoEnun, 
            complemento);
        return new Tutor(id, nome, cpf, email, telefone, endereco);
    }

    @Override
    public List<Tutor> buscarPorNome(String nome) {
        List<Tutor> tutores = new ArrayList<>();
        String sql = "SELECT t.*, e.logradouro, e.numero, " + 
            "e.complemento, e.cep, e.cidade, e.estado " +
            "FROM tutor t JOIN endereco e ON t.id_endereco = e.id " +
            "WHERE lower(t.nome) LIKE ?;";
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, "%" + nome.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tutor tutor = retornaTutor(rs);
                tutores.add(tutor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tutores;
    }

    @Override
    public Optional<Tutor> buscarPorCPF(String cpf) {
        Tutor tutor = null;
        String sql = "SELECT t.*, e.logradouro, e.numero, " + 
            "e.complemento, e.cep, e.cidade, e.estado " +
            "FROM tutor t JOIN endereco e ON t.id_endereco = e.id " +
            "WHERE t.cpf LIKE ?;";
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tutor = retornaTutor(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(tutor);
    }

    @Override
    public void salvar(Tutor tutor) {
        String sql = "INSERT INTO endereco(" +
            "logradouro, numero, cep, cidade, estado, complemento) " + 
            "VALUES (?, ?, ?, ?, ?, ?); ";
        
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, tutor.getEndereco().getLogradouro());
            ps.setString(2, tutor.getEndereco().getNumero());
            ps.setString(3, tutor.getEndereco().getCep());
            ps.setString(4, tutor.getEndereco().getCidade().getNomeCidade());
            ps.setString(5, tutor.getEndereco().getEstado().getNome());
            ps.setString(6, tutor.getEndereco().getComplemento());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

        sql = "INSERT INTO tutor(" +
            "cpf, nome, email, telefone, id_endereco)" + 
            "VALUES (?, ?, ?, ?, (SELECT id FROM endereco WHERE logradouro = ? AND " +
            "numero = ? AND cep = ?));";
        
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, tutor.getCpf());
            ps.setString(2, tutor.getNome());
            ps.setString(3, tutor.getEmail());
            ps.setString(4, tutor.getTelefone());
            ps.setString(5, tutor.getEndereco().getLogradouro());
            ps.setString(6, tutor.getEndereco().getNumero());
            ps.setString(7, tutor.getEndereco().getCep());
            ps.execute();
        } catch (SQLException e1) {
            e1.printStackTrace();
            sql = "DELETE FROM endereco WHERE logradouro = ? AND " +
            "numero = ? AND cep = ?;";
            try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
                ps.setString(1, tutor.getEndereco().getLogradouro());
                ps.setString(2, tutor.getEndereco().getNumero());
                ps.setString(3, tutor.getEndereco().getCep());
                ps.execute();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }  
    }

    @Override
    public void editar(Tutor tutor) {
        String sql = "UPDATE endereco SET logradouro = ?, numero = ?, " +
            "cep = ?, cidade = ?, estado = ?, complemento = ? " + 
            "WHERE id = ?;";
        
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, tutor.getEndereco().getLogradouro());
            ps.setString(2, tutor.getEndereco().getNumero());
            ps.setString(3, tutor.getEndereco().getCep());
            ps.setString(4, tutor.getEndereco().getCidade().getNomeCidade());
            ps.setString(5, tutor.getEndereco().getEstado().getNome());
            ps.setString(6, tutor.getEndereco().getComplemento());
            ps.setLong(7, tutor.getEndereco().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

        sql = "UPDATE tutor SET cpf = ?, nome = ?, email = ?, " +
            "telefone = ? WHERE id = ?;";
        
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, tutor.getCpf());
            ps.setString(2, tutor.getNome());
            ps.setString(3, tutor.getEmail());
            ps.setString(4, tutor.getTelefone());
            ps.setLong(5, tutor.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
}
