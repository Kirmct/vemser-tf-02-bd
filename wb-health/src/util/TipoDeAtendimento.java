package util;

public enum TipoDeAtendimento {
    CONSULTA(1), CIRURGIA(2), EXAME(3), RETORNO(4), TRIAGEM(5);

    private int codigo;

    TipoDeAtendimento(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoDeAtendimento valueOf(int code){
        for (TipoDeAtendimento valor:TipoDeAtendimento.values()) {
            if(valor.getCodigo() == code){
                return valor;
            }
        }
        throw new IllegalArgumentException("Código inválido");
    }
}
