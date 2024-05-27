package br.contas.pagamento.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.contas.pagamento.exception.ContaPagamentoException;
import br.contas.pagamento.exception.DetalheErro;
import br.contas.pagamento.exception.ParametroInvalido;
import br.contas.pagamento.exception.RecursoNaoEncontrado;

@RestControllerAdvice
public class ContaPagamentoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DetalheErro handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        final var validacoes = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> new DetalheErro.Validacao(err.getField(), err.getDefaultMessage()))
            .toList();
        return DetalheErro.builder()
            .message("Parâmetros inválidos")
            .validacoes(validacoes)
            .build();
    }

    @ExceptionHandler(ParametroInvalido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DetalheErro handleParametroInvalido(ParametroInvalido e) {
        return DetalheErro.builder()
            .status(e.getErrorCode())
            .message(e.getErrorMessage())
            .build();
    }

    @ExceptionHandler(RecursoNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DetalheErro handleRecursoNaoEncontrado(RecursoNaoEncontrado e) {
        return DetalheErro.builder()
            .status(e.getErrorCode())
            .message(e.getErrorMessage())
            .build();
    }
    
    @ExceptionHandler(ContaPagamentoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DetalheErro handleDefaultException(ContaPagamentoException e){
        return DetalheErro.builder()
        	.status(e.getErrorCode())
            .message(e.getMessage())
            .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DetalheErro handleDefaultException(Exception e){
        return DetalheErro.builder()
            .message("Erro inesperado")
            .build();
    }
}
