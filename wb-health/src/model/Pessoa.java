package model;
public abstract class Pessoa {
    private static Integer contadorId = 0;
    private Integer id;
    private String nome;
    private String cep;

    public Pessoa(String nome, String cep) {
        this.id = ++contadorId;
        this.nome = nome;
        this.cep = cep;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
