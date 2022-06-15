package com.vettodos.model.domain.entities.animal;

public enum Sexo {

    FEMEA("Fêmea"), 
    MACHO("Macho");

    private String sexo;

    private Sexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}
