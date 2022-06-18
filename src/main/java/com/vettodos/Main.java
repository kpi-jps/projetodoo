package com.vettodos;

import java.util.List;

import com.vettodos.application.controller.ModoOperacao;
import com.vettodos.application.repository.sqlite.CriadorBancoDeDados;
import com.vettodos.application.repository.sqlite.SQLProdutoDAO;
import com.vettodos.application.repository.sqlite.SQLTutorDAO;
import com.vettodos.application.repository.sqlite.SQLUsuarioDAO;
import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.individuo.Tutor;
import com.vettodos.model.domain.entities.individuo.Usuario;
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
import com.vettodos.model.domain.usecases.produto.ProdutoDAO;


public class Main {
    
    private static UsuarioDAO usuarioDAO;
    private static TutorDAO tutorDAO;
    private static ProdutoDAO produtoDAO;

    public static BuscarTutor buscarTutor;
    public static BuscarUsuario buscarUsuario;
    public static AutenticarUsuario autenticarUsuario;
    public static CadastrarUsuario cadastrarUsuario;
    public static EditarUsuario editarUsuario;
    public static EditarSenhaUsuario editarSenhaUsuario;
    public static CadastrarTutor cadastrarTutor;
    public static EditarTutor editarTutor;
    
    public static ModoOperacao modoOperacao;
    public static Usuario usuarioAutenticado;
    public static Usuario usuarioSelecionado;
    public static Tutor tutorSelecionado;

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
    }
}
