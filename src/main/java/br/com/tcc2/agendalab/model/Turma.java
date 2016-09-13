package br.com.tcc2.agendalab.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "turma")
public class Turma implements Serializable {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_turma", sequenceName = "turma_id_seq")
	@GeneratedValue(generator = "seq_turma")
	private Integer id;

	@Column(length = 5, nullable = false) // 32511
	private String codEstatico;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Curso curso;

	@Column(nullable = false)
	private Boolean ativo = true;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodEstatico() {
		return codEstatico;
	}

	public void setCodEstatico(String codEstatico) {
		this.codEstatico = codEstatico;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	

	@Override
	public String toString() {
		return "Turma [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}