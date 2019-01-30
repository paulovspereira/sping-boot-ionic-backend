package com.pv.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.cursomc.domain.Cliente;
import com.pv.cursomc.repositories.ClienteRepository;
import com.pv.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				      "Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
}
