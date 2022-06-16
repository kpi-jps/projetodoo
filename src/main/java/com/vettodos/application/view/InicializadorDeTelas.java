package com.vettodos.application.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InicializadorDeTelas extends Application{

    private static Scene scene;
    private static Object controller;
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        InicializadorDeTelas.stage = stage;
        scene = new Scene(loadFXML("tela-autenticacao"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        stage.sizeToScene();
        stage.setResizable(true);
        
        //stage.show();
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

    public static Object getController() {
        return controller;
    }

    public static Stage getStage() {
        return stage;
    }

    

    
}
