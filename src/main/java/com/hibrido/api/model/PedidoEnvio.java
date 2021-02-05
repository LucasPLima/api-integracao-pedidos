package com.hibrido.api.model;

import java.util.List;

public class PedidoEnvio {
	private String cpfCnpjCliente;
	private String cpfCpnjRepresentante;
	private Endereco enderecoEntrega;
	private String numero;
	
	private List<Produto> produtos;
	private Double valorDesconto;
	private Double valorFrete;
	private Double valorTotal;
	
	public PedidoEnvio() {
		// TODO Auto-generated constructor stub
	}
	
	public PedidoEnvio(String cpfCnpjCliente, String cpfCpnjRepresentante, Endereco enderecoEntrega, String numero,
			List<Produto> produtos, Double valorDesconto, Double valorFrete, Double valorTotal) {
		super();
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.cpfCpnjRepresentante = cpfCpnjRepresentante;
		this.enderecoEntrega = enderecoEntrega;
		this.numero = numero;
		this.produtos = produtos;
		this.valorDesconto = valorDesconto;
		this.valorFrete = valorFrete;
		this.valorTotal = valorTotal;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	public String getCpfCpnjRepresentante() {
		return cpfCpnjRepresentante;
	}
	public void setCpfCpnjRepresentante(String cpfCpnjRepresentante) {
		this.cpfCpnjRepresentante = cpfCpnjRepresentante;
	}
	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}
	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public Double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "PedidoEnvio [cpfCnpjCliente=" + cpfCnpjCliente + ", cpfCpnjRepresentante=" + cpfCpnjRepresentante
				+ ", enderecoEntrega=" + enderecoEntrega + ", numero=" + numero + ", produtos=" + produtos
				+ ", valorDesconto=" + valorDesconto + ", valorFrete=" + valorFrete + ", valorTotal=" + valorTotal
				+ "]";
	}
	
	
}
