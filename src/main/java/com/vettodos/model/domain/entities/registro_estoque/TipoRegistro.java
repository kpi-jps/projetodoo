package com.vettodos.model.domain.entities.registro_estoque;

import java.util.ArrayList;
import java.util.List;

public enum TipoRegistro {
    ENTRADA("Entrada"),
    SAIDA("Saída");

    private String tipo;
    
    private TipoRegistro(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static List<String> listarTipos() {
        List <String> tipos = new ArrayList<>();
        for (TipoRegistro t : TipoRegistro.values()) tipos.add(t.getTipo());
        return tipos;
    }

    public static TipoRegistro getCategoria(String tipo) {
        for (TipoRegistro t : TipoRegistro.values()) 
            if(tipo.equals(t.getTipo())) return t;
        return null;
    }
    
}
