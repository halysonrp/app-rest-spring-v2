package br.com.api.restful.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Halyson
 *
 */

@Entity
@Table(name = "phone")
public class Phone extends AbstractEntity{

	private String number;
	private String ddd;
	private User user;

	public Phone() {
	}

	@Column(name = "number", nullable = false)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "ddd", nullable = false)
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
