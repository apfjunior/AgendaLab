package br.com.tcc2.agendalab.dao;

import org.junit.Test;

import br.com.tcc2.agendalab.model.Laboratorio;

public class LaboratorioDAOTest {

	@Test
	public void salvar(){
		
		LaboratorioDAO labDAO = new LaboratorioDAO();
		Laboratorio lab = new Laboratorio();
		
		lab.setNome("Lab. de Eletrônica");
		lab.setAtivo(true);
		
		labDAO.salvar(lab);
		System.out.println("Salvo com sucesso!");
		
	}
	
}
