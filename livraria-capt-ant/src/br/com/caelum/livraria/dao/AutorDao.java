package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.caelum.livraria.interceptador.LogInterceptador;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
//@Interceptors({LogInterceptador.class})
public class AutorDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Inject
	UserTransaction tx;
	
	@PostConstruct
	void aposCriacao() {
		System.out.println("Autor foi criado");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salva(Autor autor) {
			
		System.out.println("salvando Autor " + autor.getNome());
		
			manager.persist(autor);
		
		System.out.println("salvou Autor " + autor.getNome());
		
		//chamada ao service externo que gera um erro
		
		
		//throw new RuntimeException("Servico externo deu erro!");
	}
	
	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a" , Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class, autorId);
		return autor;
	}
	
}
