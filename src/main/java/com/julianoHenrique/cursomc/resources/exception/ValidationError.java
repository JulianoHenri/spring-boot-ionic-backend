package com.julianoHenrique.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FildMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStampo) {
		super(status, msg, timeStampo);
	}

	public List<FildMessage> getErrors() {
		return errors;
	}

	public void addError(String fildName, String message) {
		errors.add(new FildMessage(fildName, message));
	}

	

}
