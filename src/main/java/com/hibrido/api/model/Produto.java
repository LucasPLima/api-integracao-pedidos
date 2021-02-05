package com.hibrido.api.model;

public class Produto {
	private String sku;
	private Double precoUnitario;
	private Double quantidade;
	private Double valorTotal;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}
	
	public Produto(String sku, Double precoUnitario, Double quantidade, Double valorTotal) {
		super();
		this.sku = sku;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
	}

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "Produto [sku=" + sku + ", precoUnitario=" + precoUnitario + ", quantidade=" + quantidade
				+ ", valorTotal=" + valorTotal + "]";
	}
	
	
	
}
