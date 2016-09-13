package br.com.tcc2.agendalab.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tcc2.agendalab.model.Docente;

public class DocenteDAOTest {

	@Test
	
	public void salvar() {

		DocenteDAO docenteDAO = new DocenteDAO();
		Docente docente = new Docente();

		docente.setNome("Antonino Praxedes");
		docente.setTelefone("(95)3624-2222");
		docente.setCelular("(95)99233-3333");
		docente.setEmail("antonino.praxedes@ifrr.edu.br");
		docente.setMatricula("20131prof0429");
		docente.setFormacao("Superior");
		docente.setAreaEsp("Eletricidade Empresarial");
		docente.setAtivo(true);

		docenteDAO.salvar(docente);
		System.out.println("Salvo com sucesso!");

	}

	@Test
	@Ignore
	public void listar() {

		DocenteDAO docenteDAO = new DocenteDAO();
		List<Docente> resultado = docenteDAO.listar();

		for (Docente docente : resultado) {

			System.out.println("\nCod: " + docente.getId());
			System.out.println("Nome: " + docente.getNome());
			System.out.println("Telefone: " + docente.getTelefone());
			System.out.println("Celular: " + docente.getCelular());
			System.out.println("E-mail: " + docente.getEmail());
			System.out.println("Matricula: " + docente.getMatricula());
			System.out.println("Formacao: " + docente.getFormacao());
			System.out.println("Area Esp.: " + docente.getAreaEsp());

		}

	}

	@Test
	@Ignore
	public void buscar() {

		// buscar docente
		Integer codDocente = 3;
		DocenteDAO docenteDAO = new DocenteDAO();
		Docente docente = docenteDAO.buscar(codDocente);

		System.out.println("\nCod: " + docente.getId());
		System.out.println("Nome: " + docente.getNome());
		System.out.println("Telefone: " + docente.getTelefone());
		System.out.println("Celular: " + docente.getCelular());
		System.out.println("E-mail: " + docente.getEmail());
		System.out.println("Matricula: " + docente.getMatricula());
		System.out.println("Formacao: " + docente.getFormacao());
		System.out.println("Area Esp.: " + docente.getAreaEsp());

	}

	@Test
	@Ignore
	public void excluir() {

		// buscar docente
		Integer codDocente = 3;
		DocenteDAO docenteDAO = new DocenteDAO();
		Docente docente = docenteDAO.buscar(codDocente);

		docenteDAO.excluir(docente);
		System.out.println("Excluido com sucesso! ");

	}

	@Test
	@Ignore
	public void merge() {

		// buscar docente
		Integer codDocente = 2;
		DocenteDAO docenteDAO = new DocenteDAO();
		Docente docente = docenteDAO.buscar(codDocente);

		docente.setNome("Antonino Praxedes");
		docente.setTelefone("(95)3624-2113");
		docente.setCelular("(95)99233-3788");
		docente.setEmail("antonino.praxedes@ifrr.edu.br");
		docente.setMatricula("20131prof0439");
		docente.setFormacao("Superior");
		docente.setAreaEsp("Eletricidade Empresarial");

		docenteDAO.merge(docente);
		System.out.println("UPDATE com sucesso!");

	}

}
