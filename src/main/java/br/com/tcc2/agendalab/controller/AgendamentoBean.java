
package br.com.tcc2.agendalab.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.tcc2.agendalab.dao.AgendamentoDAO;
import br.com.tcc2.agendalab.dao.ComponenteCurricularDAO;
import br.com.tcc2.agendalab.dao.LaboratorioDAO;
import br.com.tcc2.agendalab.dao.TecnicoDAO;
import br.com.tcc2.agendalab.dao.UsuarioDAO;
import br.com.tcc2.agendalab.model.Agendamento;
import br.com.tcc2.agendalab.model.ComponenteCurricular;
import br.com.tcc2.agendalab.model.EnumClass;
import br.com.tcc2.agendalab.model.Laboratorio;
import br.com.tcc2.agendalab.model.Tecnico;
import br.com.tcc2.agendalab.model.Usuario;
import br.com.tcc2.agendalab.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {

	private ScheduleModel agendamentos;
	private Agendamento agendamento;
	private List<Usuario> usuarios;
	private List<ComponenteCurricular> disciplinas;
	private ComponenteCurricular disciplina; // variavel temporaria
	private List<Laboratorio> laboratorios;
	private List<Tecnico> tecnicos;
	private List<Agendamento> listAgenda;

	// DAO
	private AgendamentoDAO eDAO;

	public ScheduleModel getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(ScheduleModel agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<ComponenteCurricular> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<ComponenteCurricular> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public ComponenteCurricular getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(ComponenteCurricular disciplina) {
		this.disciplina = disciplina;
	}

	public List<Laboratorio> getLaboratorios() {
		return laboratorios;
	}

	public void setLaboratorios(List<Laboratorio> laboratorios) {
		this.laboratorios = laboratorios;
	}

	public List<Tecnico> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public List<Agendamento> getListAgenda() {
		return listAgenda;
	}

	public void setListAgenda(List<Agendamento> listAgenda) {
		this.listAgenda = listAgenda;
	}

	public AgendamentoDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(AgendamentoDAO eDAO) {
		this.eDAO = eDAO;
	}

	@PostConstruct
	public void listar() {

		agendamento = new Agendamento();
		agendamentos = new DefaultScheduleModel();

		listAgenda = new ArrayList<>();

		try {

			AgendamentoDAO agendaDAO = new AgendamentoDAO();
			listAgenda = agendaDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar listar os dados na tabela");
			erro.printStackTrace();
		}

		preencherSchedule();

	}

	// metodo que retorna descricao EnumClass
	public EnumClass[] getStatusConlusao() {
		return EnumClass.values();
	}

	// preenche dados no banco no campo data no ScheduleEvent
	public void preencherSchedule() {

		for (Agendamento ev : listAgenda) {

			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setEndDate(ev.getFimDataHoraAgendamento());
			evt.setStartDate(ev.getInicioDataHoraAgendamento());
			evt.setTitle(ev.getLaboratorio().getNome());
			evt.setData(ev.getId());
			evt.setDescription(ev.getMaterialAula());
			evt.setAllDay(false);
			evt.setEditable(false);

			if (ev.getStatusConclusaoo() == (EnumClass.CONFIRMADO)) {
				evt.setStyleClass("aprovado");
			} else if (ev.getStatusConclusaoo() == (EnumClass.REJEITADO)) {
				evt.setStyleClass("rejeitado");
			} else {
				evt.setStyleClass("inicial");
			}
			agendamentos.addEvent(evt);
		}
	}

	// preencher dialogo com valor no Schedule;
	public void quandoSelecionadoScheduleComValor(SelectEvent selectEvent) {

		ListaCompForm();
		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

		for (Agendamento agendaLab : listAgenda) {

			if (agendaLab.getId() == (Integer) event.getData()) {

				agendamento = agendaLab;
				break;
			}
		}
	}

	// quando move agendamento para outra data; (CANCELADO)
	// ######################
	public void quandoAgendamentoMovido(ScheduleEntryMoveEvent event) {
		for (Agendamento agenda : listAgenda) {
			// compara os IDs
			if (agenda.getId() == (Integer) event.getScheduleEvent().getData())
				;
			agendamento = agenda;
			try {
				salvaAgendamento();
				listar();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Cópia de formuário com sucesso!", "Atualize as informacões."));
			} catch (Exception ex) {
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro ao salvar agendamento", "Erro: " + ex.getMessage()));
			}
			break;
		}
	}

	// quando redimensionado data; (CANCELADO)
	// ###################################
	public void quandoAgendamentoRedimensionado(ScheduleEntryResizeEvent event) {
		for (Agendamento agenda : listAgenda) {
			// compara os IDs
			if (agenda.getId() == (Integer) event.getScheduleEvent().getData())
				;
			agendamento = agenda;
			try {
				AgendamentoDAO agendaDAO = new AgendamentoDAO();
				agendaDAO.merge(agendamento);
				listar();
				Messages.addGlobalInfo("Agendamento redimensionado com sucesso!");
			} catch (Exception ex) {
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro ao salvar agendamento", "Erro: " + ex.getMessage()));
			}
			break;
		}
	}

	public void novo(SelectEvent selectEvent) {

		try {
			agendamento = new Agendamento();
			pegaDataInicioFim(selectEvent);
			ListaCompForm();

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao gerar o agendamento");
			erro.printStackTrace();
		}
	}

	// captura data clicada Schedule incio e fim;
	public void pegaDataInicioFim(SelectEvent selectEvent) {
		agendamento.setInicioDataHoraAgendamento((Date) selectEvent.getObject());
		agendamento.setFimDataHoraAgendamento((Date) selectEvent.getObject());
	}

	// lista componentes do formulario
	public void ListaCompForm() {

		// usuario
		UsuarioDAO userDAO = new UsuarioDAO();
		usuarios = userDAO.listar("login");
		// tecnicos
		TecnicoDAO tecDAO = new TecnicoDAO();
		tecnicos = tecDAO.listar("nome");
		// componenteCurricular
		ComponenteCurricularDAO compoDAO = new ComponenteCurricularDAO();
		disciplinas = compoDAO.listar("nome");
		// laboratorios
		LaboratorioDAO labDAO = new LaboratorioDAO();
		laboratorios = labDAO.listar("nome");
	}

	public void salvar() {
		if (agendamento.getId() == null) {
			if (agendamento.getInicioDataHoraAgendamento().getTime() <= agendamento.getFimDataHoraAgendamento()
					.getTime()) {
				try {
					salvaAgendamento(); // salva sem update
					listar();
				} catch (RuntimeException erro) {
					Messages.addGlobalError("Algo deu errado ao tentar efetuar o agendamento");
					erro.printStackTrace();
				}
				agendamento = new Agendamento();
			} else {
				Messages.addGlobalError("Data inicial não pode ser maior que a final");
			}
		} else {
			try {
				mergeAgendamento();
			} catch (Exception ex) {
				Messages.addGlobalError("Algo deu errado ao tentar efetuar o agendamento");
			}
		}
	}

	// validará se a data inicial é maior que a data final;
	public void salvaAgendamento() {

		AgendamentoDAO agendaDAO = new AgendamentoDAO();
		agendaDAO.salvar(agendamento);
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Solicitacção enviada com sucesso!", " Aguarde avaliação."));
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

	}

	public void mergeAgendamento() {

		AgendamentoDAO agendaDAO = new AgendamentoDAO();
		agendaDAO.merge(agendamento);
		listar();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitação atualizada!", "Aguardando avaliação."));
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

	}

	// converte data inicio
	public void converteDataInicio() {
		// no banco precisa-se soma + 1 dia;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(agendamento.getInicioDataHoraAgendamento());
		calendar.add(Calendar.DATE, 1);
		agendamento.setInicioDataHoraAgendamento(calendar.getTime());
	}

	// converte data fim
	public void converteDataFim() {
		// no banco precisa-se soma + 1 dia;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(agendamento.getFimDataHoraAgendamento());
		calendar.add(Calendar.DATE, 1);
		agendamento.setFimDataHoraAgendamento(calendar.getTime());

	}

	/**
	 * [[INICIO]] PARTE EDITAR DA GESTAO-AGENDAMENTO
	 */

	// editar
	public void editar(ActionEvent evento) {

		agendamento = (Agendamento) evento.getComponent().getAttributes().get("selecionado");
		// listar componentes
		ListaCompForm();

	}

	/**
	 * [[FIM]] PARTE EDITAR DA GESTAO-AGENDAMENTO
	 */

	// popular componente curricilar
	public void popular() {

		try {

			buscarComponentes();

		} catch (RuntimeException erro) {

			System.out.println("erro");
			Messages.addGlobalError("Algo deu errado ao tentar efetuar o agendamento");
			erro.printStackTrace();
		}

	}

	// preenche inputText de acordo com selectionOneMenu;
	public void buscarComponentes() {

		if (disciplina != null) {

			ComponenteCurricularDAO compoDAO = new ComponenteCurricularDAO();
			disciplinas = compoDAO.buscarPorTurmas(disciplina.getId());

		} else {

			System.out.println("filtrado com sucesso!");
		}

	}

	// popula selectOneMenu com Enum
	public EnumClass[] retornoEnum() {

		return EnumClass.values();

	}

	// captura data Atual
	public Date dataAtual() {

		return new Date();
	}

	// limpar campos
	public void limparCampos() {
		agendamento = new Agendamento();
		Messages.addGlobalInfo("Campos Limpos");

	}

	// imprimir relatorio agendamentos;
	public void imprimirRelatorio() throws IOException {

		try {

			// capturar dados da tabela
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

			// pegar filtros do datatable
			Map<String, Object> filtros = tabela.getFilters();

			String agendaLab = (String) filtros.get("laboratorio.nome");
			String agendaStatus = (String) filtros.get("statusConclusaoo");

			String caminho = Faces.getRealPath("/reports/relatorio_agendamentos.jasper");

			Map<String, Object> paramentros = new HashMap<>();
			if (agendaLab == null && agendaStatus == null) {
				paramentros.put("LAB_AGENDAMENTO", "%%");
				paramentros.put("STATUS", "%%");
			} else {
				paramentros.put("LAB_AGENDAMENTO", "%" + agendaLab + "%");
				paramentros.put("STATUS", "%" + agendaStatus + "%");
			}
			if (agendaLab != null && agendaStatus == null) {

				Messages.addGlobalError("Por favor, selecione o STATUS do agendamento para emissão.");
			}
			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, paramentros, conexao);

			/**
			 * Metodo funciona local, mas nao fuinciona no servidor cloud.
			 */
			JasperExportManager.exportReportToPdfFile(relatorio, "Relatório.pdf");
			java.awt.Desktop.getDesktop().open(new File("Relatório.pdf"));
			Messages.addGlobalInfo("PDF gerado com sucesso! ");

			/**
			 * chama ctrol+p
			 */
			// JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {

			Messages.addGlobalError("Erro: Falha na leitura dos filtros, tente novamente. ");
			erro.printStackTrace();
		}
	}

	// imprimir por periodo
	public void imprimirRelatorioP() throws IOException {

		Date inicialData;
		Date finalData;

		inicialData = agendamento.getInicioDataHoraAgendamento();
		finalData = agendamento.getFimDataHoraAgendamento();

		try {

			String caminhoLogo = Faces.getRealPath("/resources/images/logo_estrela_federativa.png");
			String caminho = Faces.getRealPath("/reports/relatorio_agendamentosP.jasper");

			Map<String, Object> paramentros = new HashMap<>();
			paramentros.put("CAMINHO_LOGO", caminhoLogo);
			paramentros.put("INICIO", inicialData);
			paramentros.put("FIM", finalData);

			if (inicialData == null && finalData == null) {

				Messages.addGlobalError("Não foi possível emitir o relatório");

			}

			if (inicialData == null && finalData != null) {
				Messages.addGlobalError("Não foi possível emitir o relatório. ");

			}
			if (inicialData != null && finalData == null) {
				Messages.addGlobalError("Não foi possível emitir o relatório. ");

			}
			if (inicialData != null && finalData != null) {

				Connection conexao = HibernateUtil.getConexao();

				JasperPrint relatorio = JasperFillManager.fillReport(caminho, paramentros, conexao);

				/**
				 * Metodo funciona local, mas nao fuinciona no servidor cloud.
				 */
				JasperExportManager.exportReportToPdfFile(relatorio, "Relatório_por_perído.pdf");
				java.awt.Desktop.getDesktop().open(new File("Relatório_por_perído.pdf"));
				Messages.addGlobalInfo("PDF gerado com sucesso! ");

				/**
				 * chama ctrol+p
				 */
				// JasperPrintManager.printReport(relatorio, true);
			}

		} catch (JRException erro) {

			Messages.addGlobalError("Por Favor, verifique as datas e tente novamente ");
			erro.printStackTrace();
		}
	}

	// imprimir relatorio agendamentos;
	public void imprimirFicha(ActionEvent evento) throws IOException {
		Integer recebeAgenda;
		agendamento = (Agendamento) evento.getComponent().getAttributes().get("selecionado");
		try {

			recebeAgenda = (Integer) agendamento.getId();
			System.out.println(recebeAgenda);

			String caminhoLogo = Faces.getRealPath("/resources/images/logo_estrela_federativa.png");
			String caminho = Faces.getRealPath("/reports/ficha_atividades.jasper");

			Map<String, Object> paramentros = new HashMap<>();
			paramentros.put("CAMINHO_LOGO", caminhoLogo);

			paramentros.put("AGENDAMENTO_SELECT", recebeAgenda);

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, paramentros, conexao);
			JasperExportManager.exportReportToPdfFile(relatorio, "Ficha_de_atividades_assinatura.pdf");
			java.awt.Desktop.getDesktop().open(new File("Ficha_de_atividades_assinatura.pdf"));

			Messages.addGlobalInfo("PDF gerado com sucesso! ");

			/**
			 * chama ctrol+p
			 */
			// JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {

			Messages.addGlobalError("Erro: Falha na leitura dos filtros, tente novamente. ");
			erro.printStackTrace();
		}
	}

	// imprimir agendamento em branco
	public void imprimirFichaEmBranco() throws IOException {

		try {

			String caminhoLogo = Faces.getRealPath("/resources/images/logo_estrela_federativa.png");
			String caminho = Faces.getRealPath("/reports/ficha_atividades_em_branco.jasper");

			Map<String, Object> paramentros = new HashMap<>();
			paramentros.put("CAMINHO_LOGO", caminhoLogo);

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, paramentros, conexao);

			/**
			 * Metodo funciona local, mas nao fuinciona no servidor cloud.
			 */
			JasperExportManager.exportReportToPdfFile(relatorio, "Ficha_em_branco.pdf");
			java.awt.Desktop.getDesktop().open(new File("Ficha_em_branco.pdf"));
			Messages.addGlobalInfo("PDF gerado com sucesso! ");

			/**
			 * chama ctrol+p
			 */
			// JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {

			Messages.addGlobalError("Erro: Falha na emissão da impressão em branco. Tente Novamente. ");
			erro.printStackTrace();
		}
	}

}
