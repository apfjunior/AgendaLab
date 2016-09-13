package br.com.tcc2.agendalab.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.LaboratorioDAO;
import br.com.tcc2.agendalab.model.Laboratorio;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LaboratorioBean implements Serializable {
	
	private List<Laboratorio> labs;
	
	public List<Laboratorio> getLabs() {
		return labs;
	}
	
	public void setLabs(List<Laboratorio> labs) {
		this.labs = labs;
	}

	private Laboratorio lab;

	public Laboratorio getLab() {
		return lab;
	}

	public void novo() {

		lab = new Laboratorio();

	}

	public void salvar() {
		try {

			LaboratorioDAO labDAO = new LaboratorioDAO();
			labDAO.merge(lab);

			novo();
			labs = labDAO.listar();
			Messages.addGlobalInfo("Laboratório salvo com sucesso!");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar laboratório");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			LaboratorioDAO laboratioDAO = new LaboratorioDAO();
			labs = laboratioDAO.listar();
			
		} catch (RuntimeException erro) {
			
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			lab =   (Laboratorio) evento.getComponent().getAttributes().get("selecionado");
		LaboratorioDAO labDAO = new LaboratorioDAO();
		labDAO.excluir(lab);
		labs = labDAO.listar();
		
		Messages.addGlobalInfo("Laboratório removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Laboratório");
		erro.printStackTrace();
	}
	}
	
	public void editar(ActionEvent evento){
		try{
			lab =   (Laboratorio) evento.getComponent().getAttributes().get("selecionado");
		LaboratorioDAO labDAO = new LaboratorioDAO();
		labDAO.merge(lab);
		labs = labDAO.listar();
		
		
	} catch (RuntimeException erro) {
		
		erro.printStackTrace();
	}
	}


}
