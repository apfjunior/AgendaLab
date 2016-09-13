package br.com.tcc2.agendalab.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tcc2.agendalab.model.Curso;
import br.com.tcc2.agendalab.model.Turma;

public class TurmaDAOTest {

	@Test
	public void salvar() {

		TurmaDAO turmaDAO = new TurmaDAO();
		Turma turma = new Turma();

		// buscar curso
		Integer codCurso = 1;
		CursoDAO cursoDAO = new CursoDAO();
		Curso curso = cursoDAO.buscar(codCurso);

		turma.setCodEstatico("35777");
		turma.setCurso(curso);
		turma.setAtivo(true);

		turmaDAO.salvar(turma);
		System.out.println("Salvo com sucesso");

	}

	@Test
	@Ignore
	public void listar(){
		
		TurmaDAO turmaDAO = new TurmaDAO();
		List<Turma> resultado = turmaDAO.listar();
		
		for(Turma turma : resultado) {

			System.out.println("Cod:  " + turma.getId() );
			System.out.println("Cod Estatico:  " + turma.getCodEstatico());
			System.out.println("Turma:  " + turma.getCurso().getNome() );
			System.out.println("Ativo:  " + turma.getAtivo());
				
			
		}
	}

}
