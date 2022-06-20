package com.vettodos;

import com.vettodos.application.controller.ModoOperacao;
import com.vettodos.application.repository.sqlite.CriadorBancoDeDados;
import com.vettodos.application.repository.sqlite.SQLAnimalDAO;
import com.vettodos.application.repository.sqlite.SQLAtendimentoDAO;
import com.vettodos.application.repository.sqlite.SQLProdutoDAO;
import com.vettodos.application.repository.sqlite.SQLRegistroEstoqueDAO;
import com.vettodos.application.repository.sqlite.SQLTutorDAO;
import com.vettodos.application.repository.sqlite.SQLUsuarioDAO;
import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.animal.Animal;
import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.entities.individuo.Usuario;
import com.vettodos.model.domain.entities.produto.Produto;
import com.vettodos.model.domain.usecases.animal.AnimalDAO;
import com.vettodos.model.domain.usecases.animal.BuscarAnimal;
import com.vettodos.model.domain.usecases.animal.CadastrarAnimal;
import com.vettodos.model.domain.usecases.animal.EditarAnimal;
import com.vettodos.model.domain.usecases.atendimento.AtendimentoDAO;
import com.vettodos.model.domain.usecases.atendimento.RealizarAtendimento;
import com.vettodos.model.domain.usecases.individuo.AutenticarUsuario;
import com.vettodos.model.domain.usecases.individuo.BuscarTutor;
import com.vettodos.model.domain.usecases.individuo.BuscarUsuario;
import com.vettodos.model.domain.usecases.individuo.CadastrarTutor;
import com.vettodos.model.domain.usecases.individuo.CadastrarUsuario;
import com.vettodos.model.domain.usecases.individuo.EditarSenhaUsuario;
import com.vettodos.model.domain.usecases.individuo.EditarTutor;
import com.vettodos.model.domain.usecases.individuo.EditarUsuario;
import com.vettodos.model.domain.usecases.individuo.TutorDAO;
import com.vettodos.model.domain.usecases.individuo.UsuarioDAO;
import com.vettodos.model.domain.usecases.produto.BuscarProduto;
import com.vettodos.model.domain.usecases.produto.CadastrarProduto;
import com.vettodos.model.domain.usecases.produto.EditarProduto;
import com.vettodos.model.domain.usecases.produto.ProdutoDAO;
import com.vettodos.model.domain.usecases.registro_estoque.RealizarRegistroEstoque;
import com.vettodos.model.domain.usecases.registro_estoque.RegistroEstoqueDAO;


public class Main {
    
    private static UsuarioDAO usuarioDAO;
    private static TutorDAO tutorDAO;
    private static AnimalDAO animalDAO;
    private static ProdutoDAO produtoDAO;
    private static RegistroEstoqueDAO registroEstoqueDAO;
    private static AtendimentoDAO atendimentoDAO;

    public static BuscarTutor buscarTutor;
    public static BuscarUsuario buscarUsuario;
    public static AutenticarUsuario autenticarUsuario;
    public static CadastrarUsuario cadastrarUsuario;
    public static EditarUsuario editarUsuario;
    public static EditarSenhaUsuario editarSenhaUsuario;
    public static CadastrarTutor cadastrarTutor;
    public static EditarTutor editarTutor;
    public static CadastrarAnimal cadastrarAnimal;
    public static BuscarAnimal buscarAnimal;
    public static EditarAnimal editarAnimal;
    public static CadastrarProduto cadastrarProduto;
    public static EditarProduto editarProduto;
    public static BuscarProduto buscarProduto;
    public static RealizarRegistroEstoque realizarRegistroEstoque;
    public static RealizarAtendimento realizarAtendimento;
    
    public static ModoOperacao modoOperacao;
    public static Usuario usuarioAutenticado;
    public static Usuario usuarioSelecionado;
    public static Tutor tutorSelecionado;
    public static Animal animalSelecionado;
    public static Produto produtoSelecionado;

    public static void main(String[] args) {
        usuarioAutenticado = null;
        CriadorBancoDeDados.criarSeNaoExistir();
        carregarDAOs();
        carregarCasosDeUso();
        InicializadorDeTelas.main(args);
    }

    
    private static void carregarDAOs() {
        usuarioDAO = new SQLUsuarioDAO();
        tutorDAO = new SQLTutorDAO();
        produtoDAO = new SQLProdutoDAO();
        animalDAO = new SQLAnimalDAO();
        registroEstoqueDAO = new SQLRegistroEstoqueDAO();
        atendimentoDAO = new SQLAtendimentoDAO();
    }
    private static void carregarCasosDeUso() {
        buscarTutor = new BuscarTutor(tutorDAO);
        buscarUsuario = new BuscarUsuario(usuarioDAO);
        autenticarUsuario = new AutenticarUsuario(usuarioDAO);
        cadastrarUsuario = new CadastrarUsuario(usuarioDAO);
        editarSenhaUsuario = new EditarSenhaUsuario(usuarioDAO);
        editarUsuario = new EditarUsuario(usuarioDAO);
        cadastrarTutor = new CadastrarTutor(tutorDAO);
        editarTutor = new EditarTutor(tutorDAO);
        cadastrarAnimal = new CadastrarAnimal(animalDAO);
        buscarAnimal = new BuscarAnimal(animalDAO);
        editarAnimal = new EditarAnimal(animalDAO);
        cadastrarProduto = new CadastrarProduto(produtoDAO);
        editarProduto = new EditarProduto(produtoDAO);
        buscarProduto = new BuscarProduto(produtoDAO);
        realizarRegistroEstoque = new RealizarRegistroEstoque(registroEstoqueDAO);
        realizarAtendimento = new RealizarAtendimento(atendimentoDAO);
    }
}
