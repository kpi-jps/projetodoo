package com.vettodos.model.domain.entities.animal;

import java.util.ArrayList;
import java.util.List;

public enum Raca {

    //Raças de cachorro
    AFGHAN_HOUND(Especie.CACHORRO, "Afghan Hound"),
    AKITA(Especie.CACHORRO, "Akita"),
    AMERICAN_BULLY(Especie.CACHORRO, "American Bully"),
    BEAGLE(Especie.CACHORRO, "Beagle"),
    BASENJI(Especie.CACHORRO, "Basenji"),
    BASSET(Especie.CACHORRO, "Basset"),
    BORDER_COLLIE(Especie.CACHORRO, "Border Collie"),
    BOXER(Especie.CACHORRO, "Boxer"),
    BULLDOG(Especie.CACHORRO, "Buldog"),
    CHIHUAHUA(Especie.CACHORRO, "Chihuahua"),
    CHOW_CHOW(Especie.CACHORRO, "Chow chow"),
    DALMATA(Especie.CACHORRO, "Dálmata"),
    DOBERMAN(Especie.CACHORRO, "Doberman"),
    DOGUE_ALEMAO(Especie.CACHORRO, "Dogue alemão"),
    FILA(Especie.CACHORRO, "Fila"),
    GOLDEN(Especie.CACHORRO, "Golden"),
    HUSKY_SIBERIANO(Especie.CACHORRO, "Husky siberiano"),
    LABRADOR(Especie.CACHORRO, "Labrador"),
    LHASA_APSO(Especie.CACHORRO, "Lhasa apso"),
    MALTES(Especie.CACHORRO, "Maltês"),
    PASTOR_ALEMAO(Especie.CACHORRO, "Pastor Alemão"),
    PEQUINES(Especie.CACHORRO, "Pequinês"),
    PINSCHER(Especie.CACHORRO, "Pinscher"),
    PIT_BULL(Especie.CACHORRO, "Pit bull"),
    PUG(Especie.CACHORRO, "Pug"),
    POODLE(Especie.CACHORRO, "Poodle"),
    ROTTWEILER(Especie.CACHORRO, "Rottweiler"),
    SAO_BERNARDO(Especie.CACHORRO, "São bernardo"),
    SHAR_PEI(Especie.CACHORRO, "Shar pei"),
    SHIH_TZU(Especie.CACHORRO, "Shih tzu"),
    YORKSHIRE(Especie.CACHORRO, "Yorkshire"),
    CACHORRO_SRD(Especie.CACHORRO, "Sem raça definida"),
    

    //Raças de gato
    ABSSINIO(Especie.GATO, "Abissínio"),
    ANGORA(Especie.GATO, "Angorá"),
    AMERICAN_SHORTHAIR(Especie.GATO, "American Shorthair"),
    ASHERA(Especie.GATO, "Ashera"),
    AZUL_RUSSO(Especie.GATO, "Azul Russo"),
    BIRMANO(Especie.GATO, "Birmano"),
    EXOTICO(Especie.GATO, "Exótico"),
    HIMALAIO(Especie.GATO, "Himalaio"),
    MAINE_COON(Especie.GATO, "Maine Coon"),
    PELO_CURTO(Especie.GATO, "Pelo Curto"),
    PERSA(Especie.GATO, "Persa"),
    RAGDOLL(Especie.GATO, "Ragdoll"),
    SIAMES(Especie.GATO, "Siamês"),
    SIBERIANO(Especie.GATO, "Siberiano"),
    SCOTTISH_FOLD(Especie.GATO, "Scottish Fold"),
    SNOWSHOE(Especie.GATO, "Snowshoe"),
    SPHYNX(Especie.GATO, "Sphynx"),
    GATO_SRD(Especie.GATO, "Sem raça definida"),

    //Raças de aves
    ARARA(Especie.AVE, "Arara"),
    CACATUA(Especie.AVE, "Cacatua"),
    CALOPSITA(Especie.AVE, "Calopsita"),
    CANARIO(Especie.AVE, "canário"),
    CORVO(Especie.AVE, "Corvo"),
    CURIO(Especie.AVE, "Curió"),
    GALINHA(Especie.AVE, "Galinha"),
    GRALHA(Especie.AVE, "Gralha"),
    PARDAL(Especie.AVE, "Pardal"),
    PAPAGAIO(Especie.AVE, "Papagaio"),
    PATO(Especie.AVE, "Pato"),
    PERU(Especie.AVE, "Peru"),
    PERIQUITO_AUSTRALIANO(Especie.AVE, "Periquito Australiano"),
    PINTASSILGO(Especie.AVE, "Pintassilgo"),

    // Raças de coelho
    COELHO_ANGORA(Especie.COELHO, "Angorá"),
    FUZZY_LOP(Especie.COELHO, "Fuzzy Lop"),
    HOLLAND_LOP(Especie.COELHO, "Holland Lop"),
    LEAO(Especie.COELHO, "Leão"),
    MINI_LOP(Especie.COELHO, "Mini Lop"),
    NOVA_ZELANDIA(Especie.COELHO, "Nova Zelândia"),
    REX(Especie.COELHO, "Rex"),
    TAN(Especie.COELHO, "Tan"),
    TEDDY(Especie.COELHO, "Teddy"),
    TOY(Especie.COELHO, "Toy"),

    //Raças de furão
    BULL(Especie.FURAO, "Bull"),
    STANDARD(Especie.FURAO, "Standard"),
    WHIPPET(Especie.FURAO, "Whippet"),

    //Raças de HAMSTERS
    SIRIO(Especie.HAMSTER, "Hamster sírio"),
    ANAO_RUSSO(Especie.HAMSTER, "Anão russo"),
    CHINES(Especie.HAMSTER, "Chinês"),
    ROBOROVSKI(Especie.HAMSTER, "Roborovski"),

    //Peixes
    ACARA_BANDEIRA(Especie.PEIXE, "Acará-bandeira"),
    ACARA_DISCO(Especie.PEIXE, "Acará-disco"),
    BETTA(Especie.PEIXE, "Betta"),
    GUPPY(Especie.PEIXE, "Guppy"),
    NEON(Especie.PEIXE, "Tetra Neon"),
    PEIXE_DOURADO(Especie.PEIXE, "Peixe Dourado"),

    // Raças de Porquinho-da-índia
    INGLES(Especie.PORQUINHO_DA_INDIA, "Inglês"),
    PERUANO(Especie.PORQUINHO_DA_INDIA, "Peruano"),
    ABISSINIO(Especie.PORQUINHO_DA_INDIA, "Abissínio"),
    PORQUINHO_DA_INDIA_REX(Especie.PORQUINHO_DA_INDIA, "Rex"),
    PORQUINHO_DA_INDIA_TEDDY(Especie.PORQUINHO_DA_INDIA, "Teddy"),
    ALPACA(Especie.PORQUINHO_DA_INDIA, "Alpaca"),
    COROADO_INGLES(Especie.PORQUINHO_DA_INDIA, "Coroado inglês"),
    RIDGEBACK(Especie.PORQUINHO_DA_INDIA, "Ridgeback"),
    SKINNY(Especie.PORQUINHO_DA_INDIA, "Skinny");

    private Especie especie;
    private String raca;

    

    private Raca (Especie especie, String raca) {
        this.especie = especie;
        this.raca = raca;
    }

    public Especie getEspecie() {
        return especie;
    }

    public String getNomeRaca() {
        return raca;
    }

    public static Raca retornaRaca(String nomeRaca) {
        for (Raca r : Raca.values()) 
            if(r.getNomeRaca().equals(nomeRaca)) return r;
        return null;
    }

    public static List <String> listarRacas(String nomeEspecie) {
        List <String> racas = new ArrayList<>();
        for (Raca raca : Raca.values()) 
            if(raca.getEspecie().getNomeEspecie().equals(nomeEspecie)) racas.add(raca.getNomeRaca());
        return racas;
    }

    
}
