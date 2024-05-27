package br.contas.pagamento.service;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.contas.pagamento.client.ContaPagamentoBatchClient;
import br.contas.pagamento.dto.ContaDto;
import br.contas.pagamento.entity.Conta;
import br.contas.pagamento.exception.ContaPagamentoException;
import br.contas.pagamento.exception.RecursoNaoEncontrado;
import br.contas.pagamento.mapper.ContaMapper;
import br.contas.pagamento.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository repository;

	@Autowired
	ContaMapper mapper;

	@Value("${file.path}")
	String pathFiles;
	
	@Autowired
	ContaPagamentoBatchClient client;

	public ContaDto cadastrarConta(ContaDto contaDto) {
		return mapper.toDto(repository.save(mapper.toEntity(contaDto)));
	}

	public ContaDto atualizarConta(ContaDto contaDto, Long id) {
		Optional<Conta> conta = repository.findById(id);
		if (conta.isPresent()) {
			return mapper.toDto(repository.save(mapper.partialUpdate(contaDto, conta.get())));
		} else {
			throw new RecursoNaoEncontrado("Conta não localizada");
		}
	}

	public Page<ContaDto> buscarPorFiltro(Pageable pageable, LocalDate data, String situacao) {
		if (Objects.isNull(data) && Objects.isNull(situacao)) {
			return repository.findAll(pageable).map(mapper::toDto);
		}
		return repository.findByDataVencimentoSituacao(data, situacao, pageable).map(mapper::toDto);
	}

	public ContaDto buscarPorId(Long id) {
		return mapper
				.toDto(repository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Conta não localizada")));
	}

	public void salvarArquivo(MultipartFile file) {
		Path path = Path.of(pathFiles + file.getOriginalFilename());
		try {
			file.transferTo(path);
			client.processarArquivoPagamentos(file.getOriginalFilename());
		} catch (Exception e) {
			throw new ContaPagamentoException("Erro ao salvar arquivo em diretório");
		}

	}

}
