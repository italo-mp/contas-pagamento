package br.contas.pagamento.exception;

import java.io.Serial;

public class RecursoNaoEncontrado extends DefaultException {

    @Serial
    private static final long serialVersionUID = -7516712528477440993L;

    public RecursoNaoEncontrado(Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public RecursoNaoEncontrado(String errorMessage) {
        super(errorMessage);
    }
}
