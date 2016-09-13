package br.com.tcc2.agendalab.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tcc2.agendalab.model.Agendamento;
import br.com.tcc2.agendalab.model.ComponenteCurricular;
import br.com.tcc2.agendalab.model.EnumClass;
import br.com.tcc2.agendalab.model.Laboratorio;
import br.com.tcc2.agendalab.model.Tecnico;
import br.com.tcc2.agendalab.model.Usuario;

public class AgendamentoDAOTest {

	@Test
	
	public void salvar() {

		// buscar user
		Integer codUser = 1;
		UsuarioDAO userDAO = new UsuarioDAO();
		Usuario user = userDAO.buscar(codUser);

		// buscar componente curricular
		Integer codComponente = 1;
		ComponenteCurricularDAO compoDAO = new ComponenteCurricularDAO();
		ComponenteCurricular compo = compoDAO.buscar(codComponente);

		// buscar laboratorio
		Integer codLab = 2;
		LaboratorioDAO labDAO = new LaboratorioDAO();
		Laboratorio lab = labDAO.buscar(codLab);


		//buscar tecnico
		Integer codTec = 2;
		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		Tecnico tec = tecnicoDAO.buscar(codTec);
		
		// agendamento
		AgendamentoDAO agendaDAO = new AgendamentoDAO();
		Agendamento agenda = new Agendamento();

		/*
		 * falta acrescentar inicioDataHoraAgendamento e fimDataHoraAgendamento
		 */
		agenda.setUsuario(user);
		agenda.setStatusConclusaoo(EnumClass.REJEITADO);
		agenda.setInicioDataHoraAgendamento(new Date());
		agenda.setFimDataHoraAgendamento(new Date());
		agenda.setAtividadeProgramadas("Base teórica");
		agenda.setCompoCurricular(compo);
		agenda.setObjetivoGeral("Apresentacao da Ementa");
		agenda.setLaboratorio(lab);
		agenda.setTecnico(tec);
		agenda.setTipo("Pesquisa");
		agenda.setQuantAlunos("20");
		agenda.setMaterialAula("01 alicate, 02 lambadas, 3 metros de fio");
		agenda.setAtivo(true);
		agenda.setDataDeRealizacao(new Date());

		agendaDAO.salvar(agenda);
		System.out.println("Sua solicitacão de agendamento foi enviada com sucesso!");

	}

	@Test
	@Ignore
	public void listar() {

		AgendamentoDAO agendaDAO = new AgendamentoDAO();
		List<Agendamento> resultado = agendaDAO.listar();

		for (Agendamento agenda : resultado) {

			System.out.println("\nCod: " + agenda.getId());
			System.out.println("Usuario: " + agenda.getUsuario().getLogin());
			System.out.println("Status Agendamento: " + agenda.getStatusConclusaoo());
			System.out.println("Inicio: " + agenda.getInicioDataHoraAgendamento());
			System.out.println("Fim: " + agenda.getFimDataHoraAgendamento());
			System.out.println("Atividades Programadas: " + agenda.getAtividadeProgramadas());
			System.out.println("Materia: " + agenda.getCompoCurricular().getNome());
			System.out.println("Objetivo Geral: " + agenda.getObjetivoGeral());
			System.out.println("Laboratorio: " + agenda.getLaboratorio().getNome());
			System.out.println("Tipo: " + agenda.getTipo());
			System.out.println("Qnt. Alunos: " + agenda.getQuantAlunos());
			System.out.println("Materiais p/ aula:\n " + agenda.getMaterialAula());
		}

	}
	/*
	 * @Test
	 * 
	 * @Ignore public void buscar() {
	 * 
	 * // buscar Integer codAgenda = 1; AgendamentoDAO agendaDAO = new
	 * AgendamentoDAO(); Agendamento agenda = agendaDAO.buscar(codAgenda);
	 * 
	 * System.out.println("\nCod: " + agenda.getId()); System.out.println(
	 * "Usuario: " + agenda.getUsuario().getLogin()); System.out.println(
	 * "Status Agendamento: " + agenda.getStatusAgendamento());
	 * System.out.println("Data: " + agenda.getDataHoraAgendamento());
	 * System.out.println("Atividades Programadas: " +
	 * agenda.getAtividadeProgramadas()); System.out.println("Materia: " +
	 * agenda.getCompoCurricular().getNome()); System.out.println(
	 * "Objetivo Geral: " + agenda.getObjetivoGeral()); System.out.println(
	 * "Laboratorio: " + agenda.getLaboratorio().getNome()); System.out.println(
	 * "Tipo: " + agenda.getTipo()); System.out.println("Qnt. Alunos: " +
	 * agenda.getQuantAlunos()); System.out.println("Turma: " +
	 * agenda.getTurma().getCodEstatico()); System.out.println(
	 * "Materiais p/ aula:\n " + agenda.getMaterialAula());
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @Ignore public void excluir() {
	 * 
	 * // buscar Integer codAgenda = 11; AgendamentoDAO agendaDAO = new
	 * AgendamentoDAO(); Agendamento agenda = agendaDAO.buscar(codAgenda);
	 * 
	 * agendaDAO.excluir(agenda); System.out.println("Excluido com sucesso!");
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @Ignore public void editar() {
	 * 
	 * // buscar Integer codAgenda = 1; AgendamentoDAO agendaDAO = new
	 * AgendamentoDAO(); Agendamento agenda = agendaDAO.buscar(codAgenda);
	 * 
	 * // buscar user Integer codUser = 1; UsuarioDAO userDAO = new
	 * UsuarioDAO(); Usuario user = userDAO.buscar(codUser);
	 * 
	 * // buscar componente curricular Integer codComponente = 1;
	 * ComponenteCurricularDAO compoDAO = new ComponenteCurricularDAO();
	 * ComponenteCurricular compo = compoDAO.buscar(codComponente);
	 * 
	 * // buscar laboratorio Integer codLab = 1; LaboratorioDAO labDAO = new
	 * LaboratorioDAO(); Laboratorio lab = labDAO.buscar(codLab);
	 * 
	 * // buscar Turma Integer codTurma = 1; TurmaDAO turmaDAO = new TurmaDAO();
	 * Turma turma = turmaDAO.buscar(codTurma);
	 * 
	 * agenda.setUsuario(user); agenda.setStatusAgendamento(true);
	 * agenda.setDataHoraAgendamento(new Date());
	 * agenda.setAtividadeProgramadas("Base teórica");
	 * agenda.setCompoCurricular(compo); agenda.setObjetivoGeral(
	 * "Apresentacao da Ementa"); agenda.setLaboratorio(lab);
	 * agenda.setTipo("Pesquisa"); agenda.setQuantAlunos("20");
	 * agenda.setTurma(turma); agenda.setMaterialAula(
	 * "01 alicate, 02 lambadas, 3 metros de fio");
	 * 
	 * agendaDAO.editar(agenda); System.out.println(
	 * "Agendamento ATUALIZADO com sucesso!");
	 * 
	 * 
	 * }
	 */
}
