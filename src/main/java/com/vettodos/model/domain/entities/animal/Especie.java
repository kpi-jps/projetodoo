package com.vettodos.model.domain.entities.animal;

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

    public String getEspecie() {
        return especie;
    }


}
