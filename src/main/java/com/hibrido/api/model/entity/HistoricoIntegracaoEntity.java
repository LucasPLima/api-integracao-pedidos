package com.hibrido.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_historico_integracao")
public class HistoricoIntegracaoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Lob
	@Column
	private String resumoIntegracaoJson;
	
	public HistoricoIntegracaoEntity() {
		
	}
	
	public HistoricoIntegracaoEntity(String resumoIntegracao) {
		super();
		this.resumoIntegracaoJson = resumoIntegracao;
	} 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResumoIntegracaoJson() {
		return resumoIntegracaoJson;
	}

	public void setResumoIntegracaoJson(String resumoIntegracaoJson) {
		this.resumoIntegracaoJson = resumoIntegracaoJson;
	}
	
	
}
