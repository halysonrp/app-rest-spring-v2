package br.com.api.restful.dtos;

/**
 * @author Halyson
 *
 */

public class PhoneDTO{

	private String number;
	private String ddd;
	private UserDTO user;

	public PhoneDTO() {
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
