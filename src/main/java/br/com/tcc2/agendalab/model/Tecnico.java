package br.com.tcc2.agendalab.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "pessoa")
@DiscriminatorValue("TECNICO")
public class Tecnico extends Pessoa {

}
