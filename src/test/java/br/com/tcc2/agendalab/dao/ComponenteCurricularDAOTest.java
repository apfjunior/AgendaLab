package br.com.tcc2.agendalab.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tcc2.agendalab.model.ComponenteCurricular;
import br.com.tcc2.agendalab.model.Turma;

public class ComponenteCurricularDAOTest {

	@Test

	public void salvar() {
		// compo
		ComponenteCurricularDAO componenteDAO = new ComponenteCurricularDAO();
		ComponenteCurricular componente = new ComponenteCurricular();

		// turma
		Integer turmaID = 1;
		TurmaDAO turmaDAO = new TurmaDAO();
		Turma turma = turmaDAO.buscar(turmaID);

		componente.setNome("Int. Eletricidade");
		componente.setTurma(turma);
		componente.setAtivo(true);
		componenteDAO.salvar(componente);

		System.out.println("Salvo com sucesso! ");

	}

	@Test
	@Ignore
	public void listar() {

		ComponenteCurricularDAO componenteDAO = new ComponenteCurricularDAO();
		List<ComponenteCurricular> resultado = componenteDAO.listar();

		for (ComponenteCurricular componente : resultado) {

			System.out.println("\nCod Componente Curricular: " + componente.getId());

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codComponente = 2;

		ComponenteCurricularDAO componenteDAO = new ComponenteCurricularDAO();
		ComponenteCurricular componente = componenteDAO.buscar(codComponente);

		System.out.println("Cod: " + componente.getId());
		System.out.println("Nome " + componente.getNome());
	}

	@Test
	@Ignore
	public void excluir() {

		Integer codComponente = 3;

		ComponenteCurricularDAO componenteDAO = new ComponenteCurricularDAO();
		ComponenteCurricular componente = componenteDAO.buscar(codComponente);

		componenteDAO.excluir(componente);

		System.out.println("Deletado com sucesso!");

	}

	@Test
	@Ignore
	public void merge() {

		Integer codComponente = 2;

		ComponenteCurricularDAO componenteDAO = new ComponenteCurricularDAO();
		ComponenteCurricular componente = componenteDAO.buscar(codComponente);

		componente.setNome("Eletricidade básica");
		componenteDAO.merge(componente);

		System.out.println("Editado com sucesso! ");

	}

	@Test
	@Ignore
	public void buscarPorTurma() {

		Integer turmaID = 2;

		ComponenteCurricularDAO componenteDAO = new ComponenteCurricularDAO();
		List<ComponenteCurricular> resultado = componenteDAO.buscarPorTurmas(turmaID);

		for (ComponenteCurricular componente : resultado) {

			System.out.println("\nCod Componente Curricular: " + componente.getId());
			System.out.println("Nome: " + componente.getNome());
			System.out.println("Turma: " + componente.getTurma().getCodEstatico());
			System.out.println("Curso: " + componente.getTurma().getCurso().getNome());

		}

	}

}
