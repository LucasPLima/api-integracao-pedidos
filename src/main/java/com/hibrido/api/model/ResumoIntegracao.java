package com.hibrido.api.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ResumoIntegracao {
	private LocalDate dtIntegracao;
	private LocalTime hrIntegracao;
	private Integer qtdePedidosRecebidos;
	private Integer qtdePedidosEnviados;
	private List<PedidoRecebido> pedidosRecebidos;
	private List<PedidoEnvio> pedidosEnviados;
	private List<PedidoEnvioErro> pedidosErro;
	
	public ResumoIntegracao() {
		// TODO Auto-generated constructor stub
	}
	
	public ResumoIntegracao(Integer qtdePedidosRecebidos, Integer qtdePedidosEnviados,
			List<PedidoEnvio> pedidosEnviados, List<PedidoEnvioErro> pedidosErro) {
		super();
		this.dtIntegracao = LocalDate.now();
		this.hrIntegracao = LocalTime.now();
		this.qtdePedidosRecebidos = qtdePedidosRecebidos;
		this.qtdePedidosEnviados = qtdePedidosEnviados;
		this.pedidosEnviados = pedidosEnviados;
		this.pedidosErro = pedidosErro;
	}


	public LocalDate getDtIntegracao() {
		return dtIntegracao;
	}
	public void setDtIntegracao(LocalDate dtIntegracao) {
		this.dtIntegracao = dtIntegracao;
	}
	public LocalTime getHrIntegracao() {
		return hrIntegracao;
	}
	public void setHrIntegracao(LocalTime hrIntegracao) {
		this.hrIntegracao = hrIntegracao;
	}
	public Integer getQtdePedidosRecebidos() {
		return qtdePedidosRecebidos;
	}
	public void setQtdePedidosRecebidos(Integer qtdePedidosRecebidos) {
		this.qtdePedidosRecebidos = qtdePedidosRecebidos;
	}
	public Integer getQtdePedidosEnviados() {
		return qtdePedidosEnviados;
	}
	public void setQtdePedidosEnviados(Integer qtdePedidosEnviados) {
		this.qtdePedidosEnviados = qtdePedidosEnviados;
	}
	public List<PedidoEnvio> getPedidosEnviados() {
		return pedidosEnviados;
	}
	public void setPedidosEnviados(List<PedidoEnvio> pedidosEnviados) {
		this.pedidosEnviados = pedidosEnviados;
	}
	public List<PedidoEnvioErro> getPedidosErro() {
		return pedidosErro;
	}
	public void setPedidosErro(List<PedidoEnvioErro> pedidosErro) {
		this.pedidosErro = pedidosErro;
	}

	public List<PedidoRecebido> getPedidosRecebidos() {
		return pedidosRecebidos;
	}

	public void setPedidosRecebidos(List<PedidoRecebido> pedidosRecebidos) {
		this.pedidosRecebidos = pedidosRecebidos;
	}
	
	
	
}
