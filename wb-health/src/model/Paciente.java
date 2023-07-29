package model;

public class Paciente extends Pessoa {
    private Integer idPaciente;
    private Integer idHospital;
    private Integer idPessoa;

    public Paciente(String nome, String cep, String dataNacimento,
                    String cpf, Double salarioMensal, Integer idHospital) {
        super(nome, cep, dataNacimento, cpf, salarioMensal);
        this.idHospital = idHospital;
    }

    // Getters & Setters
    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    @Override
    public Integer getIdPessoa() {
        return idPessoa;
    }

    @Override
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.getIdPaciente());
        sb.append("\nPaciente: ").append(this.getNome());
        sb.append("\nCPF: ").append(this.getCpf());
        return sb.toString();
    }

}
