package com.vettodos.model.domain.usecases.util;

import java.util.ArrayList;
import java.util.List;

public class Notificador {

    List<String> msgsErro;

    public Notificador() {
        msgsErro = new ArrayList<>();
    }

    public void adicionaMsg(String msg) {
        msgsErro.add(msg);
    }

    public boolean haErro() {
        if(msgsErro.size() == 0) return false;
        return true;
    }

    public String notificar() {
        if(!haErro()) return "Não há erros!";
        StringBuilder msgs = new StringBuilder();
        msgsErro.stream().forEach((msg -> msgs.append(msg)));
        return msgs.toString();
    }
    
}
