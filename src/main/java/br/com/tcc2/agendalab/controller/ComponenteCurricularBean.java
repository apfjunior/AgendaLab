package br.com.tcc2.agendalab.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.ComponenteCurricularDAO;
import br.com.tcc2.agendalab.dao.TurmaDAO;
import br.com.tcc2.agendalab.model.ComponenteCurricular;
import br.com.tcc2.agendalab.model.Curso;
import br.com.tcc2.agendalab.model.Turma;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ComponenteCurricularBean implements Serializable {

	private ComponenteCurricular disciplina;
	private List<Curso> cursos;
	private List<Turma> turmas;
	private List<ComponenteCurricular> disciplinas;
	private Curso curso;
	private Turma turma;

	public List<Curso> getCursos() {
		return cursos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<ComponenteCurricular> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<ComponenteCurricular> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public ComponenteCurricular getDisciplina() {
		return disciplina;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setDisciplina(ComponenteCurricular disciplina) {
		this.disciplina = disciplina;
	}

	public void novo() {

		try {
			disciplina = new ComponenteCurricular();

			TurmaDAO turmaDAO = new TurmaDAO();
			turmas = turmaDAO.listar("codEstatico");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo componente curricular");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			ComponenteCurricularDAO compDAO = new ComponenteCurricularDAO();
			compDAO.merge(disciplina);

			novo(); // instanciar

			Messages.addGlobalInfo("disciplina salvo com sucesso!");

			// atualizar datatable
			ComponenteCurricularDAO disciplinaDAO = new ComponenteCurricularDAO();
			disciplinas = disciplinaDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a disciplina");
			erro.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {
		try {

			ComponenteCurricularDAO disciplinaDAO = new ComponenteCurricularDAO();
			disciplinas = disciplinaDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar listar os componentes curriculares");
			erro.printStackTrace();
		}
	}

	public void popular() {
		try {

			if (turma != null) {
				TurmaDAO turmaDAO = new TurmaDAO();
				turmas = turmaDAO.buscarPorCurso(curso.getId());

			} else {

				System.out.println("Turmas vazias");
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as turmas");
			erro.printStackTrace();
			System.out.println("Algum erro em popular o campo curso");
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			disciplina = (ComponenteCurricular) evento.getComponent().getAttributes().get("selecionado");
			ComponenteCurricularDAO compoDAO = new ComponenteCurricularDAO();
			compoDAO.excluir(disciplina);
			disciplinas = compoDAO.listar();
			Messages.addGlobalInfo("Disciplina removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a Disciplina");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {

			novo();
			disciplina = (ComponenteCurricular) evento.getComponent().getAttributes().get("selecionado");
			ComponenteCurricularDAO compoDAO = new ComponenteCurricularDAO();
			TurmaDAO turmaDAO = new TurmaDAO();
			turmas = turmaDAO.buscarPorCurso(curso.getId());

			compoDAO.merge(disciplina);
			disciplinas = compoDAO.listar();

		} catch (RuntimeException erro) {

			erro.printStackTrace();
		}
	}

}