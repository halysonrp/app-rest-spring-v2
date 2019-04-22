package br.com.api.restful.securitys.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = -2376172753753126433L;
	
	private String message;

	public ExceptionResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
