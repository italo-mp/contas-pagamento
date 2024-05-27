package br.contas.pagamento.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public class ContaPagamentoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5242874798350546618L;

    private final Integer errorCode;
    private final String errorMessage;

    public ContaPagamentoException(String errorMessage) {
        this(null, errorMessage);
    }

    public ContaPagamentoException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public ContaPagamentoException(String errorMessage,  Throwable cause) {
        super(errorMessage, cause);
		this.errorCode = null;
        this.errorMessage = errorMessage;
    }
}
