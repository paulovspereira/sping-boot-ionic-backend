package com.pv.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pv.cursomc.domain.Categoria;;

//Utliliza camada service para fazer comunicação com camada de dominio

@Repository // Realizar operações acesso a dados
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
