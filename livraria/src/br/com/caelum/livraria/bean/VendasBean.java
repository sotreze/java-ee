package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.VendaDao;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//@Inject
	//EntityManager manager;
	
	@Inject
	private VendaDao vendaDao;
	
	private List<Venda> vendas;

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);

		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2016");

		List<Venda> vendas = getVendas();
		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		model.addSeries(vendaSerie);

		return model;
	}

//	public List<Venda> getVendas() {
//
//		List<Venda> vendas = this.vendaDao.createQuery("select v from Venda v", Venda.class).getResultList();
//		return vendas;
//	}
	
	public List<Venda> getVendas() {

		if (this.vendas == null) {
			this.vendas = this.vendaDao.listaTodos();
		}
		return vendas;
	}
}
