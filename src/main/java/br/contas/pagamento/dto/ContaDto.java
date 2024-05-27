package br.contas.pagamento.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaDto {

	private Long id;

	@NotNull(message = "Data de Vencimento obrigatória.")
	private LocalDateTime dataVencimento;

	private LocalDateTime dataPagamento;

	@NotNull(message = "Valor obrigatório")
	private BigDecimal valor;

	private String descricao;

	private String situacao;

}