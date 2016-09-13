package br.com.tcc2.agendalab.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.PerfilDAO;
import br.com.tcc2.agendalab.model.Perfil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class PerfilBean implements Serializable {
	
	private List<Perfil> perfis;

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	private Perfil perfil;

	public Perfil getPerfil() {
		return perfil;
	}

	public void novo() {

		perfil = new Perfil();

	}

	public void salvar() {
		try {

			PerfilDAO perfilDAO = new PerfilDAO();
			perfilDAO.merge(perfil);

			novo();
			perfis = perfilDAO.listar();
			Messages.addGlobalInfo("Perfil salva com sucesso!");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar Perfil");
			erro.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {
		try {
			
			PerfilDAO perfilDAO = new PerfilDAO();
			perfis = perfilDAO.listar();
			
		} catch (RuntimeException erro) {
			
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
			perfil =   (Perfil) evento.getComponent().getAttributes().get("selecionado");
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.excluir(perfil);
		perfis = perfilDAO.listar();
		
		Messages.addGlobalInfo("Laboratório removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Laboratório");
		erro.printStackTrace();
	}
	}
	public void editar(ActionEvent evento){
		try{
			perfil =   (Perfil) evento.getComponent().getAttributes().get("selecionado");
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.merge(perfil);
		perfis = perfilDAO.listar();
		
		
	} catch (RuntimeException erro) {
		
		erro.printStackTrace();
	}
	}

}
