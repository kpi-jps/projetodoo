package com.vettodos.application.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.vettodos.Main.*;


public class UsuarioController {
    
    @FXML
    private ImageView logo;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Button btnCadastrarAnimal;
    @FXML
    private Button btnCadastrarProduto;
    @FXML
    private Button btnCadastrarTutor;
    @FXML
    private Button btnCadastrarUsuario;
    @FXML
    private Button btnEditarAnimal;
    @FXML
    private Button btnEditarProduto;
    @FXML
    private Button btnEditarTutor;
    @FXML
    private Button btnEditarUsuario;
    @FXML
    private Button btnEstoque;
    @FXML
    private Button btnSair;
    @FXML
    private Label infoUsuario;

    @FXML 
    private void initialize() throws Exception {
        FileInputStream caminhoImagemLogo = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/logo.png").getPath());
        Image imagemLogo = new Image(caminhoImagemLogo);
        logo.setImage(imagemLogo);
        infoUsuario.setText("id: " + usuarioAutenticado.getId() + " - Usuário: "+ usuarioAutenticado.getNome());
    }

    public void cadastrarUsuario() throws IOException {
        modoOperacao = ModoOperacao.CRIAR;
        InicializadorDeTelas.setRoot("tela-gerenciamento-usuario");
    }

    public void editarUsuario() throws IOException {
        modoOperacao = ModoOperacao.EDITAR;
        InicializadorDeTelas.mostrarTelaModal("tela-busca-tipo1", new BuscarUsuarioController());
        
    }   

    public void cadastrarTutor() throws IOException {
        
    }

    public void editarTutor() throws IOException {
        //InicializadorDeTelas.mostrarTelaModal("tela-busca-tipo1");
        //modoOperacao = ModoOperacao.EDITAR;
        //InicializadorDeTelas.setRoot("tela-gerenciamento-tutor");
    }

    public void cadastrarAnimal() throws IOException {
        //InicializadorDeTelas.setRoot("tela-cadastra-animal");
    }

    public void editarAnimal() throws IOException {
        //InicializadorDeTelas.setRoot("tela-edita-animal");
    }

    public void cadastrarProduto() throws IOException {
        //InicializadorDeTelas.setRoot("tela-cadastra-produto");
    }

    public void editarProduto() throws IOException {
        //InicializadorDeTelas.setRoot("tela-edita-produto");
    }

    public void gerenciarEstoque() throws IOException {
        //InicializadorDeTelas.setRoot("tela-gerecia-estoque");
    }


}
