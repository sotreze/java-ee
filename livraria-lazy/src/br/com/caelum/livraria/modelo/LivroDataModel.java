package br.com.caelum.livraria.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.LivroDao;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

@Named
@ViewScoped
public class LivroDataModel extends LazyDataModel<Livro> {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private LivroDao livroDAO;

    @PostConstruct
    void init(){
        super.setRowCount(livroDAO.contaTodos());
    }

    @Override
    public List<Livro> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        List<String> colunas = new ArrayList<String>();
        List<String> valores = new ArrayList<String>();

        for (Entry<String, Object> entry : filters.entrySet()) {
            colunas.add(entry.getKey());
            valores.add(entry.getValue().toString());
        } 

        return this.livroDAO.listaTodosPaginada(first, pageSize, colunas, valores);
    }

}
