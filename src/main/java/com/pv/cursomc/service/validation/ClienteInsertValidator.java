package com.pv.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pv.cursomc.domain.Cliente;
import com.pv.cursomc.domain.enuns.TipoCliente;
import com.pv.cursomc.dto.ClienteNewDTO;
import com.pv.cursomc.repositories.ClienteRepository;
import com.pv.cursomc.resources.exception.FieldMessage;
import com.pv.cursomc.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	
	List<FieldMessage> list = new ArrayList<>();
	
	if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.cpfValidCPF(objDto.getCpfOuCnpj())){
		list.add(new FieldMessage("cpf ou cnpj", "CPF inválido"));
    }
	
	if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.cnpjValidCNPJ(objDto.getCpfOuCnpj())){
		list.add(new FieldMessage("cpf ou cnpj", "CNPJ é inválido"));
    } 
	
	Cliente aux = repo.findByEmail(objDto.getEmail());
	if(aux !=null) {
		list.add(new FieldMessage("email", "Email já existente"));
	}
	
	for (FieldMessage e : list) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(e.getMessage())
		.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
	
	return list.isEmpty();
	
	}

}
