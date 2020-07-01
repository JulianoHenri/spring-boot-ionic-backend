package com.julianoHenrique.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.julianoHenrique.cursomc.domain.enums.TipoCliente;
import com.julianoHenrique.cursomc.dto.ClienteNewDTO;
import com.julianoHenrique.cursomc.resources.exception.FildMessage;
import com.julianoHenrique.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FildMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FildMessage("cpfOuCnpj", "CPF Invalido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FildMessage("cpfOuCnpj", "CNPJ Invalido"));
		}

		for (FildMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName())
			.addConstraintViolation();
		}
		return list.isEmpty();
	}
}