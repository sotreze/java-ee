package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
//import br.com.caelum.livraria.modelo.Livro;
//import br.com.caelum.livraria.util.ForwardView;
//import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped //javax.faces.view.ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();

	@Inject
	private AutorDao dao; //CDI faz new AutorDao() e injeta
		
	private Integer autorId;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPeloId() {
		this.autor = this.dao.buscaPorId(autorId);	
	    if (this.autor == null) {
            this.autor = new Autor();
	    }
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}
	
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}
	
	//se quisermos um forward usamos a classe ForwardView e se quisermos redirect usamos RedirectView!
	
	/*public RedirectView gravar() {
	    System.out.println("Gravando autor " + this.autor.getNome());
	    
	    //new DAO<Autor>(Autor.class).adiciona(this.autor);
		if(this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
	    this.autor = new Autor();
	    
	    return new RedirectView("livro");
	    //return new RedirectView("autor");
	}*/
	
	/*public ForwardView gravar() {
	    System.out.println("Gravando autor " + this.autor.getNome());

	    new DAO<Autor>(Autor.class).adiciona(this.autor);

	    this.autor = new Autor();

	    return new ForwardView("livro");
	}*/
	
	/*public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		this.autor = new Autor();
	}*/
	
	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;	
	}
	
	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		this.dao.remove(autor);
	}
	
}
