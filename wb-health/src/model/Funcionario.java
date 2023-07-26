package model;

public class Funcionario extends Pessoa implements Pagamento{
    private String cpf;
    private Double salarioMensal;
    public Funcionario(String nome, String cep, String cpf, Double salarioMensal) {
        super(nome, cep);
        this.cpf = cpf;
        this.salarioMensal = salarioMensal;
    }

    // Getters & Setters
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.getId());
        sb.append("\nFuncionário: ").append(this.getNome());
        sb.append("\nCPF: ").append(this.getCpf());
        sb.append("\nSalário Mensal: R$").append(String.format("%.2f", this.getSalarioMensal()));
        return sb.toString();
    }

    @Override
    public Double calcularSalarioMensal() {
        Double taxaInss = 0.075;
        if (getSalarioMensal() > 1320.0 && getSalarioMensal() <= 2571.29) {
            taxaInss = 0.09;
        }
        else if (getSalarioMensal() > 2571.29 && getSalarioMensal() <= 3856.94) {
            taxaInss = 0.12;
        }
        else if (getSalarioMensal() > 3856.94 && getSalarioMensal() <= 7507.49) {
            taxaInss = 0.14;
        }

        return getSalarioMensal() - getSalarioMensal() * taxaInss;
    }

    @Override
    public Double calcularSalarioAnual() {
        return calcularSalarioMensal() * 12;
    }

}
