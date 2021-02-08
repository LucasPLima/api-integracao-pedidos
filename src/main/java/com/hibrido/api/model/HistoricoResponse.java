package com.hibrido.api.model;

public class HistoricoResponse {
	private Integer id;
	private ResumoIntegracao detalheResumo;
	
	public HistoricoResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public HistoricoResponse(Integer id, ResumoIntegracao detalheResumo) {
		super();
		this.id = id;
		this.detalheResumo = detalheResumo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ResumoIntegracao getDetalheResumo() {
		return detalheResumo;
	}
	public void setDetalheResumo(ResumoIntegracao detalheResumo) {
		this.detalheResumo = detalheResumo;
	}
	
	
}
