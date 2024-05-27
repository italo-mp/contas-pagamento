package br.contas.pagamento.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.contas.pagamento.entity.Conta;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	@Query(" SELECT c FROM Conta c WHERE (:dataVencimento is null or c.dataVencimento = :dataVencimento) "
			+ "	and (:situacao is null or c.situacao = :situacao)")
	Page<Conta> findByDataVencimentoSituacao(@Param("dataVencimento") LocalDate dataVencimento, @Param("situacao") String situacao, Pageable pageable);
}
