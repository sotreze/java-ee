package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

// CDI

@Named
@RequestScoped
public class AdminLivrosBean {
	
	private Livro livro = new Livro();
	
	// Context and Dependency injection
	@Inject
	private LivroDao dao;
	
	private List<Integer> autoresId = new ArrayList<>(); // fazemos new para evitar NullPointerException

	@Inject
	private AutorDao autorDao;

	@Transactional
	public void salvar() {
        for(Integer autorId : autoresId){
            livro.getAutores().add(new Autor(autorId));
           }
		dao.salvar(livro);
		System.out.println("Livro cadastrado: " + livro);
		
		this.livro = new Livro();
		this.autoresId = new ArrayList<>();
    }
	
	public List<Autor> getAutores(){
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

}
