package com.hibrido.api.model;


public class Endereco {
	private String bairro;
	private String cep;
	private String cidade;
	private String codigoIbge;
	private String complemento;
	private Double latitude;
	private Double longitude;
	private String numero;
	private String pais;
	private String rua;
	private String uf;
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}
	
	public Endereco(String bairro, String cep, String cidade, String codigoIbge, String complemento, Double latitude,
			Double longitude, String numero, String pais, String rua, String uf) {
		super();
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.codigoIbge = codigoIbge;
		this.complemento = complemento;
		this.latitude = latitude;
		this.longitude = longitude;
		this.numero = numero;
		this.pais = pais;
		this.rua = rua;
		this.uf = uf;
	}



	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCodigoIbge() {
		return codigoIbge;
	}
	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	

	@Override
	public String toString() {
		return "Endereco [bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", codigoIbge=" + codigoIbge
				+ ", complemento=" + complemento + ", latitude=" + latitude + ", longitude=" + longitude + ", numero="
				+ numero + ", pais=" + pais + ", rua=" + rua + ", uf=" + uf + "]";
	}
	
	
}
