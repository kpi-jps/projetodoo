package com.vettodos.application.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.entities.animal.Especie;
import com.vettodos.model.domain.entities.animal.Porte;
import com.vettodos.model.domain.entities.animal.Raca;
import com.vettodos.model.domain.usecases.util.EntradaInvalidaException;
import com.vettodos.model.domain.usecases.util.Notificador;
import com.vettodos.model.domain.usecases.util.Validadores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.vettodos.Main.*;

public class ControllerGerenciarAnimal {

    @FXML
    private Button btnBuscarTutor;
    @FXML
    private Button btnSalvarOuEditar;
    @FXML
    private Button btnVoltar;
    @FXML
    private CheckBox checkboxStatus;
    @FXML
    private ChoiceBox<String> choiceBoxEspecie;
    @FXML
    private ChoiceBox<String> choiceBoxPorte;
    @FXML
    private ChoiceBox<String> choiceBoxRaca;
    @FXML
    private ChoiceBox<String> choiceBoxSexo;
    @FXML
    private ImageView iconeUsuario;
    @FXML
    private Label labelAnoNascimento;
    @FXML
    private Label labelEspecie;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelPeso;
    @FXML
    private Label labelPorte;
    @FXML
    private Label labelRaca;
    @FXML
    private Label labelSexo;
    @FXML
    private Label labelTituloTela;
    @FXML
    private ImageView logo;
    @FXML
    private TextField txtAnoNascimento;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPeso;
    
    @FXML 
    private void initialize() throws Exception {
        FileInputStream caminhoImagemLogo = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/logo.png").getPath());
        FileInputStream caminhoImagemIconeUsuario = new FileInputStream(this.getClass().getResource("/com/vettodos/application/img/usuario.png").getPath());
        Image imagemLogo = new Image(caminhoImagemLogo);
        Image imagemIconeUsuario = new Image(caminhoImagemIconeUsuario);
        logo.setImage(imagemLogo);
        iconeUsuario.setImage(imagemIconeUsuario);
        setAmbiente();
        if(modoOperacao == ModoOperacao.CRIAR) setModoCriar();
        if(modoOperacao == ModoOperacao.EDITAR) setModoEditar();
    }

    private void setAmbiente() {
        choiceBoxEspecie.setOnAction(event -> {
            choiceBoxRaca.getItems().clear();
            choiceBoxRaca.getItems().setAll(Raca.listarRacas(choiceBoxEspecie.getValue()));
            choiceBoxRaca.setValue(choiceBoxRaca.getItems().get(0));
        });
        btnBuscarTutor.setOnAction(event -> {
            try {
                InicializadorDeTelas.mostrarTelaModal("tela-busca", new ControllerBuscarTutor());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        choiceBoxEspecie.getItems().setAll(Especie.listar());
        choiceBoxEspecie.setValue(choiceBoxEspecie.getItems().get(0));
        choiceBoxSexo.getItems().add("F");
        choiceBoxSexo.getItems().add("M");
        choiceBoxSexo.setValue(choiceBoxSexo.getItems().get(0));
        choiceBoxPorte.getItems().addAll(Porte.listar());
        choiceBoxPorte.setValue(choiceBoxPorte.getItems().get(0));
        choiceBoxRaca.getItems().setAll(Raca.listarRacas(choiceBoxEspecie.getValue()));
        choiceBoxRaca.setValue(choiceBoxRaca.getItems().get(0));
        txtAnoNascimento.setText("0");
        txtPeso.setText("0.0");
    }
   
    private void setModoCriar() {
        tutorSelecionado = null;
        labelTituloTela.setText("Cadastro Animal");
        btnSalvarOuEditar.setText("Salvar");
        checkboxStatus.setSelected(false);
        checkboxStatus.setVisible(false);
        
    }

    private void setModoEditar() {
        labelTituloTela.setText("Edição Usuário");
        btnSalvarOuEditar.setText("Editar");
        checkboxStatus.setSelected(false);
        checkboxStatus.setVisible(true);
        setDados(animalSelecionado);
    }

    public void voltar() throws IOException {
        InicializadorDeTelas.setRoot("tela-principal");
    }

    private Notificador validaDados() {
        Notificador notificador = new Notificador();
        if(tutorSelecionado == null) 
            notificador.adicionaMsg("Tutor não selecionado! ");
        if(!Validadores.validaNumero((txtAnoNascimento.getText())))
            notificador.adicionaMsg("Ano nascimento inválido! ");
        if(!Validadores.validaNumeroReal((txtPeso.getText())))
            notificador.adicionaMsg("Peso inválido! ");
        return notificador;
    }

    private Animal getDados() {
        return new Animal(
            txtNome.getText(), 
            Especie.retornaEspecie(choiceBoxEspecie.getValue()), 
            Raca.retornaRaca(choiceBoxRaca.getValue()), 
            Porte.retornaPorte(choiceBoxPorte.getValue()), 
            choiceBoxSexo.getValue(), 
            Double.parseDouble(txtPeso.getText()), 
            Integer.parseInt(txtAnoNascimento.getText()),
            !checkboxStatus.isSelected(), 
            tutorSelecionado
        );
    }

    private void setDados(Animal animal) {
        checkboxStatus.setSelected(!animal.isStatus());
        choiceBoxEspecie.setValue(animal.getEspecie().getNomeEspecie());
        choiceBoxSexo.setValue(animal.getSexo());
        choiceBoxRaca.setValue(animal.getRaca().getNomeRaca());
        choiceBoxPorte.setValue(animal.getPorte().getPorte());
        txtAnoNascimento.setText(animal.getanoNascimento().toString());
        txtPeso.setText(animal.getPeso().toString());
        txtNome.setText(animal.getNome());
    }

    private void limparDados() {
        choiceBoxEspecie.setValue(choiceBoxEspecie.getItems().get(0));
        choiceBoxSexo.setValue(choiceBoxSexo.getItems().get(0));
        choiceBoxRaca.setValue(choiceBoxRaca.getItems().get(0));
        choiceBoxPorte.setValue(choiceBoxPorte.getItems().get(0));
        txtAnoNascimento.setText("0");
        txtPeso.setText("0.0");
        txtNome.setText("");
        tutorSelecionado = null;
    }

    public void salvarOuEditar() throws IOException {
        if(modoOperacao == ModoOperacao.CRIAR) salvar();
        if(modoOperacao == ModoOperacao.EDITAR) editar();
    }

    private void salvar() throws IOException {
        Animal animal = null;
        try {
            Notificador notificador = validaDados();
            if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
            animal = getDados();
            cadastrarAnimal.cadastrar(animal);
            alerta("Aviso", "Animal cadastrado com sucesso!" , AlertType.INFORMATION);
            limparDados();
        } catch (Exception e) {
            if(e instanceof IOException) throw e;
            alerta("Erro", e.getMessage(), AlertType.ERROR);
        } 
    }

    private void editar() throws IOException {
        Animal animalNovo = null;        
        try {
            Notificador notificador = validaDados();
            if(notificador.haErro()) throw new EntradaInvalidaException(notificador.notificar());
            animalNovo = getDados();
            animalNovo.setId(animalSelecionado.getId());
            editarAnimal.editar(animalNovo);
            alerta("Aviso", "Animal editado com sucesso!" , AlertType.INFORMATION);
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
