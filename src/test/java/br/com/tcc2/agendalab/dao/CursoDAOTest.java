package br.com.tcc2.agendalab.dao;
/*
 * CLASS OK! #Antonino
 * 
 * */
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tcc2.agendalab.model.Curso;

public class CursoDAOTest {

	@Test
	
	public void salvar() {

		Curso curso = new Curso();
		curso.setNome("Eletrônica");
		curso.setTurno("Noturno");
		curso.setAtivo(true);

		CursoDAO cursoDAO = new CursoDAO();
		cursoDAO.salvar(curso);

		System.out.println("Salvo com sucesso!");

	}

	@Test
	@Ignore
	public void listar() {

		CursoDAO cursoDAO = new CursoDAO();
		List<Curso> resultado = cursoDAO.listar();

		for (Curso curso : resultado) {

			System.out.println("");
			System.out.println("Cod: " + curso.getId());
			System.out.println("Nome do curso: " + curso.getNome());
			System.out.println("Turno: " + curso.getTurno());
			System.out.println("Ativo: " + curso.getAtivo());

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codCurso = 1;

		// buscar
		CursoDAO cursoDAO = new CursoDAO();
		Curso curso = cursoDAO.buscar(codCurso);

		System.out.println("");
		System.out.println("Cod: " + curso.getId());
		System.out.println("Nome do Curso: " + curso.getNome());
		System.out.println("Turno: " + curso.getTurno());
		System.out.println("");

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codCurso = 3;

		// buscar
		CursoDAO cursoDAO = new CursoDAO();
		Curso curso = new CursoDAO().buscar(codCurso);

		cursoDAO.excluir(curso);

		System.out.println("Excluído com sucesso!");

	}

	@Test
	@Ignore
	public void merge() {

		Integer codCurso = 1;
		// buscar
		CursoDAO cursoDAO = new CursoDAO();
		Curso curso = cursoDAO.buscar(codCurso);

		curso.setNome("Eletroténica");
		curso.setTurno("Vespertino");

		cursoDAO.merge(curso);

		System.out.println("Editado com sucesso!");

	}

}
