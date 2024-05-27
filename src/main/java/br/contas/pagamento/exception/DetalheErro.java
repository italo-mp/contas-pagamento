package br.contas.pagamento.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record DetalheErro(
	Integer status,
	String message,
	List<Validacao> validacoes,
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime dateTime
) {

	public DetalheErro {
		if (dateTime == null) dateTime = LocalDateTime.now();
	}

	public record Validacao(String campo, String detalhe) {
	}
}
