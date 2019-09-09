package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped //javax.faces.view.ViewScoped
public class VendasBean implements Serializable {


	private static final long serialVersionUID = 1L;

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		
		ChartSeries vendaSerie2019 = new ChartSeries();
		vendaSerie2019.setLabel("Vendas 2019");

		List<Venda> vendas = getVendas(1234);
		for (Venda venda : vendas) {
			vendaSerie2019.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		ChartSeries vendaSerie2018 = new ChartSeries();
		vendaSerie2018.setLabel("Vendas 2018");
		
		vendas = getVendas(4321);
		for (Venda venda : vendas) {
			vendaSerie2018.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		model.addSeries(vendaSerie2019);
		model.addSeries(vendaSerie2018);
		
	    model.setTitle("Vendas"); // setando o título do gráfico
	    model.setLegendPosition("ne"); // nordeste

	    // pegando o eixo X do gráfico e setando o título do mesmo
	    Axis xAxis = model.getAxis(AxisType.X);
	    xAxis.setLabel("Título");

	    // pegando o eixo Y do gráfico e setando o título do mesmo
	    Axis yAxis = model.getAxis(AxisType.Y);
	    yAxis.setLabel("Quantidade");

		return model;
	}

	public List<Venda> getVendas(long seed) {

		List<Venda> vendas = new ArrayList<Venda>();
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();

		Random random = new Random(seed);	
		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}

		return vendas;

	}

}