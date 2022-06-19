package com.vettodos.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.vettodos.Main.*;

import java.io.FileInputStream;
import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;


public class ControllerAutenticarUsuario {

    @FXML
    private ImageView logo;
    @FXML
    ImageView cadeado;
    @FXML
    private Button btnAutenticar;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;

    @FXML 
    private void initialize() throws Exception {
        FileInputStream caminhoImagemLogo = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/logo.png").getPath());
        FileInputStream caminhoImagemCadeado = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/lock.png").getPath());
        Image imagemLogo = new Image(caminhoImagemLogo);
        Image imagemCadeado = new Image(caminhoImagemCadeado);
        logo.setImage(imagemLogo);
        cadeado.setImage(imagemCadeado);
    }

    public void autenticar(ActionEvent evento) throws Exception {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        try {
            usuarioAutenticado = autenticarUsuario.autenticar(email, senha);            
            InicializadorDeTelas.setRoot("tela-principal");
            
        } catch (Exception e) {
            //e.printStackTrace();
            if(e instanceof IOException) throw e;
            alerta("Erro de autenticação", e.getMessage(), AlertType.ERROR);
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
