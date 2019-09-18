package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDao implements Serializable {

    private static final long serialVersionUID = -6662277994148943606L;

    private DAO<Livro> dao;

    public int contaTodos() {
        return dao.contaTodos();
    }

    public List<Livro> listaTodosPaginada(int firstResult, int maxResults, List<String> colunas, List<String> valores) {
        return dao.listaTodosPaginada(firstResult, maxResults, colunas, valores);
    }

    @Inject
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        this.dao = new DAO<Livro>(entityManager, Livro.class);
    }

    public void adiciona(Livro t) {
        dao.adiciona(t);
    }

    public void remove(Livro t) {
        dao.remove(t);
    }

    public void atualiza(Livro t) {
        dao.atualiza(t);
    }

    public List<Livro> listaTodos() {
        return dao.listaTodos();
    }

    public Livro buscaPorId(Integer id) {
        return dao.buscaPorId(id);
    }

//	public List<Livro> listaTodosPaginada(int inicio, int quantidade, String string, String titulo) {
//		return dao.listaTodosPaginada(inicio, quantidade);
//	}
    
    

}