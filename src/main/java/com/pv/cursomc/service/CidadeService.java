package com.pv.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.cursomc.domain.Cidade;
import com.pv.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstados(Integer estadoId) {
		return repo.findCidades(estadoId);
	}
}
