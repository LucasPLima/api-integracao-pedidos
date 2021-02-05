package com.hibrido.api.model;


public class Pessoa {
	private String cpfCnpj;
	private String email;
	private Endereco endereco;
	
	private String nome;
	private String telefone;
	
	public Pessoa() {
		// TODO Auto-generated constructor stub
	}
	
	public Pessoa(String cpfCnpj, String email, Endereco endereco, String nome, String telefone) {
		super();
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.endereco = endereco;
		this.nome = nome;
		this.telefone = telefone;
	}



	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Pessoa [cpfCnpj=" + cpfCnpj + ", email=" + email + ", endereco=" + endereco + ", nome=" + nome
				+ ", telefone=" + telefone + "]";
	}
	
	
}
