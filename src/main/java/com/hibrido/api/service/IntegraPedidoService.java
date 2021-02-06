package com.hibrido.api.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hibrido.api.model.PedidoEnvio;
import com.hibrido.api.model.PedidoRecebido;
import com.hibrido.api.model.Produto;

@Service
public class IntegraPedidoService {
	private final String URL_ORIGEM = "http://origem.demacode.com.br:8181/WS/Pedido";
	private final String URL_DESTINO = "http://destino.demacode.com.br:8282/v1/pedido";
	
	private final RestTemplate restTemplate;
	
	public IntegraPedidoService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public List<PedidoEnvio> executaIntegracao(LocalDateTime dataInicio, LocalDateTime dataFinal) {
		String urlOrigemCompleta = UriComponentsBuilder
								   .fromUriString(URL_ORIGEM)
								   .queryParam("data_inicial", dataInicio)
								   .queryParam("data_final", dataFinal)
								   .toUriString();
		
		PedidoRecebido[] respostaOrigem= restTemplate.getForObject(urlOrigemCompleta, PedidoRecebido[].class);
		List<PedidoRecebido> pedidosRecebidos = Arrays.asList(respostaOrigem);
		List<PedidoEnvio> pedidosEnvio = pedidosRecebidos.stream().map(pedidoOrigem -> transformaPedidoOrigem(pedidoOrigem))
																  .collect(Collectors.toList());
		
		enviarPedidos(pedidosEnvio);
		return pedidosEnvio;
				
	}
	
	private void enviarPedidos(List<PedidoEnvio> pedidosEnvio) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		for (PedidoEnvio pedido : pedidosEnvio) {
			HttpEntity<PedidoEnvio> pedidoEntity = new HttpEntity<>(pedido, headers);
			System.out.println(pedidoEntity.getBody().toString());
			ResponseEntity<Void> response = this.restTemplate.postForEntity(URL_DESTINO, pedidoEntity, Void.class);
			if (response.getStatusCode() == HttpStatus.CREATED) {
				System.out.printf("O pedido %s deu bom.", pedido.getNumero());
			}
		}
	}
	
	private PedidoEnvio transformaPedidoOrigem(PedidoRecebido pedidoRecebido) {
		List<Produto> produtosSolicitados = pedidoRecebido.getItens().stream().map(item -> new Produto(item.getCodigo(),
																									   item.getPreco(),
																									   item.getQuantidade(),
																									   item.getValorTotal()))
																			  .collect(Collectors.toList());
		
		Double valorTotalItems = pedidoRecebido.getItens().stream().map(item-> item.getValorTotal())
				                                                    .collect(Collectors.summingDouble(Double::doubleValue));
		
		Double valorTotalPedido = valorTotalItems + pedidoRecebido.getValorFrete() - pedidoRecebido.getValorDesconto();
		
		PedidoEnvio pedidoEnvio = new PedidoEnvio(pedidoRecebido.getCliente().getCpfCnpj(),
												  pedidoRecebido.getRepresentante().getCpfCnpj(),
												  pedidoRecebido.getCliente().getEndereco(),
												  pedidoRecebido.getNumero(),
												  produtosSolicitados,
												  pedidoRecebido.getValorDesconto(),
												  pedidoRecebido.getValorFrete(),
												  valorTotalPedido);

		return pedidoEnvio;
		
	}
}
