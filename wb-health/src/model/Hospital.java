package model;

import java.util.ArrayList;

public class Hospital {
    private String cnpj;
    private String nome;
    private ArrayList<Medico> medicos;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Atendimento> atendimentos;

    private ArrayList<Funcionario> funcionarios;

    public Hospital(String cnpj,
                    String nome,
                    ArrayList<Medico> medicos,
                    ArrayList<Paciente> pacientes,
                    ArrayList<Atendimento> atendimentos,
                    ArrayList<Funcionario> funcionarios) {

        this.cnpj = cnpj;
        this.nome = nome;
        this.medicos = medicos;
        this.pacientes = pacientes;
        this.atendimentos = atendimentos;
        this.funcionarios = funcionarios;
    }

    // Getters & Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(ArrayList<Medico> medicos) {
        this.medicos = medicos;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(ArrayList<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
