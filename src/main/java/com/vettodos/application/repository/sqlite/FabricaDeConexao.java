package com.vettodos.application.repository.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FabricaDeConexao {
    
    private static Connection conexao;
    
    public static synchronized Connection criarConexao() throws SQLException {
        if(conexao == null) conexao = DriverManager.getConnection("jdbc:sqlite:banco_de_dados.db");
        return conexao;
    }

    public static PreparedStatement criaPreparedStatement(String sql) throws SQLException{
        return criarConexao().prepareStatement(sql);
    }

    public static Statement criaStatement() throws SQLException{
        return criarConexao().createStatement();
    }
}
