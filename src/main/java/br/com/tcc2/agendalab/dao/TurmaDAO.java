package br.com.tcc2.agendalab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.tcc2.agendalab.model.Turma;
import br.com.tcc2.agendalab.util.HibernateUtil;

public class TurmaDAO extends GenericDAO<Turma> {
	
	@SuppressWarnings("unchecked")
	public List<Turma> buscarPorCurso(Integer codCurso) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			
			Criteria consulta = sessao.createCriteria(Turma.class);
			consulta.add(Restrictions.eq("curso.id", codCurso));	
			consulta.addOrder(Order.asc("codestatico"));
			List<Turma> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}