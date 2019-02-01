package com.pv.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.cursomc.domain.Pedido;
import com.pv.cursomc.repositories.PedidoRepository;
import com.pv.cursomc.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				      "Objeto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}
}
