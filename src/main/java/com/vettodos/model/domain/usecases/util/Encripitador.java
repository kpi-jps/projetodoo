package com.vettodos.model.domain.usecases.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encripitador {

    public static String encripitar(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean checarSenha(String senha, String hash) {
        return BCrypt.checkpw(senha, hash);
    }
    
}
