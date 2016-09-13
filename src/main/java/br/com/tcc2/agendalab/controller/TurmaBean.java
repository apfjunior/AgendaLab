package br.com.tcc2.agendalab.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.CursoDAO;
import br.com.tcc2.agendalab.dao.TurmaDAO;
import br.com.tcc2.agendalab.model.Curso;
import br.com.tcc2.agendalab.model.Turma;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TurmaBean implements Serializable {
	
	private List<Curso> cursos;
	
	public List<Curso> getCursos() {
		return cursos;
	}
	
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
		
	private List<Turma> turmas;
	
	public List<Turma> getTurmas() {
		return turmas;
	}
	
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	private Turma turma;

	public Turma getTurma() {
		return turma;
	}

	public void novo() {

		turma = new Turma();
		
		CursoDAO cursoDAO = new CursoDAO();
		cursos = cursoDAO.listar();

	}

	public void salvar() {

		try {

			TurmaDAO turmaDAO = new TurmaDAO();
			turmaDAO.merge(turma);

			novo();
			turmas = turmaDAO.listar();

			Messages.addGlobalInfo("Turma salva com sucesso!");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar turma");
			erro.printStackTrace();
		}

	}
	
	@PostConstruct
	public void listar() {
		try {
			
			TurmaDAO turmaDAO = new TurmaDAO();
			turmas = turmaDAO.listar();
			
		} catch (RuntimeException erro) {
			
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as turmas");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			turma =  (Turma) evento.getComponent().getAttributes().get("selecionado");
		TurmaDAO turmaDAO = new TurmaDAO();
		turmaDAO.excluir(turma);
		turmas = turmaDAO.listar();
		
		Messages.addGlobalInfo("Turma removida com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a Turma");
		erro.printStackTrace();
	}
	}
	
	public void editar(ActionEvent evento){
		try{
			turma =  (Turma) evento.getComponent().getAttributes().get("selecionado");
		TurmaDAO turmaDAO = new TurmaDAO();
		turmaDAO.merge(turma);
		turmas = turmaDAO.listar();
		CursoDAO cursoDAO = new CursoDAO();
		cursos = cursoDAO.listar();
		
		
	} catch (RuntimeException erro) {
	
		erro.printStackTrace();
	}
	}



}
