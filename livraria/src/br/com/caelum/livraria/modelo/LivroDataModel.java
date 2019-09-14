package br.com.caelum.livraria.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.LivroDao;

public class LivroDataModel extends LazyDataModel<Livro> {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private LivroDao livroDao;

    @PostConstruct
    void init(){
        super.setRowCount(livroDao.contaTodos());
    }

    @Override
    public List<Livro> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        List<String> colunas = new ArrayList<String>();
        List<String> valores = new ArrayList<String>();

        for (Entry<String, Object> entry : filters.entrySet()) {
            colunas.add(entry.getKey());
            valores.add(entry.getValue().toString());
        } 

        return this.livroDao.listaTodosPaginada(first, pageSize, colunas, valores);
    }
 
    
}
