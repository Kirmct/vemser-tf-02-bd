package model;

public class Medico extends Pessoa implements Pagamento{
    private String crm;
    private Double salarioMensal;

    public Medico(String nome, String cep, String crm, Double salarioMensal) {
        super(nome, cep);
        this.crm = crm;
        this.salarioMensal = salarioMensal;
    }

    // Getters & Setters
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Double getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public void setSalarioMensal(Double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.getId());
        sb.append("\nMédico: ").append(this.getNome());
        sb.append("\nCRM: ").append(this.getCrm());
        sb.append("\nSalário Mensal: R$").append(String.format("%.2f", this.getSalarioMensal()));
        return sb.toString();
    }

    @Override
    public Double calcularSalarioMensal() {
        Double taxaInss = 0.14;
        return getSalarioMensal() - getSalarioMensal() * taxaInss;
    }

    @Override
    public Double calcularSalarioAnual() {
        return calcularSalarioMensal() * 12;
    }
}
