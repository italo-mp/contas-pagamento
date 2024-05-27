package br.contas.pagamento.exception;

import java.io.Serial;

public class ParametroInvalido extends DefaultException {

    @Serial
    private static final long serialVersionUID = 2255677372935833769L;

    public ParametroInvalido(String errorMessage) {
        super(errorMessage);
    }

    public ParametroInvalido(Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
