package com.vettodos.model.domain.entities.individuo;

public enum Credencial {
    ADMINISTRADOR("A"), VETERINARIO("V");

    private String credencial;
    private Credencial(String credencial) {
        this.credencial = credencial;
    }
    public String getNomeCredencial() {
        return credencial;
    }
}
