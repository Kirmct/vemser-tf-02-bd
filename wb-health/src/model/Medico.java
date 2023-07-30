package model;

public class Medico extends Pessoa implements Pagamento{

    private Integer idPessoa;
    private Integer idHospital;
    private Integer idMedico;
    private String crm;

    public Medico() {
    }

    public Medico(String nome, String cep, String dataNascimento, String cpf, Double salarioMensal, Integer idHospital, String crm) {
        super(nome, cep, dataNascimento, cpf, salarioMensal);
        this.idHospital = idHospital;
        this.crm = crm;
    }

    // Getters & Setters
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.getIdMedico());
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

    @Override
    public Integer getIdPessoa() {
        return idPessoa;
    }

    @Override
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }
}
