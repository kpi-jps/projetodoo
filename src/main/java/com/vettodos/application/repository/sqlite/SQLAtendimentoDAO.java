package com.vettodos.application.repository.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vettodos.model.domain.entities.atendimento.Atendimento;
import com.vettodos.model.domain.entities.produto.Categoria;
import com.vettodos.model.domain.entities.produto.Produto;
import com.vettodos.model.domain.entities.produto.UnidadeMedida;
import com.vettodos.model.domain.usecases.atendimento.AtendimentoDAO;
import com.vettodos.model.domain.usecases.produto.ProdutoDAO;



public class SQLAtendimentoDAO implements AtendimentoDAO {

    private Produto retornaProduto(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String categoria = rs.getString("categoria");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");
        String unidadeMedida = rs.getString("unidade_medida");
        Double minimoEmEstoque = rs.getDouble("minimo_em_estoque");
        Categoria categoriaEnun = Categoria.getCategoria(categoria);
        UnidadeMedida unidadeMedidaEnun = UnidadeMedida.getUnidade(unidadeMedida);
        
        return  new Produto(id, categoriaEnun, nome, descricao, unidadeMedidaEnun, minimoEmEstoque);
    }

    @Override
    public void salvar(Atendimento atendimento) {
        String sql = "INSERT INTO atendimento("+
        "data, queixa, diagnostico, receituario, id_veterinario, id_animal)" + 
        "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = FabricaDeConexao.criaPreparedStatement(sql)) {
            ps.setString(1, atendimento.getData().toString());
            ps.setString(2, atendimento.getQueixa());
            ps.setString(3, atendimento.getDiagnostico());
            ps.setString(4, atendimento.getReceituario());
            ps.setLong(5, atendimento.getVeterinario().getId());
            ps.setLong(5, atendimento.getVeterinario().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    

    @Override
    public List<Atendimento> buscarPorData(LocalDate dataInicial, LocalDate dataFinal) {
        // TODO Auto-generated method stub
        return null;
    }


}