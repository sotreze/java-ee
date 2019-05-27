package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaFuncaoCount {
	
	public static void main(String[] args) {
		
		
		EntityManager manager = new JPAUtil().getEntityManager();
			
		Conta conta = manager.find(Conta.class, 2);

			
		Query query = manager.createQuery("select count(m) from Movimentacao m where m.conta = :pConta");
		query.setParameter("pConta", conta);
		
		
		Long quantidade =  (Long) query.getSingleResult();
		
		System.out.println("A quantidade de valores é: " + quantidade);
		

		
	}
	

}
