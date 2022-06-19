package com.vettodos.application.controller;

import java.io.IOException;

import com.vettodos.Main;
import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.individuo.Tutor;

import static com.vettodos.Main.*;

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

public class ControllerBuscarTutorParaEditar {
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
    private TableColumn<Tutor, String> colResultado;
    @FXML
    private TableView<Tutor> resultadoBusca;
    private ObservableList<Tutor> resultados;

    @FXML
    private void initialize() throws Exception {
        choiceBox1.setVisible(false);
        resultados = FXCollections.observableArrayList();
        labelTituloTela.setText("Buscar Tutor");
        btnBuscaTipo1.setText("Buscar por CPF");
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
            buscarPorCPF();  
        });

        btnBuscaTipo2.setOnAction(event ->  {
            buscarPorNome();  
        });
        
    }
    
    public void retornarResultadoBusca() throws IOException {
        tutorSelecionado = resultadoBusca.getSelectionModel().getSelectedItem();
        InicializadorDeTelas.fecharModal();
        InicializadorDeTelas.setRoot("tela-gerenciamento-tutor");
    }
    
    public void buscarPorCPF() {
        resultados.clear();
        try {
            resultados.add(Main.buscarTutor.buscarPorCPF(txtEntradaBusca.getText()));
            resultadoBusca.setItems(resultados);
        } catch (Exception e) {
            alerta("Erro", e.getMessage(), AlertType.ERROR);
        }
    }

    public void buscarPorNome() {
        resultados.clear();
        try {
            resultados.addAll(Main.buscarTutor.buscarPorNome(txtEntradaBusca.getText()));
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
