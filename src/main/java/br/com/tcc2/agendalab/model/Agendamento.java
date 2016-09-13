package br.com.tcc2.agendalab.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "agendamento")
public class Agendamento implements Serializable {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_agendamento", sequenceName = "agendamento_id_seq")
	@GeneratedValue(generator = "seq_agendamento")
	private Integer id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private EnumClass statusConclusaoo = EnumClass.INICIAL; // ja comeca inicial

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicioDataHoraAgendamento;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fimDataHoraAgendamento;

	@Column(length = 140, nullable = false)
	private String atividadeProgramadas;

	@ManyToOne
	@JoinColumn(nullable = false)
	private ComponenteCurricular compoCurricular;

	@Column(length = 140, nullable = false)
	private String objetivoGeral;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Laboratorio laboratorio;

	@Column(length = 14, nullable = false)
	private String tipo;

	@Column(length = 2)
	private String quantAlunos;

	@Column(nullable = false)
	private String materialAula;

	@Column(nullable = false)
	private Boolean ativo = true; // já comeca marcado

	@Column(length = 140)
	private String observacoes;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeRealizacao = new Date();

	// novo
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeConfirmacao;

	@ManyToOne
	@JoinColumn
	private Tecnico tecnico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EnumClass getStatusConclusaoo() {
		return statusConclusaoo;
	}

	public void setStatusConclusaoo(EnumClass statusConclusaoo) {
		this.statusConclusaoo = statusConclusaoo;
	}

	public Date getInicioDataHoraAgendamento() {
		return inicioDataHoraAgendamento;
	}

	public void setInicioDataHoraAgendamento(Date inicioDataHoraAgendamento) {
		this.inicioDataHoraAgendamento = inicioDataHoraAgendamento;
	}

	public Date getFimDataHoraAgendamento() {
		return fimDataHoraAgendamento;
	}

	public void setFimDataHoraAgendamento(Date fimDataHoraAgendamento) {
		this.fimDataHoraAgendamento = fimDataHoraAgendamento;
	}

	public String getAtividadeProgramadas() {
		return atividadeProgramadas;
	}

	public void setAtividadeProgramadas(String atividadeProgramadas) {
		this.atividadeProgramadas = atividadeProgramadas;
	}

	public ComponenteCurricular getCompoCurricular() {
		return compoCurricular;
	}

	public void setCompoCurricular(ComponenteCurricular compoCurricular) {
		this.compoCurricular = compoCurricular;
	}

	public String getObjetivoGeral() {
		return objetivoGeral;
	}

	public void setObjetivoGeral(String objetivoGeral) {
		this.objetivoGeral = objetivoGeral;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getQuantAlunos() {
		return quantAlunos;
	}

	public void setQuantAlunos(String quantAlunos) {
		this.quantAlunos = quantAlunos;
	}

	public String getMaterialAula() {
		return materialAula;
	}

	public void setMaterialAula(String materialAula) {
		this.materialAula = materialAula;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Date getDataDeRealizacao() {
		return dataDeRealizacao;
	}

	public void setDataDeRealizacao(Date dataDeRealizacao) {
		this.dataDeRealizacao = dataDeRealizacao;
	}

	public Date getDataDeConfirmacao() {
		return dataDeConfirmacao;
	}

	public void setDataDeConfirmacao(Date dataDeConfirmacao) {
		this.dataDeConfirmacao = dataDeConfirmacao;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atividadeProgramadas == null) ? 0 : atividadeProgramadas.hashCode());
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((compoCurricular == null) ? 0 : compoCurricular.hashCode());
		result = prime * result + ((dataDeRealizacao == null) ? 0 : dataDeRealizacao.hashCode());
		result = prime * result + ((fimDataHoraAgendamento == null) ? 0 : fimDataHoraAgendamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inicioDataHoraAgendamento == null) ? 0 : inicioDataHoraAgendamento.hashCode());
		result = prime * result + ((laboratorio == null) ? 0 : laboratorio.hashCode());
		result = prime * result + ((materialAula == null) ? 0 : materialAula.hashCode());
		result = prime * result + ((objetivoGeral == null) ? 0 : objetivoGeral.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((quantAlunos == null) ? 0 : quantAlunos.hashCode());
		result = prime * result + ((statusConclusaoo == null) ? 0 : statusConclusaoo.hashCode());
		result = prime * result + ((tecnico == null) ? 0 : tecnico.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Agendamento other = (Agendamento) obj;
		if (atividadeProgramadas == null) {
			if (other.atividadeProgramadas != null)
				return false;
		} else if (!atividadeProgramadas.equals(other.atividadeProgramadas))
			return false;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (compoCurricular == null) {
			if (other.compoCurricular != null)
				return false;
		} else if (!compoCurricular.equals(other.compoCurricular))
			return false;
		if (dataDeRealizacao == null) {
			if (other.dataDeRealizacao != null)
				return false;
		} else if (!dataDeRealizacao.equals(other.dataDeRealizacao))
			return false;
		if (fimDataHoraAgendamento == null) {
			if (other.fimDataHoraAgendamento != null)
				return false;
		} else if (!fimDataHoraAgendamento.equals(other.fimDataHoraAgendamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inicioDataHoraAgendamento == null) {
			if (other.inicioDataHoraAgendamento != null)
				return false;
		} else if (!inicioDataHoraAgendamento.equals(other.inicioDataHoraAgendamento))
			return false;
		if (laboratorio == null) {
			if (other.laboratorio != null)
				return false;
		} else if (!laboratorio.equals(other.laboratorio))
			return false;
		if (materialAula == null) {
			if (other.materialAula != null)
				return false;
		} else if (!materialAula.equals(other.materialAula))
			return false;
		if (objetivoGeral == null) {
			if (other.objetivoGeral != null)
				return false;
		} else if (!objetivoGeral.equals(other.objetivoGeral))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (quantAlunos == null) {
			if (other.quantAlunos != null)
				return false;
		} else if (!quantAlunos.equals(other.quantAlunos))
			return false;
		if (statusConclusaoo != other.statusConclusaoo)
			return false;
		if (tecnico == null) {
			if (other.tecnico != null)
				return false;
		} else if (!tecnico.equals(other.tecnico))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
