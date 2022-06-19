package com.vettodos.application.controller;


import java.io.FileInputStream;
import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.produto.Categoria;
import com.vettodos.model.domain.entities.produto.Produto;
import com.vettodos.model.domain.entities.produto.UnidadeMedida;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.vettodos.Main.*;

public class ControllerGerenciarProduto {

    @FXML
    private Button btnSalvarOuEditar;
    @FXML
    private Button btnVoltar;
    @FXML
    private ChoiceBox<String> choiceBoxCategoria;
    @FXML
    private ChoiceBox<String> choiceBoxUnidade;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Label labelCategoria;
    @FXML
    private Label labelDescricao;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelQUantidadeMinima;
    @FXML
    private Label labelTituloTela;
    @FXML
    private Label labelUnidade;
    @FXML
    private ImageView logo;
    @FXML
    private TextArea textAreaDescricao;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtQUantidadeMinima;

    @FXML 
    private void initialize() throws Exception {
        FileInputStream caminhoImagemLogo = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/logo.png").getPath());
        FileInputStream caminhoImagemIconeUsuario = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/produto.png").getPath());
        Image imagemLogo = new Image(caminhoImagemLogo);
        Image imagemIconeUsuario = new Image(caminhoImagemIconeUsuario);
        logo.setImage(imagemLogo);
        iconeUsuario.setImage(imagemIconeUsuario);
        setAmbiente();
        if(modoOperacao == ModoOperacao.CRIAR) setModoCriar();
        if(modoOperacao == ModoOperacao.EDITAR) setModoEditar();
    }

    private void setAmbiente() {
        choiceBoxCategoria.getItems().addAll(Categoria.listarCategorias());
        choiceBoxCategoria.setValue(choiceBoxCategoria.getItems().get(0));
        choiceBoxUnidade.getItems().addAll(UnidadeMedida.listarUnidades());
        choiceBoxUnidade.setValue(choiceBoxUnidade.getItems().get(0));
        txtQUantidadeMinima.setText("0");
    }
    
    private void setModoCriar() {
        labelTituloTela.setText("Cadastro Produto");
        btnSalvarOuEditar.setText("Salvar");        
    }

    private void setModoEditar() {
        labelTituloTela.setText("Edição Produto");
        btnSalvarOuEditar.setText("Editar");
        setDados(produtoSelecionado);
    }

    public void voltar() throws IOException {
        InicializadorDeTelas.setRoot("tela-principal");
    }

    private Notificador validaDados() {
        Notificador notificador = new Notificador();
        if(!Validadores.validaNumeroReal((txtQUantidadeMinima.getText())))
            notificador.adicionaMsg("Quantidade mínima inválida! ");
        return notificador;
    }

    private Produto getDados() {
        return new Produto(
            Categoria.getCategoria(choiceBoxCategoria.getValue()), 
            txtNome.getText(), 
            textAreaDescricao.getText(), 
            UnidadeMedida.getUnidade(choiceBoxUnidade.getValue()), 
            Double.parseDouble(txtQUantidadeMinima.getText())
            );
    }

    private void setDados(Produto produto) {
        choiceBoxCategoria.setValue(produto.getCategoria().getNome());
        choiceBoxUnidade.setValue(produto.getUnidadeMedida().getNome());
        txtQUantidadeMinima.setText(produto.getMinimoEmEstoque().toString());
        txtNome.setText(produto.getNome());
        textAreaDescricao.setText(produto.getDescricao());
    }

    private void limparDados() {
        choiceBoxCategoria.setValue(choiceBoxCategoria.getItems().get(0));
        choiceBoxUnidade.setValue(choiceBoxUnidade.getItems().get(0));
        txtQUantidadeMinima.setText("0");
        txtNome.setText("");
        textAreaDescricao.setText("");
    }

    public void salvarOuEditar() throws IOException {
        if(modoOperacao == ModoOperacao.CRIAR) salvar();
        if(modoOperacao == ModoOperacao.EDITAR) editar();
    }

    private void salvar() throws IOException {
        Produto produto = null;
        try {
            Notificador notificador = validaDados();
            if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
            produto = getDados();
            cadastrarProduto.cadastrar(produto);
            alerta("Aviso", "Produto cadastrado com sucesso!" , AlertType.INFORMATION);
            limparDados();
        } catch (Exception e) {
            if(e instanceof IOException) throw e;
            alerta("Erro", e.getMessage(), AlertType.ERROR);
        } 
    }

    private void editar() throws IOException {
        Produto produtoNovo = null;        
        try {
            Notificador notificador = validaDados();
            if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
            produtoNovo = getDados();
            produtoNovo.setId(produtoSelecionado.getId());
            editarProduto.editar(produtoNovo);
            alerta("Aviso", "Produto editado com sucesso!" , AlertType.INFORMATION);
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
