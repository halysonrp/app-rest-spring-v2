package br.com.api.restful.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Halyson
 *
 */

@Entity
@Table(name="phone")
public class Phone extends AbstractEntity{

	private String number;
	private String ddd;
	
	public Phone() {
	}

	@Column(name="number", nullable = false)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Column(name="ddd", nullable = false)
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

}
