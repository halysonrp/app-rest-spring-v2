package br.com.api.restful.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginDTO {

	private String email;
	private String password;
	private String nome;
	
	@Email
	@NotEmpty(message = "Usuário e/ou senha inválidos")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotEmpty(message = "Usuário e/ou senha inválidos")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
