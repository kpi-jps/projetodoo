package com.vettodos;

import com.vettodos.application.repository.sqlite.CriadorBancoDeDados;
import com.vettodos.application.repository.sqlite.SQLProdutoDAO;
import com.vettodos.application.repository.sqlite.SQLTutorDAO;
import com.vettodos.application.repository.sqlite.SQLUsuarioDAO;
import com.vettodos.application.view.InicializadorDeTelas;
import com.vettodos.model.domain.entities.individuo.Usuario;
import com.vettodos.model.domain.usecases.individuo.AutenticarUsuario;
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

    public static AutenticarUsuario autenticarUsuario;
    public static CadastrarUsuario cadastrarUsuario;
    public static EditarUsuario editarUsuario;
    public static EditarSenhaUsuario editarSenhaUsuario;
    public static CadastrarTutor cadastrarTutor;
    public static EditarTutor editarTutor;
    
    public static Usuario usuarioAutenticado;

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
        autenticarUsuario = new AutenticarUsuario(usuarioDAO);
        cadastrarUsuario = new CadastrarUsuario(usuarioDAO);
        editarSenhaUsuario = new EditarSenhaUsuario(usuarioDAO);
        editarUsuario = new EditarUsuario(usuarioDAO);
        cadastrarTutor = new CadastrarTutor(tutorDAO);
        editarTutor = new EditarTutor(tutorDAO);
    }
}
