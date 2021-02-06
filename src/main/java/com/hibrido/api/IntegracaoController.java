package com.hibrido.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hibrido.api.model.PedidoEnvio;
import com.hibrido.api.service.IntegraPedidoService;

@RestController
@RequestMapping(path = "/integra-pedido")
public class IntegracaoController {
	
	private IntegraPedidoService integraPedidoService;
	
	public IntegracaoController(IntegraPedidoService integraPedidoService) {
		this.integraPedidoService = integraPedidoService;
	}
	
	@GetMapping("/ping")
	@ResponseStatus(HttpStatus.OK)
	public void ping() {
		
	}
	
	@GetMapping
	public ResponseEntity<List<PedidoEnvio>> integraPedido(@RequestParam("dataInicio") 
	 												 @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataInicio,
	 												@RequestParam("dataFinal") 
	 												@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataFinal){
		List<PedidoEnvio> pedidosEnviados = integraPedidoService.executaIntegracao(dataInicio, dataFinal);
		return ResponseEntity.ok(pedidosEnviados);
		
	}
	
}
