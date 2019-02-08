package com.pv.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pv.cursomc.domain.enuns.TipoCliente;
import com.pv.cursomc.dto.ClienteNewDTO;
import com.pv.cursomc.resources.exception.FieldMessage;
import com.pv.cursomc.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	
	List<FieldMessage> list = new ArrayList<>();
	
	if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.cpfValidoCPF(objDto.getCpfOuCnpj())){
		list.add(new FieldMessage("cpf ou cnpj", "CPF inválido"));
    }
	
	if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.cnpjValidoCNPJ(objDto.getCpfOuCnpj())){
		list.add(new FieldMessage("cpf ou cnpj", "CNPJ é inválido"));
    } 
	
	for (FieldMessage e : list) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(e.getMessage())
		.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
	
	return list.isEmpty();
	
	}

}
