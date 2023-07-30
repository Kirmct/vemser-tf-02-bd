package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pessoa {

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Integer idPessoa;
    private String nome;
    private String cep;
    private LocalDate dataNascimento;
    private String cpf;
    private Double salarioMensal;

    public Pessoa() {
    }

    public Pessoa(String nome, String cep, String dataNascimento, String cpf, Double salarioMensal) {
        this.nome = nome;
        this.cep = cep;
        if (dataNascimento == ""){
            this.dataNascimento = null;
        }else {
            this.dataNascimento = LocalDate.parse(dataNascimento, fmt);
        }
        this.cpf = cpf;
        this.salarioMensal = salarioMensal;
    }

    // Getters & Setters
    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(Double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }
}
