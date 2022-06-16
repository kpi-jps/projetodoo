package com.vettodos.application.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.individuo.Usuario;
import com.vettodos.model.domain.entities.individuo.Veterinario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.vettodos.Main.*;

public class CadastroUsuarioController {

    @FXML
    private ImageView logo;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnVoltar;
    @FXML
    private CheckBox checkboxVeterinario;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelRegistroProfissional;
    @FXML
    private Label labelSenha;
    @FXML
    private Label labelTelefone;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRegistroProfissional;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtTelefone;
    @FXML 
    private void initialize() throws Exception {
        FileInputStream caminhoImagemLogo = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/logo.png").getPath());
        FileInputStream caminhoImagemIconeUsuario = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/usuario.png").getPath());
        Image imagemLogo = new Image(caminhoImagemLogo);
        Image imagemIconeUsuario = new Image(caminhoImagemIconeUsuario);
        logo.setImage(imagemLogo);
        iconeUsuario.setImage(imagemIconeUsuario);
        //labelRegistroProfissional.setDisable(true);
        setRegistroProfissionalInvisivel();
        checkboxVeterinario.setSelected(false);
    }

    private void setRegistroProfissionalVisivel() {
        labelRegistroProfissional.setVisible(true);
        txtRegistroProfissional.setVisible(true);
    }

    private void setRegistroProfissionalInvisivel() {
        labelRegistroProfissional.setVisible(false);
        txtRegistroProfissional.setVisible(false);
    }

    public void selecionarVeterinario() {
        if(checkboxVeterinario.isSelected()) setRegistroProfissionalVisivel();
        else setRegistroProfissionalInvisivel();
    }

    public void voltar() throws IOException {
        InicializadorDeTelas.setRoot("tela-principal-usuario");
    }

    private Usuario getDadosUsuario() {
        if(checkboxVeterinario.isSelected()) 
            return new Veterinario(txtEmail.getText(), txtNome.getText(), txtTelefone.getText(), true, txtRegistroProfissional.getText());
        else 
            return new Usuario(txtEmail.getText(), txtNome.getText(), txtTelefone.getText(), true);
    }

    private void limparFormulario() {
        txtEmail.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtRegistroProfissional.setText("");
        txtSenha.setText("");
    }

    public void salvar() throws IOException {
        Usuario usuario = getDadosUsuario();
        String msgSucesso = "Usuário salvo com sucesso!";
        if(usuario instanceof Veterinario) msgSucesso = "Veterinário salvo com sucesso!";
        try {
            cadastrarUsuario.cadastrar(getDadosUsuario(), txtSenha.getText());
            alerta("Aviso", msgSucesso , AlertType.INFORMATION);
            limparFormulario();
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
