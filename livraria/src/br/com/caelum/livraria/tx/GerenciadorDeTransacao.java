package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object executaTX(InvocationContext context) throws Exception {	
		
		// abre transacao
		manager.getTransaction().begin();
		System.out.println("abrindo tx");
		
		//chamar os daos que precisam de uma TX
		Object resultado = context.proceed();
		
		System.out.println("fechando tx");
		// commita a transacao
		manager.getTransaction().commit();
		
		return resultado;
	}

}
