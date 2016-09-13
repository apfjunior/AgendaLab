package br.com.tcc2.agendalab.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tcc2.agendalab.model.Tecnico;

public class TecnicoDAOTest {

	@Test
	
	public void salvar() {

		TecnicoDAO tecDAO = new TecnicoDAO();
		Tecnico tec = new Tecnico();

		tec.setNome("Andrade Anjos");
		tec.setTelefone("(95)3624-2142");
		tec.setCelular("(95)99233-3553");
		tec.setEmail("francisco@ifrr.edu.br");
		tec.setMatricula("20151tec0144");
		tec.setFormacao("Superior");
		tec.setAtivo(true);

		tecDAO.salvar(tec);
		System.out.println("Salvo com sucesso!");

	}

	@Test
	@Ignore
	public void listar() {

		TecnicoDAO tecDAO = new TecnicoDAO();
		List<Tecnico> resultado = tecDAO.listar();

		for (Tecnico tec : resultado) {

			System.out.println("\nCod: " + tec.getId());
			System.out.println("Nome: " + tec.getNome());
			System.out.println("Telefone: " + tec.getTelefone());
			System.out.println("Celular: " + tec.getCelular());
			System.out.println("E-mail: " + tec.getEmail());
			System.out.println("Matricula: " + tec.getMatricula());
			System.out.println("Formacao: " + tec.getFormacao());

		}

	}

	@Test
	@Ignore
	public void buscar() {

		// buscar
		Integer codTec = 2;
		TecnicoDAO tecDAO = new TecnicoDAO();
		Tecnico tec = tecDAO.buscar(codTec);

		System.out.println("\nCod: " + tec.getId());
		System.out.println("Nome: " + tec.getNome());
		System.out.println("Telefone: " + tec.getTelefone());
		System.out.println("Celular: " + tec.getCelular());
		System.out.println("E-mail: " + tec.getEmail());
		System.out.println("Matricula: " + tec.getMatricula());
		System.out.println("Formacao: " + tec.getFormacao());

	}
	@Test
	@Ignore
	public void excluir() {

		// buscar
		Integer codTec = 4;
		TecnicoDAO tecDAO = new TecnicoDAO();
		Tecnico tec = tecDAO.buscar(codTec);

		tecDAO.excluir(tec);
		System.out.println("Excluido com sucesso!");
		
	}

	@Test
	@Ignore
	public void merge() {

		// buscar
		Integer codTec = 3;
		TecnicoDAO tecDAO = new TecnicoDAO();
		Tecnico tec = tecDAO.buscar(codTec);

		tec.setNome("Francisco");
		tec.setTelefone("(95)3624-2142");
		tec.setCelular("(95)99233-3553");
		tec.setEmail("francisco@ifrr.edu.br");
		tec.setMatricula("20151tec0144");
		tec.setFormacao("Superior");

		tecDAO.merge(tec);
		System.out.println("UPDATE com sucesso!");
		
	
	
	}

}
