package br.contas.pagamento.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.contas.pagamento.exception.ContaPagamentoException;

@Component
public class ContaPagamentoBatchClient {

    @Value("${batch.url}") 
    private String batchUrl;
    
    public WebClient getClient() {
		return WebClient.builder()
            .baseUrl(batchUrl)
            .build();
    }

	public String processarArquivoPagamentos(String fileName) {
        return getClient().post()
        	.uri("/api/processamento-contas/{fileName}", fileName)
        	.contentType(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::isError, response -> {
            		throw new ContaPagamentoException("Erro ao processar arquivo");
            })
            .bodyToMono(String.class)
            .block();
    }

}
