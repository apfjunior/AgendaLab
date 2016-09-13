package br.com.tcc2.agendalab.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.tcc2.agendalab.util.HibernateUtil;

public class GenericDAO<Entidade> {

	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {

		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	public void salvar(Entidade entidade) {

		// abrir sessao
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {

				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}

	}

	//lis
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(String campoOrdenacao) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			Criteria consulta = sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(campoOrdenacao));
			
			List<Entidade> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}

	}

	
	@SuppressWarnings("unchecked")
	public Entidade buscar(Integer codigo) {

		// abrir sessao
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			Criteria consulta = sessao.createCriteria(classe);
			// restricao
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}

	}

	public void excluir(Entidade entidade) {

		// abrir sessao
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {

				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void merge(Entidade entidade) {

		// abrir sessao
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.merge(entidade);
			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {

				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
