package br.com.tcc2.agendalab.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.tcc2.agendalab.model.Usuario;
import br.com.tcc2.agendalab.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	public Usuario autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			
			
			consulta.add(Restrictions.eq("login", login));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
