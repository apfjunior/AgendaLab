package br.com.tcc2.agendalab.util;

import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.tcc2.agendalab.controller.AutenticacaoBean;
import br.com.tcc2.agendalab.model.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {
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

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
	
		boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");
	
		if(!ehPaginaDeAutenticacao){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			if(autenticacaoBean == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if(usuario == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			if(usuario!=null && autenticacaoBean!=null){
				
				if (usuario.getPerfil().getNome().equals("Docente")){
					
					if(paginaAtual.equals("/pages/componenteCurricular.xhtml")
							||paginaAtual.equals("/pages/curso.xhtml")
							||paginaAtual.equals("/pages/docente.xhtml") 
							||paginaAtual.equals("/pages/gestaoAgendamento.xhtml")
							||paginaAtual.equals("/pages/laboratorio.xhtml")
							||paginaAtual.equals("/pages/perfil.xhtml")
							||paginaAtual.equals("/pages/tecnico.xhtml")
							||paginaAtual.equals("/pages/usuario.xhtml")
							||paginaAtual.equals("/pages/agendamentoAdministrador.xhtml")
							||paginaAtual.equals("/pages/relatorioAgendamentosEmitidosPorPeriodos.xhtml")
							||paginaAtual.equals("/pages/relatorioAgendamentosEmitidosPorStatus.xhtml")
							||paginaAtual.equals("/pages/turma.xhtml")){
						Faces.navigate("/pages/acessoNegado.xhtml");
						
					}
				}
				
				if (usuario.getPerfil().getNome().equals("Tecnico")){
					
					if(paginaAtual.equals("/pages/agendamento.xhtml")
							||paginaAtual.equals("/pages/relatorioAgendamentosEmitidosPorStatus.xhtml")){
						
						Faces.navigate("/pages/acessoNegado.xhtml");
						
					}
				}
			}
		}		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
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

}
