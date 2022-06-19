package com.vettodos.model.domain.entities.produto;

import java.util.ArrayList;
import java.util.List;

public enum UnidadeMedida {
    MG("mg"),
    KG("Kg"), 
    L("L"),
    ML("mL"),
    UNIDADE("Unidade");

    private String unidade;
    
    private UnidadeMedida(String unidade) {
        this.unidade = unidade;
    }

    public String getNome() {
        return unidade;
    }

    
    public static List<String> listarUnidades() {
        List <String> unidades = new ArrayList<>();
        for (UnidadeMedida u : UnidadeMedida.values()) unidades.add(u.getNome());
        return unidades;
    }

    public static UnidadeMedida getUnidade(String unidade) {
        for (UnidadeMedida u: UnidadeMedida.values()) 
            if(unidade.equals(u.getNome())) return u;
        return null;
    }
  }
