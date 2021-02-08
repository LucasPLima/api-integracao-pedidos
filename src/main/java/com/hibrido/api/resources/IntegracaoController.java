package com.hibrido.api.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hibrido.api.model.HistoricoResponse;
import com.hibrido.api.model.ResumoIntegracao;
import com.hibrido.api.service.HistoricoIntegracaoService;
import com.hibrido.api.service.IntegraPedidoService;

@RestController
@RequestMapping(path = "/integra-pedido")
public class IntegracaoController {
	
	private IntegraPedidoService integraPedidoService;
	private HistoricoIntegracaoService historicoService;
	
	public IntegracaoController(IntegraPedidoService integraPedidoService, HistoricoIntegracaoService historicoService) {
		this.integraPedidoService = integraPedidoService;
		this.historicoService = historicoService;
	}
	
	@GetMapping("/ping")
	@ResponseStatus(HttpStatus.OK)
	public void ping() {
		
	}
	
	@GetMapping
	public ResponseEntity<ResumoIntegracao> integraPedido(@RequestParam("dataInicio") 
	 												 @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataInicio,
	 												@RequestParam("dataFinal") 
	 												@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataFinal){
		ResumoIntegracao resumoIntegracao = integraPedidoService.executaIntegracao(dataInicio, dataFinal);
		return ResponseEntity.ok(resumoIntegracao);
		
	}
	
	@GetMapping("/historico")
	public ResponseEntity<List<HistoricoResponse>> getHistoricoIntegracao(){
		return ResponseEntity.ok(historicoService.getAll());
	}
	
   @GetMapping("/historico/{idIntegracao}")
   public ResponseEntity<HistoricoResponse> getHistoricoById(@PathVariable Integer idIntegracao){
	   HistoricoResponse historico = historicoService.getById(idIntegracao);
	   return historico.getId()==null? ResponseEntity.notFound().build(): ResponseEntity.ok(historico);
	}
	
}
