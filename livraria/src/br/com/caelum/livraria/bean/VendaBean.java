package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendaBean {
	
	public List<Venda> getVendas() {
		
		List<Venda> vendas = new ArrayList<Venda>();
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();

		
		
		for (Livro livro : livros) {
			Random random = new Random(1234);
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}
		
		return vendas;

	}

}