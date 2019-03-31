package br.com.api.restful.controllers.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import br.com.api.restful.entities.User;
import br.com.api.restful.responses.Response;

public class AbstractControllerImpl {

	public ResponseEntity<Response<User>> isBadRequest(Response<User> response, BindingResult result){
		result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(response);
	}
	
	public ResponseEntity<Response<User>> addResponseMessageError(String message, Response<User> response, HttpStatus status) {
		List<String> errors = new ArrayList<String>();
		errors.add(message);
		response.setErros(errors);
		return ResponseEntity.status(status).body(response);
	}
}
