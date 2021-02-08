package com.hibrido.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hibrido.api.model.HistoricoResponse;
import com.hibrido.api.model.ResumoIntegracao;
import com.hibrido.api.model.entity.HistoricoIntegracaoEntity;
import com.hibrido.api.repository.HistoricoIntegracaoRepository;

@Service
public class HistoricoIntegracaoService {
	
	private HistoricoIntegracaoRepository historicoIntegracaoRepository;
	private final ObjectMapper objectMapper;
	
	public HistoricoIntegracaoService(HistoricoIntegracaoRepository historicoIntegracaoRepository) {
		this.historicoIntegracaoRepository = historicoIntegracaoRepository;
		this.objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.registerModule(new JavaTimeModule());
	}
	
	public void registrarSolicitacao(ResumoIntegracao resumoIntegracao) {
			
		if (historicoIntegracaoRepository.count()>4) {
			List<HistoricoIntegracaoEntity> resumos = historicoIntegracaoRepository.findAll();
			HistoricoIntegracaoEntity primeiroRegistro = resumos.get(0);
			historicoIntegracaoRepository.deleteById(primeiroRegistro.getId());
		}
		try {
			String resumoIntegracaoJson = objectMapper.writeValueAsString(resumoIntegracao);
			historicoIntegracaoRepository.save(new HistoricoIntegracaoEntity(resumoIntegracaoJson));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HistoricoResponse getById(Integer idHistorico) {
		Optional<HistoricoIntegracaoEntity> historico = historicoIntegracaoRepository.findById(idHistorico);
		HistoricoIntegracaoEntity historicoIntegracao = historico.isEmpty()? null: historico.get();
		ResumoIntegracao resumo = new ResumoIntegracao();
		HistoricoResponse historicoResponse = new HistoricoResponse();
		
		if (historicoIntegracao != null) {
			resumo = converteJsonParaResumoIntegracao(historicoIntegracao.getResumoIntegracaoJson());
			historicoResponse.setId(historicoIntegracao.getId());
			historicoResponse.setDetalheResumo(resumo);
		}
			
		return historicoResponse;
	}
	
	public List<HistoricoResponse> getAll(){
		List<HistoricoIntegracaoEntity> historicos = historicoIntegracaoRepository.findAll();
		List<HistoricoResponse> historicosResponse = historicos.stream().map(historico -> new HistoricoResponse(historico.getId(), 
																												converteJsonParaResumoIntegracao(historico.getResumoIntegracaoJson())))
																		.collect(Collectors.toList());
		return historicosResponse;
	}
	
	private ResumoIntegracao converteJsonParaResumoIntegracao(String resumoIntegracaoJson) {
		//ObjectMapper objectMapper = new ObjectMapper();
		ResumoIntegracao resumoObjeto = new ResumoIntegracao();
		try {
			resumoObjeto = objectMapper.readValue(resumoIntegracaoJson, ResumoIntegracao.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resumoObjeto;
	}
}
