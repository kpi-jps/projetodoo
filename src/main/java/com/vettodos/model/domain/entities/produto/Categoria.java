package com.vettodos.model.domain.entities.produto;

import java.util.ArrayList;
import java.util.List;

public enum Categoria {
    
    MEDICAMENTO("Medicamento"),
    RACAO_ANIMAL("Ração animal"),
    MATERIAL_CiRURGICO("Material cirúrgico");

    private String categoria;

    private Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return categoria;
    }

    public static List<String> listarCategorias() {
        List <String> categorias = new ArrayList<>();
        for (Categoria c : Categoria.values()) categorias.add(c.getNome());
        return categorias;
    }

    public static Categoria getCategoria(String categoria) {
        for (Categoria c: Categoria.values()) 
            if(categoria.equals(c.getNome())) return c;
        return null;
    }
}
