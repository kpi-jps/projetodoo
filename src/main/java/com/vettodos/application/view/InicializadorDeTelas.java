package com.vettodos.application.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InicializadorDeTelas extends Application{

    private static Scene scene;
    private static Object controller;
    private static Stage stage;
    private static Scene modalScene;
    private static Stage modalStage;
    
    public static void fecharModal() {
        modalStage.close();
    }

    public static Stage getStage() {
        return stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        InicializadorDeTelas.stage = stage;
        scene = new Scene(loadFXML("tela-autenticacao"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void mostrarTelaModal(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controller);
        Parent parent = fxmlLoader.load(InicializadorDeTelas.class.getResource(fxml + ".fxml").openStream());
        modalStage = new Stage();
        modalScene = new Scene(parent);
        modalStage.setScene(modalScene);
        modalStage.setResizable(false);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.showAndWait();
    }

    public static void trocarTelaModal(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controller);
        Parent parent = fxmlLoader.load(InicializadorDeTelas.class.getResource(fxml + ".fxml").openStream());
        modalScene.setRoot(parent);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        stage.sizeToScene();
        stage.setResizable(true);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = fxmlLoader.load(InicializadorDeTelas.class.getResource(fxml + ".fxml").openStream());
        controller = fxmlLoader.getController();
        return parent;
    }

    public static void main(String[] args) {
        launch();
    }
  
}
