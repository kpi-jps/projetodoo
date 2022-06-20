package com.vettodos.application.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.atendimento.Atendimento;
import com.vettodos.model.domain.entities.individuo.Veterinario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.vettodos.Main.*;

public class ControllerRegistrarAtendimento {

    @FXML
    private Button btnSalvarOuEditar;
    @FXML
    private Button btnVoltar;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Label labelAnimal;
    @FXML
    private Label labelDiagnostico;
    @FXML
    private Label labelQueixa;
    @FXML
    private Label labelReceituario;
    @FXML
    private Label labelTituloTela;
    @FXML
    private Label labelVeterinario;
    @FXML
    private ImageView logo;
    @FXML
    private TextArea textAreaQueixa;
    @FXML
    private TextArea textAreaDiagnostico;
    @FXML
    private TextArea textAreaReceituario;
    
    @FXML 
    private void initialize() throws Exception {
        FileInputStream caminhoImagemLogo = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/logo.png").getPath());
        FileInputStream caminhoImagemIconeUsuario = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/usuario.png").getPath());
        Image imagemLogo = new Image(caminhoImagemLogo);
        Image imagemIconeUsuario = new Image(caminhoImagemIconeUsuario);
        logo.setImage(imagemLogo);
        iconeUsuario.setImage(imagemIconeUsuario);
        labelTituloTela.setText("Registrar Atendimento");
        labelVeterinario.setText("Veterinário: " + usuarioAutenticado.getNome());
        labelAnimal.setText("Animal: " + animalSelecionado.getNome());
    }

    private Atendimento criaAtendimento() {
        return new Atendimento(
            animalSelecionado, 
            (Veterinario) usuarioAutenticado, 
            textAreaQueixa.getText(), 
            textAreaDiagnostico.getText(), 
            textAreaReceituario.getText()
            );
    }

    public void voltar() throws IOException {
        InicializadorDeTelas.setRoot("tela-principal");
    }

    public void salvar() throws IOException {
       Atendimento atendimento = null;
        try {
            atendimento = criaAtendimento();
            realizarAtendimento.cadastrar(atendimento);
            alerta("Aviso", "Atendimento registrado com sucesso" , AlertType.INFORMATION);
            voltar();
        } catch (Exception e) {
            if(e instanceof IOException) throw e;
            alerta("Erro", e.getMessage(), AlertType.ERROR);
        } 
    }

    private void alerta(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
 }
