/**
 * 
 */
package br.com.api.restful.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;

/**
 * @author Halyson
 *
 */
@Entity
@Table(name="user")
public class User extends AbstractEntity{
	
	private String name;
	private String email;
	private String password;
	private Date lastLogin;
	private Date created;
	private Date modified;
	private String token;
	
	private List<Phone> phones;
	


	@Column(name="name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Email
	@Column(name="email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	public List<Phone> getPhones() {
		return phones;
	}
		
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	@Column(name="last_login", nullable = false)
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name="token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name = "dt_created", nullable = false)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "dt_modified", nullable = false)
	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	@PrePersist
	public void prePersist() {
		final Date newDate = new Date();
		this.created = newDate;
		this.modified = newDate;
		this.setLastLogin(new Date());
	}

	@PreUpdate
	public void preUpdate() {
		this.modified = new Date();
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", lastLogin=" + lastLogin
				+ ", token=" + token + ", phones=" + phones + "]";
	}


}
