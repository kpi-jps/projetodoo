package com.vettodos.application.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.individuo.Veterinario;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.vettodos.Main.*;


public class ControllerPrincipal {
    
    @FXML
    private ImageView logo;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Label titulo;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Label infoUsuario;

    @FXML 
    private void initialize() throws Exception {
        FileInputStream caminhoImagemLogo = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/logo.png").getPath());
        Image imagemLogo = new Image(caminhoImagemLogo);
        logo.setImage(imagemLogo);
        if(usuarioAutenticado instanceof Veterinario) setAmbienteParaVeterinario();
        else setAmbienteParaUsuario();
    }

    private void setAmbienteParaUsuario() {
        titulo.setText("Seja bem vindo Usuário");
        infoUsuario.setText("id: " + usuarioAutenticado.getId() + " - Usuário: "+ usuarioAutenticado.getNome());
        btn1.setText("Cadastrar Usuário");
        btn1.setOnAction(event -> {
            try {
                cadastrarUsuario();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn2.setText("Editar Usuário");
        btn2.setOnAction(event -> {
            try {
                editarUsuario();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn3.setText("Cadastrar Tutor");
        btn3.setOnAction(event -> {
            try {
                cadastrarTutor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn4.setText("Editar Tutor");
        btn4.setOnAction(event -> {
            try {
                editarTutor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn5.setText("Cadastrar Animal");
        btn5.setOnAction(event -> {
            try {
                cadastrarAnimal();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn6.setText("Editar Animal");
        btn6.setOnAction(event -> {
            try {
                editarAnimal();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn7.setText("Cadastrar Produto");
        btn7.setOnAction(event -> {
            try {
                cadastrarProduto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn8.setText("Editar Produto");
        btn8.setOnAction(event -> {
            try {
                editarProduto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn9.setText("Gerenciar Estoque");
        btn9.setOnAction(event -> {
            try {
                gerenciarEstoque();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setAmbienteParaVeterinario() {
        titulo.setText("Seja bem vindo Veterinário");
        infoUsuario.setText("id: " + usuarioAutenticado.getId() + " - Veterinário: "+ usuarioAutenticado.getNome());
        btn1.setText("Cadastrar Tutor");
        btn1.setOnAction(event -> {
            try {
                cadastrarTutor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn2.setText("Editar Tutor");
        btn2.setOnAction(event -> {
            try {
                editarTutor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn3.setText("Cadastrar Animal");
        btn3.setOnAction(event -> {
            try {
                cadastrarAnimal();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn4.setText("Editar Animal");
        btn4.setOnAction(event -> {
            try {
                editarAnimal();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn5.setText("Cadastrar Produto");
        btn5.setOnAction(event -> {
            try {
                cadastrarProduto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn6.setText("Editar Produto");
        btn6.setOnAction(event -> {
            try {
                editarProduto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn7.setText("Gerenciar Estoque");
        btn7.setOnAction(event -> {
            try {
                gerenciarEstoque();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn8.setText("Registrar Atendimento");
        btn8.setOnAction(event -> {
            try {
                registrarAtendimento();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn9.setVisible(false);
    }

    public void cadastrarUsuario() throws IOException {
        modoOperacao = ModoOperacao.CRIAR;
        InicializadorDeTelas.setRoot("tela-gerenciamento-usuario");
    }

    public void editarUsuario() throws IOException {
        modoOperacao = ModoOperacao.EDITAR;
        InicializadorDeTelas.mostrarTelaModal("tela-busca", new ControllerBuscarUsuario());
        
    }   

    public void cadastrarTutor() throws IOException {
        modoOperacao = ModoOperacao.CRIAR;
        InicializadorDeTelas.setRoot("tela-gerenciamento-tutor");
    }

    public void editarTutor() throws IOException {
        modoOperacao = ModoOperacao.EDITAR;
        InicializadorDeTelas.mostrarTelaModal("tela-busca", new ControllerBuscarTutorParaEditar());
    }

    public void cadastrarAnimal() throws IOException {
        modoOperacao = ModoOperacao.CRIAR;
        InicializadorDeTelas.setRoot("tela-gerenciamento-animal");
    }

    public void editarAnimal() throws IOException {
        modoOperacao = ModoOperacao.EDITAR;
        InicializadorDeTelas.mostrarTelaModal("tela-busca", new ControllerBuscarAnimal());
    }

    public void cadastrarProduto() throws IOException {
        modoOperacao = ModoOperacao.CRIAR;
        InicializadorDeTelas.setRoot("tela-gerenciamento-produto");
    }

    public void editarProduto() throws IOException {
        modoOperacao = ModoOperacao.EDITAR;
        InicializadorDeTelas.mostrarTelaModal("tela-busca", new ControllerBuscarProdutoParaEditar());
    }

    public void gerenciarEstoque() throws IOException {
        modoOperacao = ModoOperacao.EDITAR;
        InicializadorDeTelas.mostrarTelaModal("tela-busca", new ControllerBuscarProduto());
    }

    public void registrarAtendimento() throws IOException {
        modoOperacao = ModoOperacao.CRIAR;
        InicializadorDeTelas.mostrarTelaModal("tela-busca", new ControllerBuscarAnimalParaAtendimento());
    }


}
