package com.hibrido.api.model;

public class Item {
	private String codigo;
	private Double preco;
	private Double quantidade;
	private Double valorTotal;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(String codigo, Double preco, Double quantidade, Double valorTotal) {
		super();
		this.codigo = codigo;
		this.preco = preco;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
	}



	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
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
		return "Item [codigo=" + codigo + ", preco=" + preco + ", quantidade=" + quantidade + ", valorTotal="
				+ valorTotal + "]";
	}
	
	
	
}
