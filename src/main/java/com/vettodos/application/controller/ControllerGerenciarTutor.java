package com.vettodos.application.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.endereco.Cidade;
import com.vettodos.model.domain.entities.endereco.Endereco;
import com.vettodos.model.domain.entities.endereco.Estado;
import com.vettodos.model.domain.entities.individuo.Tutor;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.vettodos.Main.*;

public class ControllerGerenciarTutor {
    @FXML
    private Button btnSalvarOuEditar;
    @FXML
    private Button btnVoltar;
    @FXML
    private ChoiceBox<String> choiceBoxCidade;
    @FXML
    private ChoiceBox<String> choiceBoxEstado;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Label labelCEP;
    @FXML
    private Label labelCPF;
    @FXML
    private Label labelCidade;
    @FXML
    private Label labelComplemento;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelEstado;
    @FXML
    private Label labelLogradouro;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelNumero;
    @FXML
    private Label labelTelefone;
    @FXML
    private Label labelTituloTela;
    @FXML
    private ImageView logo;
    @FXML
    private TextField txtCEP;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtComplemento;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtLogradouro;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtNumero;
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
        setAmbiente();
        if(modoOperacao == ModoOperacao.EDITAR) setModoEditar();
        else setModoSalvar();
    }

    private void setModoEditar() {
        setDados(tutorSelecionado);
        labelTituloTela.setText("Edição Tutor");
        btnSalvarOuEditar.setText("Editar");
    }

    private void setModoSalvar() {
        labelTituloTela.setText("Cadastrar Tutor");
        btnSalvarOuEditar.setText("Salvar");
    }

    private void preencherListaCidade() {
        choiceBoxCidade.getItems().setAll(Cidade.listarNomeCidades(choiceBoxEstado.getValue()));
    }

    private void preencherListaEstados() {
        choiceBoxEstado.getItems().setAll(Estado.listarSiglaEstados());
        choiceBoxEstado.setValue(choiceBoxEstado.getItems().get(0));
    }

    private void setAmbiente() {
        choiceBoxEstado.setOnAction(event -> {
            choiceBoxCidade.getItems().clear();
            preencherListaCidade();
            choiceBoxCidade.setValue(choiceBoxCidade.getItems().get(0));
        });
        preencherListaEstados();
        preencherListaCidade();
        choiceBoxCidade.setValue(choiceBoxCidade.getItems().get(0));
    }

    public void voltar() throws IOException {
        InicializadorDeTelas.setRoot("tela-principal");
    }

    private Tutor getDados() {
        return new Tutor(
            txtNome.getText(), 
            txtCPF.getText(), 
            txtEmail.getText(), 
            txtTelefone.getText(), 
            new Endereco(
                txtLogradouro.getText(), 
                txtCEP.getText(), 
                txtNumero.getText(), 
                Cidade.getCidade(choiceBoxCidade.getValue()),
                Estado.getEstado(choiceBoxEstado.getValue()), 
                txtComplemento.getText()
                )
            );
    }

    private void setDados(Tutor tutor) {
        txtCPF.setText(tutor.getCpf());
        txtNome.setText(tutor.getNome());
        txtEmail.setText(tutor.getEmail());
        txtTelefone.setText(tutor.getTelefone());
        txtLogradouro.setText(tutor.getEndereco().getLogradouro());
        txtNumero.setText(tutor.getEndereco().getNumero());
        txtCEP.setText(tutor.getEndereco().getCep());
        txtComplemento.setText(tutor.getEndereco().getComplemento());
        choiceBoxEstado.setValue(tutor.getEndereco().getCidade().getEstado().getSigla());
        choiceBoxCidade.setValue(tutor.getEndereco().getCidade().getNomeCidade());
    }

    private void limparDados() {
        txtCPF.setText("");
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtLogradouro.setText("");
        txtNumero.setText("");
        txtCEP.setText("");
        txtComplemento.setText("");
        choiceBoxEstado.setValue(choiceBoxEstado.getItems().get(0));
        choiceBoxCidade.setValue(choiceBoxCidade.getItems().get(0));  
    }

    public void salvarOuEditar() throws IOException {
        if(modoOperacao == ModoOperacao.CRIAR) salvar();
        if(modoOperacao == ModoOperacao.EDITAR) editar();
    }

    private void salvar() throws IOException {
        Tutor tutor = getDados();
        try {
            cadastrarTutor.cadastrar(tutor);
            alerta("Aviso", "Tutor salvo com sucesso!" , AlertType.INFORMATION);
            limparDados();
        } catch (Exception e) {
            alerta("Erro", e.getMessage(), AlertType.ERROR);
        }
    }

    private void editar() throws IOException {
        Tutor tutorNovo = getDados();
        tutorNovo.setId(tutorSelecionado.getId());
        tutorNovo.getEndereco().setId(tutorSelecionado.getEndereco().getId());
        try {
            editarTutor.editar(tutorSelecionado, tutorNovo);
            alerta("Aviso", "Tutor editado com sucesso!" , AlertType.INFORMATION);
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
