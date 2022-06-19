package com.vettodos.model.domain.entities.animal;

import java.util.ArrayList;
import java.util.List;

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

    public static Porte retornaPorte(String porte) {
        for (Porte p : Porte.values()) 
            if(p.getPorte().equals(porte)) return p;
        return null;
    }

    public static List<String> listar() {
        List<String> portes = new ArrayList<>();
        for (Porte p : Porte.values()) portes.add(p.getPorte());
        return portes;
    }
}
