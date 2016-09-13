package br.com.tcc2.agendalab.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.PerfilDAO;
import br.com.tcc2.agendalab.dao.PessoaDAO;
import br.com.tcc2.agendalab.dao.UsuarioDAO;
import br.com.tcc2.agendalab.model.Perfil;
import br.com.tcc2.agendalab.model.Pessoa;
import br.com.tcc2.agendalab.model.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	private List<Perfil> perfis;
	public Usuario getUsuario() {
		return usuario;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public void novo() {
		try {
		
			usuario = new Usuario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			PerfilDAO perfilDAO = new PerfilDAO();
			perfis = perfilDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo usuario");
			erro.printStackTrace();
		}

	}

	

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
			usuarioDAO.merge(usuario);
			
			
			usuario = new Usuario();
			usuarios = usuarioDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			novo();
			Messages.addGlobalInfo("Usuário salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar cadastrar o usuário");
			Messages.addGlobalError("Login ja está cadastrado para outro usuário");
			erro.printStackTrace();
		}
	}	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}

	public Date getDataHoje(){
		
		return new Date();
	}
	
	public void excluir(ActionEvent evento){
		try{
			usuario = (Usuario) evento.getComponent().getAttributes().get("selecionado");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
		setUsuarios(usuarioDAO.listar());
		
		Messages.addGlobalInfo("Usuário removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Usuário");
		erro.printStackTrace();
	}
	}
	
	public void editar(ActionEvent evento){
		try{
			usuario = (Usuario) evento.getComponent().getAttributes().get("selecionado");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		setUsuarios(usuarioDAO.listar());
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoas = pessoaDAO.listar();
		PerfilDAO perfilDAO = new PerfilDAO();
		perfis = perfilDAO.listar();
		usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
				
		usuarioDAO.merge(usuario);
		
		
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar cadastrar o Usuário");
		Messages.addGlobalError("Login ja está cadastrado para outro usuário");
		erro.printStackTrace();
	}
	}
}
