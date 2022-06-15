package com.vettodos.model.domain.entities.produto;

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
}
