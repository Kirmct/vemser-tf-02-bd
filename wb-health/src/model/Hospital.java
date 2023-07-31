package model;

public class Hospital {
    private Integer idHospital;
    private String nome;

    public Hospital() {
    }
    public Hospital(Integer idHospital, String nome) {
        this.idHospital = idHospital;
        this.nome = nome;
    }

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
