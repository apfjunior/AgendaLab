package br.com.tcc2.agendalab.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.tcc2.agendalab.dao.UsuarioDAO;
import br.com.tcc2.agendalab.model.Pessoa;
import br.com.tcc2.agendalab.model.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getLogin(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("Login e senha não conferem!");
				return;
			}

			Faces.redirect("./pages/index.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

	public boolean temPermissoes(List<String> permissoes) {
		System.out.println("Permissoes" + permissoes);
		System.out.println("Perfil:" + usuarioLogado.getPerfil().getNome());
		for (String permissao : permissoes) {
			if (usuarioLogado.getPerfil().getNome().equals(permissao)) {
				return true;
			}
		}

		return false;
	}

	public void sair() throws IOException {
		usuario = null;
		AutenticacaoBean autenticacaoBean = null;
		Faces.invalidateSession();
		Faces.redirect("./pages/autenticacao.xhtml");
	}

}
