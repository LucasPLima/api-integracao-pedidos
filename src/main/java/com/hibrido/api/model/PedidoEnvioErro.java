package com.hibrido.api.model;

public class PedidoEnvioErro {
	private String descricaoErro;
	private PedidoEnvio detalhePedido;
	
	public PedidoEnvioErro() {
		// TODO Auto-generated constructor stub
	}
	
	public PedidoEnvioErro(String descricaoErro, PedidoEnvio detalhePedido) {
		super();
		this.descricaoErro = descricaoErro;
		this.detalhePedido = detalhePedido;
	}
	
	public String getDescricaoErro() {
		return descricaoErro;
	}
	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}
	public PedidoEnvio getDetalhePedido() {
		return detalhePedido;
	}
	public void setDetalhePedido(PedidoEnvio detalhePedido) {
		this.detalhePedido = detalhePedido;
	}
	
	
}
