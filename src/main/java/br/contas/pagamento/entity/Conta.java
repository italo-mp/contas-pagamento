package br.contas.pagamento.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "CONTA")
public class Conta {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SQ_CATEGORIA")
    private Long id;

    @NotNull
    @Column(name = "DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @NotNull
    @Column(name = "DATA_PAGAMENTO")
    private LocalDate dataPagamento;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "SITUACAO")
    private String situacao;


}