package com.vettodos.model.domain.entities.individuo;

public class Veterinario extends Usuario{
    
    private String registroProfissional;

    public Veterinario ( String email, String nome, String telefone, boolean status,String registroProfissional) {
        this(null, email, nome, telefone, status, registroProfissional);
    }
    
    public Veterinario(Long id, String email, String nome, String telefone, boolean status,String registroProfissional) {
        super(id, email, nome, telefone, status);
        this.registroProfissional = registroProfissional;
        this.setCredencial(Credencial.VETERINARIO);
    }

    
    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }
    
    @Override
    public String toString() {
        return "{" +
            "id='" + getId() + "'" +
            " nome='" + getNome() + "'" +
            ", status='" + isStatus() + "'" +
            ", credencial='" + getCredencial().getNomeCredencial() + "'" +
            ", registro_profissional='"+ getRegistroProfissional() + "'" +
            "}";
    }
}
