package com.vettodos.application.controller;

import com.vettodos.model.domain.entities.registro_estoque.RegistroEstoque;
import com.vettodos.model.domain.entities.registro_estoque.TipoRegistro;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import static com.vettodos.Main.*;

import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;

public class ControllerRegistroEstoque {

    @FXML
    private Button btnBuscaTipo1;
    @FXML
    private ChoiceBox<String> choiceBoxTipoAlteracao;
    @FXML
    private Label labelNomeProduto;
    @FXML
    private Label labelQuantidade;
    @FXML
    private Label labelQuantidadeEmEstoque;
    @FXML
    private Label labelQuantidadeMinima;
    @FXML
    private Label labelTipoAlteracao;
    @FXML
    private Label labelTituloTela;
    @FXML
    private TextField txtQuantidade;

    @FXML
    private void initialize() throws Exception {
        choiceBoxTipoAlteracao.getItems().addAll(TipoRegistro.listarTipos());
        choiceBoxTipoAlteracao.setValue(choiceBoxTipoAlteracao.getItems().get(0));
        labelNomeProduto.setText(
            "Nome produto:  " + produtoSelecionado.getNome()
        );
        labelQuantidadeMinima.setText(
            "Quantidade mínima em estoque: " + produtoSelecionado.getMinimoEmEstoque().toString() +
                " " + produtoSelecionado.getUnidadeMedida().getNome()
        );
        labelQuantidadeEmEstoque.setText(
            "Quantidade total em estoque: " + realizarRegistroEstoque.getTotalEmEstoque(produtoSelecionado.getId()).toString() +
            " " + produtoSelecionado.getUnidadeMedida().getNome()
        );

        btnBuscaTipo1.setOnAction(event -> {
            registrar();
        });

    }

    private Notificador validaDados() {
        Notificador notificador = new Notificador();
        if(produtoSelecionado == null) 
            notificador.adicionaMsg("Produto não selecionado! ");
        if(!Validadores.validaNumeroRealMaioQueZero((txtQuantidade.getText())))
            notificador.adicionaMsg("Quantidade inválida! ");
        return notificador;
    }

    public RegistroEstoque criarRegistro() {
        return new RegistroEstoque(
            produtoSelecionado, 
            Double.parseDouble(txtQuantidade.getText()), 
            TipoRegistro.getCategoria(choiceBoxTipoAlteracao.getValue())
        );
    }

    public void registrar() {
        RegistroEstoque registroEstoque = null;
        try {
            Notificador notificador = validaDados();
            if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
            registroEstoque = criarRegistro();
            realizarRegistroEstoque.registrar(registroEstoque);
            alerta("Aviso", "Registro criado com sucesso!" , AlertType.INFORMATION);
            InicializadorDeTelas.fecharModal();
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
