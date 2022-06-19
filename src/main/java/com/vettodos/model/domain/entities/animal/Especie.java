package com.vettodos.model.domain.entities.animal;

import java.util.ArrayList;
import java.util.List;

public enum Especie {
    AVE("Ave"), 
    CACHORRO("Cachorro"), 
    COELHO("Coelho"),
    FURAO("Furão"),
    GATO("Gato"), 
    HAMSTER("Hamster"),
    PEIXE("Peixe"),
    PORQUINHO_DA_INDIA("Porquinho-da-índia"),
    OUTRO("Outro");

    private String especie;

    private Especie(String especie) {
        this.especie = especie;
    }

    public String getNomeEspecie() {
        return especie;
    }

    public static Especie retornaEspecie(String nomeEspecie) {
        for (Especie e : Especie.values()) 
            if(e.getNomeEspecie().equals(nomeEspecie)) return e;
        return null;
    }

    public static List<String> listar() {
        List<String> especies = new ArrayList<>();
        for (Especie e : Especie.values()) especies.add(e.getNomeEspecie());
        return especies;
    }

}
