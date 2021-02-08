package com.hibrido.api.scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hibrido.api.model.ResumoIntegracao;
import com.hibrido.api.service.IntegraPedidoService;

@Service
public class IntegracaoPeriodica {
	private IntegraPedidoService integraPedidoService;
	
	public IntegracaoPeriodica(IntegraPedidoService integraPedidoService) {
		this.integraPedidoService = integraPedidoService;
	}
	
	@Scheduled(fixedDelay = 1000 * 60 * 5)
	public void scheduleIntegracaoPedido() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	    String dataInicio = LocalDateTime.now().minusMinutes(5).format(formatter);
	    String dataFinal  = LocalDateTime.now().format(formatter);
	    
	    @SuppressWarnings("unused")
		ResumoIntegracao resumo = integraPedidoService.executaIntegracao(LocalDateTime.parse(dataInicio), 
	    																 LocalDateTime.parse(dataFinal));
	}
}
