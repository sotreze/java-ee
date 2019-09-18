package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DAO<T> implements Serializable {

    private final Class<T> classe;
    private EntityManager em;

    public DAO(EntityManager entityManager, Class<T> classe) {
        this.em = entityManager;
        this.classe = classe;
    }

    public void adiciona(T t) {

        // abre transacao
        em.getTransaction().begin();

        // persiste o objeto
        em.persist(t);

        // commita a transacao
        em.getTransaction().commit();
    }

    public void remove(T t) {
        em.getTransaction().begin();

        em.remove(em.merge(t));

        em.getTransaction().commit();
    }

    public void atualiza(T t) {
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
    }

    public List<T> listaTodos() {
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));

        List<T> lista = em.createQuery(query).getResultList();

        return lista;
    }

    public T buscaPorId(Integer id) {
        T instancia = em.find(classe, id);
        return instancia;
    }

    public int contaTodos() {
        long result = (Long) em.createQuery("select count(n) from " + classe.getSimpleName() + " n")
                .getSingleResult();

        return (int) result;
    }

    public List<T> listaTodosPaginada(int firstResult, int maxResults) {
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));

        List<T> lista = em.createQuery(query).setFirstResult(firstResult)
                .setMaxResults(maxResults).getResultList();

        return lista;
    }

    public List<T> listaTodosPaginada(int firstResult, int maxResults, List<String> colunas, List<String> valores) {
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        Root<T> root = query.from(classe);
        if(valores != null && !valores.isEmpty() && colunas != null && !colunas.isEmpty() && colunas.size() == valores.size()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (int i = 0; i < colunas.size(); i++) {
                predicates.add(em.getCriteriaBuilder().like(root.<String>get(colunas.get(i)), valores.get(i) + "%"));
            }

            query = query.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

        return lista;
    }
}