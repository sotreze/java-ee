package br.com.caelum.livraria.modelo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro> {
	
	DAO<Livro> dao = new DAO<Livro>(Livro.class);
	private DAO<Livro> daoLivro;
	
	public LivroDataModel() {
	    super.setRowCount(dao.quantidadeDeElementos());
	}

	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
	    String titulo = (String) filtros.get("genero");

	   List<Livro> data = dao.listaTodosPaginada(inicio, quantidade, "genero", titulo);
	
	 // sort
        if (campoOrdenacao != null) {
            Collections.sort(data, new LazySorter(campoOrdenacao, sentidoOrdenacao));
        }

        return data;
	}
	
	
//    @Override
//    public List<Livro> load(int first, int pageSize, String sortField, SortOrder sortOrder,
//            Map<String, Object> filters) {
//
//        Set<String> keySet = filters.keySet();
//        String valorDigitado = null;
//        String campoFiltrado = null;
//        for (String key : keySet) {
//            if (filters.containsKey(key))
//                valorDigitado = (String) filters.get(key);
//                campoFiltrado = key;
//        }
//
//        return dao.listaTodosPaginada(first, pageSize, campoFiltrado, valorDigitado);
//
//    }
	
	
	
}
