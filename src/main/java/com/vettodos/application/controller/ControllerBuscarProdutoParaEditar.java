package com.vettodos.application.controller;

import java.io.IOException;


import com.vettodos.Main;
import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.produto.Categoria;
import com.vettodos.model.domain.entities.produto.Produto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerBuscarProdutoParaEditar {
    @FXML
    private Button btnBuscaTipo1;
    @FXML
    private Button btnBuscaTipo2;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private Label labelBusca;
    @FXML
    private Label labelTituloTela;
    @FXML
    private TextField txtEntradaBusca;
    @FXML
    private TableColumn<Produto, String> colResultado;
    @FXML
    private TableView<Produto> resultadoBusca;
    private ObservableList<Produto> resultados;

    @FXML
    private void initialize() throws Exception {
        choiceBox1.setVisible(true);
        choiceBox1.getItems().addAll(Categoria.listarCategorias());
        choiceBox1.setValue(choiceBox1.getItems().get(0));
        btnBuscaTipo2.setVisible(false);
        txtEntradaBusca.setVisible(false);
        labelBusca.setText("Selecionar categoria");
        resultados = FXCollections.observableArrayList();
        labelTituloTela.setText("Buscar Produto");
        btnBuscaTipo1.setText("Buscar por categoria");
        resultadoBusca.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colResultado.setCellValueFactory(new PropertyValueFactory<>("nome"));
        resultadoBusca.setOnMouseClicked(event -> {
            try {
                retornarResultadoBusca();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        btnBuscaTipo1.setOnAction(event ->  {
            buscarPorCategoria(); 
        });
        
    }
    public void retornarResultadoBusca() throws IOException {
        Main.produtoSelecionado = resultadoBusca.getSelectionModel().getSelectedItem();
        InicializadorDeTelas.fecharModal();
        InicializadorDeTelas.setRoot("tela-gerenciamento-produto");
    }
    public void buscarPorCategoria() {
        resultados.clear();
        try {
            resultados.addAll(Main.buscarProduto.buscarPorCategoria(Categoria.getCategoria(choiceBox1.getValue())));
            resultadoBusca.setItems(resultados);
        } catch (Exception e) {
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
