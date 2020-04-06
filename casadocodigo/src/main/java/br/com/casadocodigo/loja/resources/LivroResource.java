package br.com.casadocodigo.loja.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;

@Path("livros")
public class LivroResource {
	
	@Inject
	private LivroDao dao;
	
	@GET
	@Produces( MediaType.APPLICATION_JSON)
	@Path("json")
	public List<Livro> ultimosLancamentos() {
		
		return dao.ultimosLancamentos();
		
	}

}
