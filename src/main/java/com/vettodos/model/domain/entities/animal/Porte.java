package com.vettodos.model.domain.entities.animal;


public enum Porte {

    MEDIO("Médio"),
    PEQUENO("Pequeno"),
    GRANDE("Grande");

    private String porte;

    private Porte(String porte) {
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }
}
