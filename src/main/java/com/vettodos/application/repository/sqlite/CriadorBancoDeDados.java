package com.vettodos.application.repository.sqlite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

import com.vettodos.model.domain.usecases.util.Encripitador;

public class CriadorBancoDeDados {

    private static final String caminhoArquivoBancoDeDados = "banco_de_dados.db";
    
    public static void criarSeNaoExistir() {
        try {
            if(!bancoDeDadosExiste()) {
                System.out.println("Banco de dados inexistente!\n");
                construir();
                criarUsuarioInicial();
                System.out.println("\nBanco de dados criado!");
            } else {
                System.out.println("\nBanco de dados existente!");
            }
            
        } catch (Exception e) {
            excluir();
            e.printStackTrace();
            System.out.println("\nOcorreu um erro durante a criação do banco de dados e ele foi excluído!");
        }
        
    }

    private static boolean bancoDeDadosExiste() {
        if(!Files.exists(Paths.get(caminhoArquivoBancoDeDados))) return false;
        return true;
    }

    private static void criarUsuarioInicial() throws SQLException {
        try (Statement stmt = FabricaDeConexao.criaStatement()){
            String hashSenha = Encripitador.encripitar("@vettodos");
            String insertUsuario = "INSERT INTO usuario  (\n" +
                                    "email, hash_senha, nome, telefone, credencial, status, registro_profissional)" + "\n" + 
                                    "VALUES ('vettodos@email.com', '" + hashSenha + "', 'Clínica Vettodos', '9999999999', 'A', 1, null" + 
                                    ") \n";
            stmt.addBatch(insertUsuario);
            System.out.println("\n Criando usuário inicial! \n");
            System.out.println(insertUsuario);
            System.out.println("login: vettodos@email.com");
            System.out.println("senha: @vettodos");
            stmt.executeBatch();
        }  catch (SQLException e) {
            throw e;
        }
    }

    
    private static void construir() throws SQLException {
        try (Statement stmt = FabricaDeConexao.criaStatement()){
            System.out.println("Criando banco de dados!");
            String tabelaUsuario = "CREATE TABLE usuario (\n" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                                    "email TEXT NOT NULL UNIQUE, \n" +
                                    "hash_senha TXT NOT NULL, \n"+
                                    "nome TEXT NOT NULL, \n" + 
                                    "telefone TEXT,\n" +
                                    "credencial TEXT NOT NULL, \n" +
                                    "status INTEGER NOT NULL, \n" +
                                    "registro_profissional TEXT \n" +
                                    "); \n";
            String tabelaEndereco = "CREATE TABLE endereco (\n" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                                    "logradouro TXT NOT NULL, \n"+
                                    "numero TEXT NOT NULL, \n" + 
                                    "cep TEXT NOT NULL,\n" +
                                    "cidade TEXT, \n" +
                                    "estado TEXT, \n" +
                                    "complemento TEXT \n" +
                                    "); \n";
            String tabelaTutor = "CREATE TABLE tutor (\n" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                                    "cpf TEXT NOT NULL UNIQUE, \n" +
                                    "nome TXT NOT NULL, \n" +
                                    "email TEXT, \n" + 
                                    "telefone TEXT, \n" +
                                    "id_endereco INTEGER, \n" +
                                    "FOREIGN KEY(id_endereco) REFERENCES endereco(id) ON DELETE CASCADE \n"+
                                    "); \n";
            String tabelaAnimal = "CREATE TABLE animal (\n" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                                    "nome TEXT NOT NULL, \n" +
                                    "especie TEXT NOT NULL, \n" +
                                    "raca TEXT NOT NULL, \n"+
                                    "porte TEXT NOT NULL, \n"+
                                    "sexo TEXT NOT NULL, \n" + 
                                    "peso REAL,\n" +
                                    "ano_nascimento INTEGER, \n" +
                                    "status INTEGER NOT NULL, \n" +
                                    "id_tutor INTEGER,\n" +
                                    "FOREIGN KEY(id_tutor) REFERENCES tutor(id) ON DELETE SET NULL\n"+
                                    "); \n";
            String tabelaProduto = "CREATE TABLE produto (\n" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                                    "categoria TEXT NOT NULL, \n" +
                                    "nome TEXT NOT NULL, \n"+
                                    "descricao TEXT NOT NULL, \n"+
                                    "unidade_medida TEXT NOT NULL, \n" + 
                                    "minimo_em_estoque REAL NOT NULL\n" +
                                    "); \n";
            String tabelaRegistroEstoque = "CREATE TABLE registro_estoque (\n" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                                    "data TEXT NOT NULL, \n" +
                                    "tipo_registro TEXT NOT NULL, \n" +
                                    "quantidade REAL NOT NULL,\n" +
                                    "id_produto INTEGER, \n" +
                                    "FOREIGN KEY(id_produto) REFERENCES produto(id) ON DELETE SET NULL \n"+
                                    "); \n";
            String tabelaAtendimento = "CREATE TABLE atendimento (\n" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                                    "data TEXT NOT NULL, \n" +
                                    "queixa TEXT NOT NULL, \n"+
                                    "diagnostico TEXT NOT NULL, \n"+
                                    "receituario TEXT, \n" + 
                                    "id_veterinario INTEGER, \n" +
                                    "id_animal INTEGER, \n" +
                                    "FOREIGN KEY(id_veterinario) REFERENCES usuario(id) ON DELETE SET NULL, \n"+
                                    "FOREIGN KEY(id_animal) REFERENCES animal(id) ON DELETE SET NULL \n"+
                                    "); \n";
            
            System.out.println("\nCriando tabela \"usuario\"! \n");
            System.out.println(tabelaUsuario);
            stmt.addBatch(tabelaUsuario);

            System.out.println("\nCriando tabela \"endereco\"! \n");
            System.out.println(tabelaEndereco);
            stmt.addBatch(tabelaEndereco);

            System.out.println("\nCriando tabela \"tutor\"! \n");
            System.out.println(tabelaTutor);
            stmt.addBatch(tabelaTutor);

            System.out.println("\nCriando tabela \"animal\"! \n");
            System.out.println(tabelaAnimal);
            stmt.addBatch(tabelaAnimal);

            System.out.println("\nCriando tabela \"produto\"! \n");
            System.out.println(tabelaProduto);
            stmt.addBatch(tabelaProduto);

            System.out.println("\nCriando tabela \"registro_estoque\"! \n");
            System.out.println(tabelaRegistroEstoque);
            stmt.addBatch(tabelaRegistroEstoque);

            System.out.println("\nCriando tabela \"atendimento\"! \n");
            System.out.println(tabelaAtendimento);
            stmt.addBatch(tabelaAtendimento);

            stmt.executeBatch();

        } catch (SQLException e) {
            throw e;
        }
    }

    private static void excluir() {
        try {
            Files.deleteIfExists(Paths.get(caminhoArquivoBancoDeDados));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
