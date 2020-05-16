package br.com.casadocodigo.loja.daos;

import java.io.Serializable;
//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Compra;

public class CompraDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	/*
	 * public List<Compra> listar(){ return
	 * manager.createQuery("select * from Compra c where c.usuario_id = :usuario_id"
	 * , Compra.class) .getResultList();
	 * 
	 * }
	 */
    
	public void salvar(Compra compra) {
		manager.persist(compra);
	}

	public Compra buscarPorUuid(String uuid) {
		return manager.createQuery("select c from Compra c where c.uuid = :uuid", Compra.class)
				.setParameter("uuid", uuid).getSingleResult();
	}
	
}
