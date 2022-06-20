package com.vettodos.application.controller;

import java.io.IOException;

import com.vettodos.Main;
import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.animal.Animal;

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

public class ControllerBuscarAnimalParaAtendimento {
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
    private TableColumn<Animal, String> colResultado;
    @FXML
    private TableView<Animal> resultadoBusca;
   
    private ObservableList<Animal> resultados;

    @FXML
    private void initialize() throws Exception {
        choiceBox1.setVisible(false);
        resultados = FXCollections.observableArrayList();
        labelTituloTela.setText("Buscar Animal");
        btnBuscaTipo1.setText("Buscar por nome");
        btnBuscaTipo2.setText("Buscar por Tutor");
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
            buscarPorNome();  
        });

        btnBuscaTipo2.setOnAction(event ->  {
            buscarPorTutor();  
        });
        
    }
    
    public void retornarResultadoBusca() throws IOException {
        Main.animalSelecionado = resultadoBusca.getSelectionModel().getSelectedItem();
        Main.tutorSelecionado = Main.animalSelecionado.getTutor();
        InicializadorDeTelas.fecharModal();
        InicializadorDeTelas.setRoot("tela-registrar-atendimento");
    }
    public void buscarPorTutor() {
        resultados.clear();
        try {
            resultados.addAll(Main.buscarAnimal.buscarPorTutor(txtEntradaBusca.getText()));
            resultadoBusca.setItems(resultados);
        } catch (Exception e) {
            alerta("Erro", e.getMessage(), AlertType.ERROR);
        }
    }

    public void buscarPorNome() {
        resultados.clear();
        try {
            resultados.addAll(Main.buscarAnimal.buscarPorNome(txtEntradaBusca.getText()));
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
