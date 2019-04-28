package br.com.api.restful.securitys.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class responseException implements Serializable {

	private static final long serialVersionUID = -2376172753753126433L;
	
	private String mensagem;

	public responseException(String message) {
		this.mensagem = message;
	}

	public String getMessage() {
		return mensagem;
	}

	public void setMessage(String mensagem) {
		this.mensagem = mensagem;
	}
}
