package br.com.api.restful.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Halyson
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {

	private UUID id;
	private Date created;
	private Date modified;
	private List<String> mensagem = new ArrayList<String>();


	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
	
	@Transient
	public List<String> getMensagem() {
		return mensagem;
	}

	public void setMensagem(List<String> mensagem) {
		this.mensagem = mensagem;
	}


	@PrePersist
	public void prePersist() {
		final Date newDate = new Date();
		this.created = newDate;
		this.modified = newDate;
	}

	@PreUpdate
	public void preUpdate() {
		this.modified = new Date();
	}
	

}
