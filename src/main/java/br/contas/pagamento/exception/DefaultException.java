package br.contas.pagamento.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
abstract class DefaultException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2552526973185332393L;

    private final Integer errorCode;
    private final String errorMessage;

    protected DefaultException(String errorMessage) {
        this(null, errorMessage);
    }

    protected DefaultException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
