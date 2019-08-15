package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao {
	
	public boolean existe(Usuario usuario) {
	EntityManager em = new JPAUtil().getEntityManager();
	long result = (Long) em.createQuery("select count(n) from livro n")
			.getSingleResult();
	em.close();

		return false;
	}

}
