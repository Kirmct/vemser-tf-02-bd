package model;

import util.TipoDeAtendimento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Atendimento {
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private Integer idAtendimento;
    private Integer idHospital;
    private Integer idPaciente;
    private Integer idMedico;
    private LocalDate dataAtendimento;
    private String laudo;
    private Integer tipoDeAtendimento;

    private Double valorDoAtendimento;


    public Atendimento(){
    }

    public Atendimento(Integer idHospital, Integer idPaciente, Integer idMedico, String dataAtendimento, String laudo, Integer tipoDeAtendimento) {
        this.idHospital = idHospital;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.dataAtendimento = LocalDate.parse(dataAtendimento, fmt);
        this.laudo = laudo;
        setTipoDeAtendimento(tipoDeAtendimento);
        setValorDoAtendimento(tipoDeAtendimento);
    }

    public Double calcularValorDoAtendimento(Integer tipoDeAtendimento) {
        return switch (tipoDeAtendimento) {
            case 1 -> 5000.0;
            case 2 -> 300.0;
            case 3 -> 180.0;
            case 4 -> 100.0;
            case 5 -> 10.0;
            default -> 0.0;
        };
    }

    // Getters & Setters

    public Double getValorDoAtendimento() {
        return valorDoAtendimento;
    }

    public void setValorDoAtendimento(Integer valorAtendimento) {
        valorDoAtendimento = calcularValorDoAtendimento(valorAtendimento);
    }

    public TipoDeAtendimento getTipoDeAtendimento(){
        return TipoDeAtendimento.valueOf(tipoDeAtendimento);
    }

    public void setTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento){
        if(tipoDeAtendimento != null){
            this.tipoDeAtendimento = tipoDeAtendimento.getCodigo();
        }
    }

    public static DateTimeFormatter getFmt() {
        return fmt;
    }

    public static void setFmt(DateTimeFormatter fmt) {
        Atendimento.fmt = fmt;
    }

    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Integer idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public void setTipoDeAtendimento(Integer tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.idAtendimento);
        sb.append("\nData: ").append(this.dataAtendimento.format(fmt));
        sb.append("\nLaudo: ").append(this.laudo);
        sb.append("\nTipo de Atendimento: ").append(TipoDeAtendimento.valueOf(this.tipoDeAtendimento));
        sb.append("\nValor: R$").append(String.format("%.2f", this.valorDoAtendimento));
        return sb.toString();
    }
}
