package br.com.api.restful.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserDTO {
	
	private UUID id;
	private String name;
	private String email;
	private String password;
	private Date lastLogin;
	private String token;
	private Date created;
	private Date modified;
	
	private List<PhoneDTO> phones;

	public UserDTO() {
	}

	
	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		this.modified = modified;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password
				+ ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getModified()=" + getModified() + "]";
	}


}
