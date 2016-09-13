package br.com.tcc2.agendalab.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.TecnicoDAO;
import br.com.tcc2.agendalab.model.Tecnico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TecnicoBean implements Serializable {

	private List<Tecnico> tecnicos ;
	
	public List<Tecnico> getTecnicos() {
		return tecnicos;
	}
	
	public void setTecnicos(List<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}
	
	
	private Tecnico tecnico;

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void novo() {

		tecnico = new Tecnico();

	}

	public void salvar() {

		try {
			TecnicoDAO tecDAO = new TecnicoDAO();
			tecDAO.merge(tecnico);

			novo();
			tecnicos = tecDAO.listar();
			Messages.addGlobalInfo("Técnico salvo com sucesso!");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar Técnico");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			TecnicoDAO tecnicoDAO = new TecnicoDAO();
			tecnicos = tecnicoDAO.listar();
			
		} catch (RuntimeException erro) {
			
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os tecnicos");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			tecnico =    (Tecnico) evento.getComponent().getAttributes().get("selecionado");
		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		tecnicoDAO.excluir(tecnico);
		tecnicos = tecnicoDAO.listar();
		
		Messages.addGlobalInfo("Laboratório removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Laboratório");
		erro.printStackTrace();
	}
	}
	
	public void editar(ActionEvent evento){
		try{
			tecnico =    (Tecnico) evento.getComponent().getAttributes().get("selecionado");
			TecnicoDAO tecnicoDAO = new TecnicoDAO();
			tecnicoDAO.merge(tecnico);
			tecnicos = tecnicoDAO.listar();
				
		
	} catch (RuntimeException erro) {
		
		erro.printStackTrace();
	}
	}

}
