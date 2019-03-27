package br.com.api.restful.dtos;

import java.util.UUID;

import javax.validation.constraints.Email;

public class LoginDto {

	private String email;
	private UUID password;
	
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public UUID getPassword() {
		return password;
	}
	public void setPassword(UUID password) {
		this.password = password;
	}
	
	
	
}
