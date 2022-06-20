package com.vettodos.application.repository.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vettodos.model.domain.entities.registro_estoque.RegistroEstoque;
import com.vettodos.model.domain.usecases.registro_estoque.RegistroEstoqueDAO;

public class SQLRegistroEstoqueDAO implements RegistroEstoqueDAO {

    
    @Override
    public void registroEstoque(RegistroEstoque registroEstoque) {
        String sql = "INSERT INTO registro_estoque("+
        "data, quantidade, tipo_registro, id_produto)" + 
        "VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, registroEstoque.getData().toString());
            ps.setDouble(2, registroEstoque.getQuantidade());
            ps.setString(3, registroEstoque.getTipoRegistro().getTipo());
            ps.setLong(4, registroEstoque.getProduto().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }     
    }

    @Override
    public Double totalEmEstoque(Long idProduto) {
        String sql = "SELECT SUM(quantidade) AS total FROM registro_estoque WHERE id_produto = ?;";
        Double total = 0.0;
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setLong(1, idProduto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }


}