package com.vettodos.model.domain.entities.produto;

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
 }
