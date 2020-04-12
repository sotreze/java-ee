package br.com.casadocodigo.loja.service;

import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.CompraDao;
import br.com.casadocodigo.loja.infra.MailSender;
import br.com.casadocodigo.loja.models.Compra;

public class EnviaEmailCompra {
	
    @Inject
    private CompraDao compraDao;
	
	@Inject
	private MailSender mailSender;
	
	public void enviar(String uuid) {
		
		Compra compra = compraDao.buscarPorUuid(uuid);
		String messageBody = "Sua compra foi realizada com sucesso, com número de pedido " + compra.getUuid();
		
		mailSender.send("compras@casadocodigo.com.br", compra.getUsuario().getEmail(), "Nova compra na CDC", messageBody);
		
		
	}

}
