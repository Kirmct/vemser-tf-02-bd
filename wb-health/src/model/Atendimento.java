package model;

import util.TipoDeAtendimento;

public class Atendimento {
    private static Integer contadorId = 0;
    private Integer id;
    private Paciente paciente;
    private Medico medico;
    private String data;
    private String laudo;
    private TipoDeAtendimento tipoDeAtendimento;
    private Double valor;

    public Atendimento(Paciente paciente, Medico medico, String data, String laudo, TipoDeAtendimento tipoDeAtendimento) {
        this.id = ++contadorId;;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.laudo = laudo;
        this.tipoDeAtendimento = tipoDeAtendimento;
        this.valor = calcularValorDoAtendimento(this.tipoDeAtendimento);
    }

    public Double calcularValorDoAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        return switch (tipoDeAtendimento) {
            case CIRURGIA -> 5000.0;
            case EXAME -> 300.0;
            case CONSULTA -> 180.0;
            case RETORNO -> 100.0;
            case TRIAGEM -> 10.0;
            default -> 0.0;
        };
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public TipoDeAtendimento getTipoDeAtendimento() {
        return tipoDeAtendimento;
    }

    public void setTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.id);
        sb.append("\nPaciente: ").append(this.paciente.getNome());
        sb.append("\nMédico: ").append(this.medico.getNome());
        sb.append("\nData: ").append(this.data);
        sb.append("\nLaudo: ").append(this.laudo);
        String strAtendimento = this.tipoDeAtendimento.toString();
        strAtendimento = strAtendimento.substring(0 , 1).toUpperCase() + strAtendimento.substring(1);
        sb.append("\nTipo de Atendimento: ").append(strAtendimento);
        sb.append("\nValor: R$").append(String.format("%.2f", this.valor));
        return sb.toString();
    }
}
