package com.vettodos.model.domain.usecases.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validadores {

    public static boolean nuloOuVazio(Object o) {
        if (o == null) return true;
        if(o instanceof String){
            final String string = (String) o;
            return string.isBlank() || string.isEmpty();
        }
        return false;   
    }

    public static boolean validaEmail(String email) {
        String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";   
        Pattern padraoEmail = Pattern.compile(regexEmail);  
        Matcher combinador = padraoEmail.matcher(email);
        return combinador.matches();
    }

    public static boolean validaCPF(String cpf) {
        String regexCPF = "^\\d{11}$";  //^\d{3}\.\d{3}\.\d{3}\-\d{2}$  com os pontos
        Pattern padraoCPF = Pattern.compile(regexCPF);  
        Matcher combinador = padraoCPF.matcher(cpf);
        return combinador.matches();
    }

    public static boolean validaCEP(String cep) {
        String regexCEP = "^\\d{8}$";  //^\d{5}-\\d{3}$  com o hífen
        Pattern padraoCEP = Pattern.compile(regexCEP);  
        Matcher combinador = padraoCEP.matcher(cep);
        return combinador.matches();
    }

    public static boolean validaNumero(String numero) {
        try {
            int n = Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validaNumeroReal(String numero) {
        try {
            Double n = Double.parseDouble(numero);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validaNumeroRealMaioQueZero(String numero) {
        try {
            Double n = Double.parseDouble(numero);
            if(n <= 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
}
