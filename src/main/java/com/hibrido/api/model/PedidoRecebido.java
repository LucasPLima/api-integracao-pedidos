package com.hibrido.api.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PedidoRecebido {
	private String numero;
	private LocalDate data;
	private LocalTime hora;
	private Double valorFrete;
	private Double valorDesconto;
	
	private Pessoa representante;
	private Pessoa cliente;
	
	private List<Item> itens;
	
	public PedidoRecebido() {
		// TODO Auto-generated constructor stub
	}
	
	public PedidoRecebido(String numero, LocalDate data, LocalTime hora, Double valorFrete, Double valorDesconto,
			Pessoa representante, Pessoa cliente, List<Item> itens) {
		super();
		this.numero = numero;
		this.data = data;
		this.hora = hora;
		this.valorFrete = valorFrete;
		this.valorDesconto = valorDesconto;
		this.representante = representante;
		this.cliente = cliente;
		this.itens = itens;
	}



	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Pessoa getRepresentante() {
		return representante;
	}

	public void setRepresentante(Pessoa representante) {
		this.representante = representante;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "PedidoRecebido [numero=" + numero + ", data=" + data + ", hora=" + hora + ", valorFrete=" + valorFrete
				+ ", valorDesconto=" + valorDesconto + ", representante=" + representante + ", cliente=" + cliente
				+ ", itens=" + itens + "]";
	}
	
	
	
}
