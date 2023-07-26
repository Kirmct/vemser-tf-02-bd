package model;

public class Paciente extends Pessoa {
    private String cpf;

    public Paciente(String nome, String cep, String cpf) {
        super(nome, cep);
        this.cpf = cpf;
    }

    // Getters & Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.getId());
        sb.append("\nPaciente: ").append(this.getNome());
        sb.append("\nCPF: ").append(this.getCpf());
        return sb.toString();
    }

}
