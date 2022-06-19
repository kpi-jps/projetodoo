package com.vettodos.model.domain.entities.endereco;

import java.util.ArrayList;
import java.util.List;

public enum Estado {

    AC("AC", "Acre"),
    AL("AL", "Alagoas"),
    AM("AM", "Amazonas"),
    BA("BA", "Bahia"),
    CE("CE", "Ceará"),
    DF("DF", "Distrito Federal"),
    ES("ES", "Espírito Santo"),
    GO("GO", "Goiás"),
    MA("MA", "Maranhão"),
    MT("MT", "Mato Grosso"),
    MS("MS", "Mato Grosso do Sul"),
    MG("MG", "Minas Gerais"),
    PA("PA", "Pará"),
    PB("PB", "Paraíba"),
    PR("PR", "Paraná"),
    PE("PE", "Pernambuco"),
    PI("PI", "Piauí"),
    RJ("RJ", "Rio de Janeiro"),
    RN("RN", "Rio Grande do Norte"),
    RS("RS", "Rio Grande do Sul"),
    RO("RO", "Rondônia"),
    RR("RR", "Roraima"),
    SC("SC", "Santa Catarina"),
    SP("SP", "São Paulo"),
    SE("SE", "Sergipe"),
    TO("TO", "Tocantins");
    
    private String nome;
    private String sigla;

    private Estado(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public static List<String> listarSiglaEstados() {
        List <String> siglas = new ArrayList<>();
        for (Estado estado : Estado.values()) siglas.add(estado.getSigla());
        return siglas;
    }

    public static Estado getEstado(String siglaEstado) {
        for (Estado estado : Estado.values()) 
            if(siglaEstado.equals(estado.getSigla())) return estado;
        return null;
    }
}
