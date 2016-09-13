package br.com.tcc2.agendalab.dao;



import br.com.tcc2.agendalab.model.Agendamento;


public class AgendamentoDAO  extends GenericDAO<Agendamento>{

	
	/*
	@SuppressWarnings("unchecked")
	public List<AgendamentoBean> buscarPorLaboratorios(Integer labId) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			Criteria consulta = sessao.createCriteria(AgendamentoBean.class);
			
			consulta.add(Restrictions.eq("laboratorio.id", labId));
			
			consulta.addOrder(Order.asc("nome"));
			List<AgendamentoBean> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;

		} finally {

			sessao.close();
		}

	}

	
	*/
}
