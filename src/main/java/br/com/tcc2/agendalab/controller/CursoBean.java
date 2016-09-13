package br.com.tcc2.agendalab.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.CursoDAO;
import br.com.tcc2.agendalab.model.Curso;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CursoBean implements Serializable {
	private List<Curso> cursos;
	
	public List<Curso> getCursos() {
		return cursos;
	}
	
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	private Curso curso;

	public Curso getCurso() {
		return curso;
	}


	public void novo() {

		curso = new Curso();

	}

	public void salvar() {

		try {
			CursoDAO cursoDAO = new CursoDAO();
			cursoDAO.merge(curso);

			novo(); // instanciar

			Messages.addGlobalInfo("Curso salvo com sucesso!");
			
			//listagem dataTable
			cursos = cursoDAO.listar("nome");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Curso");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			CursoDAO cursoDAO = new CursoDAO();
			cursos = cursoDAO.listar();
			
		} catch (RuntimeException erro) {
			
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			curso =  (Curso) evento.getComponent().getAttributes().get("selecionado");
		CursoDAO cursoDAO = new CursoDAO();
		cursoDAO.excluir(curso);
		cursos = cursoDAO.listar();
		
		Messages.addGlobalInfo("Curso removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Curso");
		erro.printStackTrace();
	}
	}
	
	public void editar(ActionEvent evento){
		try{
		curso = (Curso) evento.getComponent().getAttributes().get("selecionado");
		
		
		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}
	}

}
