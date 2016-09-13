package br.com.tcc2.agendalab.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import br.com.tcc2.agendalab.model.Pessoa;

public class PessoaDAOTest {

	@Test
	
	public void salvar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = new Pessoa();

		pessoa.setNome("Francisco Fabrício");
		pessoa.setTelefone("(95)3624-1111");
		pessoa.setCelular("(95)99233-1111");
		pessoa.setEmail("francisco@ifrr.edu.br");
		pessoa.setMatricula("20131prof0329");

		pessoaDAO.salvar(pessoa);
		System.out.println("salvo com sucesso! ");

	}

	@Test
	@Ignore
	public void listar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();

		for (Pessoa pessoa : resultado) {

			System.out.println("");
			System.out.println("Cod: " + pessoa.getId());
			System.out.println("Nome: " + pessoa.getNome());
			System.out.println("Telefone: " + pessoa.getTelefone());
			System.out.println("Celular" + pessoa.getCelular());
			System.out.println("E-mail: " + pessoa.getEmail());
			System.out.println("Matrícula: " + pessoa.getMatricula());

		}

	}

	@Test
	@Ignore
	public void buscar() {

		// buscar pessoa
		Integer codPessoa = 4;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);

		System.out.println("");
		System.out.println("Cod: " + pessoa.getId());
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("Telefone: " + pessoa.getTelefone());
		System.out.println("Celular" + pessoa.getCelular());
		System.out.println("E-mail: " + pessoa.getEmail());
		System.out.println("Matrícula: " + pessoa.getMatricula());

	}

	@Test
	@Ignore
	public void excluir() {

		// buscar pessoa
		Integer codPessoa = 4;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);

		pessoaDAO.excluir(pessoa);
		System.out.println("excluido com sucesso");

	}

	@Test
	@Ignore
	public void merge() {

		// buscar pessoa
		Integer codPessoa = 1
				;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);

		pessoa.setNome("Antonino Praxedes");
		pessoa.setTelefone("(95)3624-2115");
		pessoa.setCelular("(95)99142-9551");
		pessoa.setEmail("antonino@ifrr.edu.br");
		pessoa.setMatricula("20141prof0329");

		pessoaDAO.merge(pessoa);
		System.out.println("UPDATE com sucesso! ");

	}

}
