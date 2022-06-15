package com.vettodos.application.repository.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vettodos.model.domain.entities.produto.Categoria;
import com.vettodos.model.domain.entities.produto.Produto;
import com.vettodos.model.domain.entities.produto.UnidadeMedida;
import com.vettodos.model.domain.usecases.produto.ProdutoDAO;



public class SQLProdutoDAO implements ProdutoDAO {

    private Produto retornaProduto(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String categoria = rs.getString("categoria");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");
        String unidadeMedida = rs.getString("unidade_medida");
        Double minimoEmEstoque = rs.getDouble("minimo_em_estoque");
        Categoria categoriaEnun;
        UnidadeMedida unidadeMedidaEnun;
        try {
            categoriaEnun = Categoria.valueOf(categoria);
        } catch (Exception e) {
            categoriaEnun = null;
        }
        try {
            unidadeMedidaEnun = UnidadeMedida.valueOf(unidadeMedida);
        } catch (Exception e) {
            unidadeMedidaEnun = null;
        }
        return  new Produto(id, categoriaEnun, nome, descricao, unidadeMedidaEnun, minimoEmEstoque);
    }

    @Override
    public List<Produto> buscarPorCategoria(Categoria categoria) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE categoria = ?;";
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, categoria.getNome());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = retornaProduto(rs);
                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto("+
        "categoria, nome, descricao, unidade_medida, minimo_em_estoque)" + 
        "VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, produto.getCategoria().getNome());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getDescricao());
            ps.setString(4, produto.getUnidadeMedida().getNome());
            ps.setDouble(5, produto.getMinimoEmEstoque());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    @Override
    public void editar(Produto produto) {
        String sql = "UPDATE produto SET categoria = ?, nome = ?"+
        "descricao = ?, unidade_medida = ?, minimo_em_estoque = ?" + 
        "WHERE id = ?;";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, produto.getCategoria().getNome());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getDescricao());
            ps.setString(4, produto.getUnidadeMedida().getNome());
            ps.setDouble(5, produto.getMinimoEmEstoque());
            ps.setLong(5, produto.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        
    }

    @Override
    public void registroEstoque(Produto produto, Double quantidade) {
        String sql = "INSERT INTO registro_estoque("+
        "data, quantidade, id_produto" + 
        "VALUES (?, ?, ?);";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, LocalDateTime.now().toString());
            ps.setDouble(2, quantidade);
            ps.setLong(3, produto.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        
    }

    @Override
    public Double totalEmEstoque(Produto produto) {
        String sql = "SELECT COUNT(quantidade) AS total FROM registro_estoque WHERE id_produto = ?;";
        Double total = 0.0;
        try (PreparedStatement ps =  FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setLong(1, produto.getId());
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