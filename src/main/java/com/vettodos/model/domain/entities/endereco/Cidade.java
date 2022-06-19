package com.vettodos.model.domain.entities.endereco;

import java.util.ArrayList;
import java.util.List;

public enum Cidade {
    ARACAJU("Aracaju", Estado.SE),
    ARARAQUARA("Araraquara", Estado.SP),
    BRASILIA("Brasília", Estado.DF),
    BAURU("Bauru", Estado.SP),
    BELEM("Belém", Estado.PA),
    BELO_HORIZONTE("Belo Horizonte", Estado.MG),
    BOA_VISTA("Boa Vista", Estado.RR),
    BLUMENAU("Blumenau", Estado.SC),
    CAMPINAS("Campinas", Estado.SP),
    CAMPO_GRANDE("Campo Grande", Estado.MS),
    CAMPINA_GRANDE("Campina Grande", Estado.PB),
    CARUARU("Caruaru", Estado.PE),
    CASCAVEL("Cascavel", Estado.PR),
    CONTAGEM("Contagem", Estado.MG),
    CUIABA("Cuiabá", Estado.MT),
    CURITIBA("Curitiba", Estado.PR),
    DUQUE_DE_CAXIAS("Duque de Caxias", Estado.RJ),
    FRANCA("Franca", Estado.SP),
    FEIRA_DE_SANTaANA("Feira de Santana", Estado.BA),
    FLORIANOPOLIS("Florianópolis", Estado.SC),
    FORTALEZA("Fortaleza", Estado.CE),
    GOIANIA("Goiânia", Estado.GO),
    GUARUJA("Guarujá", Estado.SP),
    GUARULHOS("Guarulhos", Estado.SP),
    JOINVILLE("Joinville", Estado.SC),
    JOAO_PESSOA("João Pessoa", Estado.PB),
    JUIZ_DE_FORA("juiz de Fora", Estado.MG),
    LIMEIRA("Limeira", Estado.SP),
    LONDRINA("Londrina", Estado.PR),
    MACAPA("Macapá", Estado.PA),
    MACEIO("Maceió", Estado.AL),
    MANAUS("Manaus", Estado.AM),
    MARINGA("Maringá", Estado.PR),
    NATAL("Natal", Estado.RN),
    NITEROI("Niterói", Estado.RJ),
    NOVA_IGUACU("Nova Iguaçu", Estado.RJ),
    OLINDA("Olinda", Estado.PE),
    OSASCO("Osasco", Estado.SP),
    PALMAS("Palmas", Estado.TO),
    PELOTAS("Pelotas", Estado.RS),
    PETROLINA("Petrolina", Estado.PE),
    PETROPOLIS("Petrópolis", Estado.RJ),
    PIRACICABA("Piracicaba", Estado.SP),
    PONTA_GROSSA("Ponta Grossa", Estado.PR),
    PORTO_ALEGRE("Porto Alegre", Estado.RS),
    PORTO_VELHO("Porto Velho", Estado.RO),
    RECIFE("Recife", Estado.PE),
    RIBEIRAO_PRETO("Ribeirão Preto", Estado.SP),
    RIO_BRANCO("Rio Branco", Estado.AC),
    RIO_DE_JANEIRO("Rio de Janeiro", Estado.RJ),
    SALVADOR("Salvador", Estado.BA),
    SANTOS("Santos", Estado.SP),
    SANTO_ANDRE("Santo André", Estado.SP),
    SERRA("Serra", Estado.ES),
    SOROCABA("Sorocaba", Estado.SP),
    SAO_BERNARDO_DO_CAMPO("São Bernardo do Campo", Estado.SP),
    SAO_CARLOS("São Carlos", Estado.SP),
    SAO_GONÇALO("São Gonçalo", Estado.RJ),
    SAO_JOSE_DOS_CAMPOS("São José dos Campos", Estado.SP),
    SAO_JOSE_DO_RIO_PRETO("São José do Rio Preto", Estado.SP),
    SAO_LUIS("São Luís", Estado.MA),
    SAO_PAULO("São Paulo", Estado.SP),
    TAUBATE("Taubaté", Estado.SP),
    TERESINA("Teresina", Estado.PI),
    UBERABA("Uberaba", Estado.MG),
    UBERLANDIA("Uberlândia", Estado.MG),
    VILA_VELHA("Vila Velha", Estado.ES),
    VITORIA("Vitória", Estado.ES);

    private Estado estado;
    private String nomeCidade;

    private Cidade(String nomeCidade, Estado estado) {
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public static Cidade getCidade(String nomeCidade) {
        for (Cidade cidade : Cidade.values()) 
            if(nomeCidade.equals(cidade.getNomeCidade())) return cidade;
        return null;
    }

    public Estado getEstado() {
        return estado;
    }

    public List <Cidade> listarCidades(Estado estado) {
        List <Cidade> cidades = new ArrayList<>();
        for (Cidade cidade : Cidade.values()) 
            if(cidade.getEstado() == estado) cidades.add(cidade);
        return cidades;
    }

    public static List <String> listarNomeCidades(String sigleEstado) {
        List <String> cidades = new ArrayList<>();
        for (Cidade cidade : Cidade.values()) 
            if(cidade.getEstado().getSigla().equals(sigleEstado)) cidades.add(cidade.getNomeCidade());
        return cidades;
    }
}
