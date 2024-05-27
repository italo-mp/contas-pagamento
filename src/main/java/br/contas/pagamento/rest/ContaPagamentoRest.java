package br.contas.pagamento.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.contas.pagamento.dto.ContaDto;
import br.contas.pagamento.exception.RecursoNaoEncontrado;
import br.contas.pagamento.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contas-pagamento")
public class ContaPagamentoRest  {
	
	@Autowired
	private ContaService service;
	
	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ContaDto> buscarPorId(@RequestParam(name = "id") Long id) throws RecursoNaoEncontrado{
		return ResponseEntity
				.ok()
				.body(service.buscarPorId(id));
	}
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ContaDto>> pesquisarPorFiltro(Pageable pageable, LocalDate data, String situacao) {
		return ResponseEntity.ok(service.buscarPorFiltro(pageable, data, situacao));
	}

	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaDto dto) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(service.cadastrarConta(dto));
	}
	
	@PutMapping(value = "/{id}" ,produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ContaDto> atualizar(@RequestParam(name = "id") Long id, @RequestBody @Valid ContaDto dto) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(service.atualizarConta(dto, id));
	}
	
	 @PostMapping(value = "/carregrar-csv", produces = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ContaDto> carregarArquivoCsv(@RequestParam(name = "file", required = false) MultipartFile file ) {
		service.salvarArquivo(file);
		return ResponseEntity
				.accepted().build();
	}

}
