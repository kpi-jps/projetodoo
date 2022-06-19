package com.vettodos.application.controller;

import java.io.IOException;

import com.vettodos.Main;
import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.individuo.Usuario;

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

public class ControllerBuscarUsuario {
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
    private TableColumn<Usuario, String> colResultado;
    @FXML
    private TableView<Usuario> resultadoBusca;

    private ObservableList<Usuario> resultados;

    @FXML
    private void initialize() throws Exception {
        choiceBox1.setVisible(false);
        resultados = FXCollections.observableArrayList();
        labelTituloTela.setText("Buscar Usuário");
        btnBuscaTipo1.setText("Buscar por email");
        btnBuscaTipo2.setText("Buscar por nome");
        resultadoBusca.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colResultado.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        resultadoBusca.setOnMouseClicked(event -> {
            try {
                retornarResultadoBusca();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        btnBuscaTipo1.setOnAction(event ->  {
            buscarPorEmail();  
        });

        btnBuscaTipo2.setOnAction(event ->  {
            buscarPorNome();  
        });
        
    }
    public void retornarResultadoBusca() throws IOException {
        Main.usuarioSelecionado = resultadoBusca.getSelectionModel().getSelectedItem();
        InicializadorDeTelas.fecharModal();
        InicializadorDeTelas.setRoot("tela-gerenciamento-usuario");
    }
    public void buscarPorEmail() {
        resultados.clear();
        try {
            resultados.add(Main.buscarUsuario.buscarPorEmail(txtEntradaBusca.getText()));
            resultadoBusca.setItems(resultados);
        } catch (Exception e) {
            alerta("Erro", e.getMessage(), AlertType.ERROR);
        }
    }

    public void buscarPorNome() {
        resultados.clear();
        try {
            resultados.addAll(Main.buscarUsuario.buscarPorNome(txtEntradaBusca.getText()));
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
