package com.hibrido.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hibrido.api.exception.PeriodoInvalidoException;
import com.hibrido.api.model.ErroIntegracaoDestino;
import com.hibrido.api.model.PedidoEnvio;
import com.hibrido.api.model.PedidoEnvioErro;
import com.hibrido.api.model.PedidoRecebido;
import com.hibrido.api.model.Produto;
import com.hibrido.api.model.ResumoIntegracao;

@Service
public class IntegraPedidoService {
	private final String URL_ORIGEM = "http://origem.demacode.com.br:8181/WS/Pedido";
	private final String URL_DESTINO = "http://destino.demacode.com.br:8282/v1/pedido";
	
	private final RestTemplate restTemplate;
	
	public IntegraPedidoService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
							
	}
	
	public ResumoIntegracao executaIntegracao(LocalDateTime dataInicio, LocalDateTime dataFinal) {
		validaPeriodoSolicitado(dataInicio, dataFinal);
		
		String urlOrigemCompleta = UriComponentsBuilder
								   .fromUriString(URL_ORIGEM)
								   .queryParam("data_inicial", dataInicio)
								   .queryParam("data_final", dataFinal)
								   .toUriString();
		
		PedidoRecebido[] respostaOrigem= restTemplate.getForObject(urlOrigemCompleta, PedidoRecebido[].class);
		List<PedidoRecebido> pedidosRecebidos = Arrays.asList(respostaOrigem);
		List<PedidoEnvio> pedidosEnvio = pedidosRecebidos.stream().map(pedidoOrigem -> transformaPedidoOrigem(pedidoOrigem))
																  .collect(Collectors.toList());
		
		ResumoIntegracao resumoIntegracao = enviarPedidos(pedidosEnvio);
		resumoIntegracao.setPedidosRecebidos(pedidosRecebidos);
		return resumoIntegracao;
				
	}
	
	private ResumoIntegracao enviarPedidos(List<PedidoEnvio> pedidosEnvio) {
		List<PedidoEnvio> pedidosEnviadosDestino = new ArrayList<PedidoEnvio>();
		List<PedidoEnvioErro> pedidosNaoEnviados = new ArrayList<PedidoEnvioErro>();;
		Integer quantidadePedidosEnviado = 0;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		for (PedidoEnvio pedido : pedidosEnvio) {
			try {
				HttpEntity<PedidoEnvio> pedidoEntity = new HttpEntity<>(pedido, headers);
				ResponseEntity<ErroIntegracaoDestino> response = this.restTemplate.postForEntity(URL_DESTINO, pedidoEntity, ErroIntegracaoDestino.class);
				if(response.getStatusCode() == HttpStatus.CREATED) {
					pedidosEnviadosDestino.add(pedido);
					quantidadePedidosEnviado++;
				}
			}catch (HttpClientErrorException ex) {
				try {
				  ObjectMapper objectMapper = new ObjectMapper();
				  String jsonResponse = ex.getResponseBodyAsString();
				  ErroIntegracaoDestino erroIntegracao = objectMapper.readValue(jsonResponse, ErroIntegracaoDestino.class);
				  pedidosNaoEnviados.add(new PedidoEnvioErro(erroIntegracao.getDescricao(), pedido));
				  
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		return new ResumoIntegracao(pedidosEnvio.size(), 
									quantidadePedidosEnviado, 
									pedidosEnviadosDestino, 
									pedidosNaoEnviados);
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
	
	private void validaPeriodoSolicitado(LocalDateTime dataInicio, LocalDateTime dataFinal) {
		if (dataInicio.isAfter(dataFinal)) {
			throw new PeriodoInvalidoException("Data de início é maior que a data final.");
		} else if (dataInicio.isAfter(LocalDateTime.now())) {
			throw new PeriodoInvalidoException("Data de início é maior que a data atual.");
		} else if (dataFinal.isAfter(LocalDateTime.now())) {
			throw new PeriodoInvalidoException("Data final é maior que a data atual.");
		}
	}
}
