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

public class ControllerGerenciarUsuario {

    @FXML
    private ImageView logo;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Label labelTituloTela;
    @FXML
    private Button btnSalvarOuEditar;
    @FXML
    private Button btnVoltar;
    @FXML
    private CheckBox checkboxVeterinario;
    @FXML
    private CheckBox checkboxStatus;
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
        if(modoOperacao == ModoOperacao.CRIAR) setModoCriar();
        if(modoOperacao == ModoOperacao.EDITAR) setModoEditar();
        
    }

    private void setRegistroProfissionalVisivel() {
        labelRegistroProfissional.setVisible(true);
        txtRegistroProfissional.setVisible(true);
    }

    private void setRegistroProfissionalInvisivel() {
        labelRegistroProfissional.setVisible(false);
        txtRegistroProfissional.setVisible(false);
    }

    private void setModoCriar() {
        labelTituloTela.setText("Cadastro Usuário");
        labelSenha.setVisible(true);
        txtSenha.setVisible(true);
        btnSalvarOuEditar.setText("Salvar");
        setRegistroProfissionalInvisivel();
        checkboxVeterinario.setSelected(false);
        checkboxVeterinario.setVisible(true);
        checkboxStatus.setSelected(false);
        checkboxStatus.setVisible(false);
    }

    private void setModoEditar() {
        labelTituloTela.setText("Edição Usuário");
        labelSenha.setVisible(false);
        txtSenha.setVisible(false);
        btnSalvarOuEditar.setText("Editar");
        checkboxVeterinario.setVisible(false);
        if(usuarioSelecionado instanceof Veterinario) setRegistroProfissionalVisivel();
        else setRegistroProfissionalInvisivel();
        setDados(usuarioSelecionado);

    }

    public void selecionarVeterinario() {
        if(checkboxVeterinario.isSelected()) setRegistroProfissionalVisivel();
        else setRegistroProfissionalInvisivel();
    }

    public void voltar() throws IOException {
        InicializadorDeTelas.setRoot("tela-principal");
    }

    private Usuario getDados() {
        if(checkboxVeterinario.isSelected()) 
            return new Veterinario(txtEmail.getText(), txtNome.getText(), txtTelefone.getText(), !checkboxStatus.isSelected(), txtRegistroProfissional.getText());
        else 
            return new Usuario(txtEmail.getText(), txtNome.getText(), txtTelefone.getText(), !checkboxStatus.isSelected());
    }

    private void setDados(Usuario usuario) {
        txtEmail.setText(usuario.getEmail());
        txtNome.setText(usuario.getNome());
        txtTelefone.setText(usuario.getTelefone());
        checkboxStatus.setSelected(!usuario.isStatus());
        if(usuario instanceof Veterinario) {
            Veterinario veterinario = (Veterinario) usuario;
            checkboxVeterinario.setSelected(true);
            txtRegistroProfissional.setText(veterinario.getRegistroProfissional());
        }
    }

    private void limparDados() {
        txtEmail.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtRegistroProfissional.setText("");
        txtSenha.setText("");
    }

    public void salvarOuEditar() throws IOException {
        if(modoOperacao == ModoOperacao.CRIAR) salvar();
        if(modoOperacao == ModoOperacao.EDITAR) editar();
    }

    private void salvar() throws IOException {
        Usuario usuario = getDados();
        String msgSucesso = "Usuário salvo com sucesso!";
        if(usuario instanceof Veterinario) msgSucesso = "Veterinário salvo com sucesso!";
        try {
            cadastrarUsuario.cadastrar(getDados(), txtSenha.getText());
            alerta("Aviso", msgSucesso , AlertType.INFORMATION);
            limparDados();
        } catch (Exception e) {
            if(e instanceof IOException) throw e;
            alerta("Erro", e.getMessage(), AlertType.ERROR);
        } 
    }

    private void editar() throws IOException {
        Usuario usuarioNovo = getDados();
        usuarioNovo.setId(usuarioSelecionado.getId());
        String msgSucesso = "Usuário editado com sucesso!";
        if(usuarioNovo instanceof Veterinario) msgSucesso = "Veterinário editado com sucesso!";
        try {
            editarUsuario.editar(usuarioSelecionado, usuarioNovo);
            alerta("Aviso", msgSucesso , AlertType.INFORMATION);
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
