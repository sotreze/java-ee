package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	
	public static void main(String[] args) {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
	
		
		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta " 
		+ " and m.tipo = :pTipo " 
		+ "group by day(m.data), month(m.data), year(m.data)";

		
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		
		List<Double> medias = (List<Double>) query.getResultList();
		
		System.out.println("A m�dia do dia 22 �: " + medias.get(0));
		System.out.println("A m�dia do dia 27 �: " + medias.get(1));
		System.out.println("A m�dia do dia 28 �: " + medias.get(2));

		
		em.getTransaction().commit();
		em.close();
		
	}
	

}