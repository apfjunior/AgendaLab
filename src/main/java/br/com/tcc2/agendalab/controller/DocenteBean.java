package br.com.tcc2.agendalab.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.DocenteDAO;
import br.com.tcc2.agendalab.model.Docente;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DocenteBean implements Serializable {
	
	private List<Docente> docentes;
	
	public List<Docente> getDocentes() {
		return docentes;
	}
	
	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	private Docente docente;

	public Docente getDocente() {
		return docente;
	}

	public void novo() {

		docente = new Docente();

	}

	public void salvar() {

		try {
			DocenteDAO docenteDAO = new DocenteDAO();
			docenteDAO.merge(docente);

			novo();
			docentes = docenteDAO.listar();

			Messages.addGlobalInfo("Docente salvo com sucesso!");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar docente");
			erro.printStackTrace();
		}

	}
	
	@PostConstruct
	public void listar() {
		try {
			
			DocenteDAO docenteDAO = new DocenteDAO();
			docentes = docenteDAO.listar();
			
		} catch (RuntimeException erro) {
			
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os tecnicos");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			docente =  (Docente) evento.getComponent().getAttributes().get("selecionado");
		DocenteDAO docenteDAO = new DocenteDAO();
		docenteDAO.excluir(docente);
		docentes = docenteDAO.listar();
		
		Messages.addGlobalInfo("Docente removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Docente");
		erro.printStackTrace();
	}
	}
	
	public void editar(ActionEvent evento){
		try{
			docente =  (Docente) evento.getComponent().getAttributes().get("selecionado");
		DocenteDAO docenteDAO = new DocenteDAO();
		docenteDAO.merge(docente);
		docentes = docenteDAO.listar();
		
		
	} catch (RuntimeException erro) {
		
		erro.printStackTrace();
	}
	}

}
