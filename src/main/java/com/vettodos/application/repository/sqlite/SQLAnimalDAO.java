package com.vettodos.application.repository.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.entities.animal.Especie;
import com.vettodos.model.domain.entities.animal.Porte;
import com.vettodos.model.domain.entities.animal.Raca;
import com.vettodos.model.domain.entities.endereco.Cidade;
import com.vettodos.model.domain.entities.endereco.Endereco;
import com.vettodos.model.domain.entities.endereco.Estado;
import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.usecases.animal.AnimalDAO;

public class SQLAnimalDAO implements AnimalDAO{

    private Animal retornaAnimal(ResultSet rs) throws SQLException {
        Long idAnimal = rs.getLong("id");
        String nomeAnimal = rs.getString("nome");
        String especie = rs.getString("especie");
        String raca = rs.getString("raca");
        String porte = rs.getString("porte");
        String sexo = rs.getString("sexo");
        Double peso = rs.getDouble("peso");
        Integer status = rs.getInt("status");
        Integer anoNascimento = rs.getInt("ano_nascimento");
        Long idTutor = rs.getLong("id_tutor");
        Especie especieEnun = Especie.retornaEspecie(especie);
        Raca racaEnun = Raca.retornaRaca(raca);
        Porte porteEnun = Porte.retornaPorte(porte);
        String cpf = rs.getString("cpf");
        String nomeTutor = rs.getString("nome_tutor");
        String email = rs.getString("email");
        String telefone = rs.getString("telefone");
        Long idEndereco = rs.getLong("id_endereco");
        String logradouro = rs.getString("logradouro");
        String cep = rs.getString("cep");
        String numero = rs.getString("numero");
        String cidade = rs.getString("cidade");
        String estado = rs.getString("estado");
        String complemento = rs.getString("complemento");
        Cidade cidadeEnun = Cidade.getCidade(cidade);
        Estado estadoEnun = Estado.getEstado(estado);
        Endereco endereco = new Endereco(idEndereco, 
            logradouro, cep, 
            numero, 
            cidadeEnun, 
            estadoEnun, 
            complemento);
        Tutor tutor = new Tutor(idTutor, nomeTutor, cpf, email, telefone, endereco);
        if(status == 0) return new Animal(idAnimal, nomeAnimal, especieEnun, racaEnun, porteEnun, sexo, peso, anoNascimento, false, tutor);
        return new Animal(idAnimal, nomeAnimal, especieEnun, racaEnun, porteEnun, sexo, peso, anoNascimento, true, tutor);

    }
    @Override
    public List<Animal> buscarPorNome(String nomeAnimal) {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT a.*, t.cpf, t.nome as nome_tutor, t.email, t.telefone, t.id_endereco, "+
            "e.logradouro, e.numero, e.complemento, e.cep, e.cidade, e.estado " +
            "FROM animal a JOIN tutor t ON a.id_tutor = t.id " +
            "JOIN endereco e ON t.id_endereco = e.id " +
            "WHERE a.nome LIKE ? ;";
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, "%" + nomeAnimal.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Animal animal = retornaAnimal(rs);
                animais.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animais;
    }

    @Override
    public List<Animal> buscarPorTutor(String nomeTutor) {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT a.*, t.cpf, t.nome as nome_tutor, t.email, t.telefone, t.id_endereco, "+
            "e.logradouro, e.numero, e.complemento, e.cep, e.cidade, e.estado " +
            "FROM animal a JOIN tutor t ON a.id_tutor = t.id " +
            "JOIN endereco e ON t.id_endereco = e.id " +
            "WHERE t.nome LIKE ? ;";
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, nomeTutor.toLowerCase());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Animal animal = retornaAnimal(rs);
                animais.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animais;
    }
    @Override
    public void salvar(Animal animal) {
        String sql = "INSERT INTO animal(" +
            "nome, especie, raca, porte, sexo, peso, ano_nascimento, status, id_tutor) " + 
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, animal.getNome());
            ps.setString(2, animal.getEspecie().getNomeEspecie());
            ps.setString(3, animal.getRaca().getNomeRaca());
            ps.setString(4, animal.getPorte().getPorte());
            ps.setString(5, animal.getSexo());
            ps.setDouble(6, animal.getPeso());
            ps.setInt(7, animal.getanoNascimento());
            if(animal.isStatus()) ps.setInt(8, 1);
            else ps.setInt(8, 0);
            ps.setLong(9, animal.getTutor().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void editar(Animal animal) {
        String sql = "UPDATE animal SET nome = ?, especie = ?, raca = ?, porte = ?," +
            "sexo = ?, peso = ?, ano_nascimento = ?, status = ?, id_tutor = ? " + 
            "WHERE id = ?; ";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, animal.getNome());
            ps.setString(2, animal.getEspecie().getNomeEspecie());
            ps.setString(3, animal.getRaca().getNomeRaca());
            ps.setString(4, animal.getPorte().getPorte());
            ps.setString(5, animal.getSexo());
            ps.setDouble(6, animal.getPeso());
            ps.setInt(7, animal.getanoNascimento());
            if(animal.isStatus()) ps.setInt(8, 1);
            else ps.setInt(8, 0);
            ps.setLong(9, animal.getTutor().getId());
            ps.setLong(10, animal.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
}
