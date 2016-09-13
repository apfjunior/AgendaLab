package br.com.tcc2.agendalab.dao;

import org.junit.Test;

import br.com.tcc2.agendalab.model.Perfil;

public class PerfilDAOTest {
	
	@Test
	public void salvar(){
		
		PerfilDAO perfilDAO = new PerfilDAO();
		Perfil perfil = new Perfil();
		
		perfil.setNome("Técnico");
		perfilDAO.salvar(perfil);
		
		System.out.println("Salvo com sucesso! ");
	}
	

}
