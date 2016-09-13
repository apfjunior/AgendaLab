package br.com.tcc2.agendalab.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("DOCENTE")
public class Docente extends Pessoa {

	@Column // essa coluna nao é obrigatória
	private String areaEsp;

	public String getAreaEsp() {
		return areaEsp;
	}

	public void setAreaEsp(String areaEsp) {
		this.areaEsp = areaEsp;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + Objects.hashCode(this.areaEsp);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Docente other = (Docente) obj;
		if (!Objects.equals(this.areaEsp, other.areaEsp)) {
			return false;
		}
		return true;
	}

}
