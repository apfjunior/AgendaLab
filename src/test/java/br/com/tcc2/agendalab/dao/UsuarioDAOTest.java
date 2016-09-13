package br.com.tcc2.agendalab.dao;

import java.util.Date;

import org.junit.Test;

import br.com.tcc2.agendalab.model.Perfil;
import br.com.tcc2.agendalab.model.Pessoa;
import br.com.tcc2.agendalab.model.Usuario;

public class UsuarioDAOTest {
	
	@Test
	public void salvar(){
		
		UsuarioDAO userDAO = new UsuarioDAO();
		Usuario user = new Usuario();
		
		//buscar perfil		
		Integer codPerfil = 2;
		PerfilDAO perfilDAO = new PerfilDAO();
		Perfil perfil = perfilDAO.buscar(codPerfil);
		
		//buscar pessoa
		Integer codPessoa = 2;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);
		
		user.setLogin("anjos");
		user.setSenha("123");
		user.setAtivo(true);
		user.setPerfil(perfil);
		user.setDataCadastro(new Date());
		user.setPessoa(pessoa);
		
		userDAO.salvar(user);
		System.out.println("Salvo com sucesso!");
	
	}

}
