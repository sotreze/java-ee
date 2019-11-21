package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	@Inject
	private FacesContext context;
	
	@Transactional
	public String salvar() {
        for(Integer autorId : autoresId){
            livro.getAutores().add(new Autor(autorId));
           }
		dao.salvar(livro);
	    
		context.getExternalContext()
        .getFlash().setKeepMessages(true); // Aqui estamos ativando o FlashScope
		context
        .addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));
		
		return "/livros/lista?faces-redirect=true"; // E retornamos a página que o usuário irá sem o .xhtml
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
