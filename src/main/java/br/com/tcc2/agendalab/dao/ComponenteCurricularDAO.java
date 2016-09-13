package br.com.tcc2.agendalab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.tcc2.agendalab.model.ComponenteCurricular;
import br.com.tcc2.agendalab.util.HibernateUtil;

public class ComponenteCurricularDAO extends GenericDAO<ComponenteCurricular> {

	@SuppressWarnings("unchecked")
	public List<ComponenteCurricular> buscarPorTurmas(Integer turmaId) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(ComponenteCurricular.class);
			consulta.add(Restrictions.eq("turma.id", turmaId));
			consulta.addOrder(Order.asc("nome"));
			List<ComponenteCurricular> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;

		} finally {

			sessao.close();
		}

	}

}
