package br.com.casadocodigo.loja.websockets;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/canal/promos")
public class PromosEndPoint {
	
	@OnOpen
	public void onMessage(Session session) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(session.isOpen()) {
			try {
				session.getBasicRemote().sendText("{\"titulo\": \"Livro Java OO com 40% de desconto\", \"livroId\": 1}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
