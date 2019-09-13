package br.com.caelum.livraria.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.LivroDataModel;

@Named
@ViewScoped //javax.faces.view.ViewScoped
public class LivroBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();
	
	@Inject
	private LivroDao livroDao; //CDI faz new AutorDao() e injeta
	
	@Inject
	private AutorDao autorDao; //CDI faz new AutorDao() e injeta
	
	@Inject
    private LivroDataModel livroDataModel;
	
	private Integer autorId;
	
	private Integer livroId;

	private List<Livro> livros;
	
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");
	
	//private LivroDataModel livroDataModel = new LivroDataModel();
	
	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	
	public void carregarLivroPeloId() {
		this.livro = livroDao.buscaPorId(this.livro.getId());	
//	    if (this.livro == null) {
//            this.livro = new Livro();
//	    }
	}

	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public List<Autor> getAutores() {
		return autorDao.listaTodos();
		//return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros() {
		if(this.livros == null) {
			this.livros = livroDao.listaTodos();
		}		
		return livros;
		}
	
	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro escrito por: " + autor.getNome());
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}
		
		if(this.livro.getId() == null) {
			livroDao.adiciona(this.livro);
			this.livros = livroDao.listaTodos();
		} else {
			livroDao.atualiza(this.livro);
		}
		this.livro = new Livro();
	}
	public void remover(Livro livro) {
		System.out.println("Removendo livro");
		this.livroDao.remove(livro);
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);		
	}
	
	public void carregar(Livro livro) {
		System.out.println("Carregando livro");
		this.livro = livro;	
	}
	
	public String formAutor() {
		System.out.println("Chamando o formulário do Autor");
		return "autor?faces-redirect=true";
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"ISBN deveria começar com 1"));
		}

	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public List<String> getGeneros() {
	    return generos;
	}

	public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) { // java.util.Locale

        //tirando espaços do filtro
        String textoDigitado = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();

        System.out.println("Filtrando pelo " + textoDigitado + ", Valor do elemento: " + valorColuna);

        // o filtro é nulo ou vazio?
        if (textoDigitado == null || textoDigitado.equals("")) {
            return true;
        }

        // elemento da tabela � nulo?
        if (valorColuna == null) {
            return false;
        }

        try {
            // fazendo o parsing do filtro para converter para Double
            Double precoDigitado = Double.valueOf(textoDigitado);
            Double precoColuna = (Double) valorColuna;

            // comparando os valores, compareTo devolve um valor negativo se o value é menor do que o filtro
            return precoColuna.compareTo(precoDigitado) < 0;

        } catch (NumberFormatException e) {

            // usuario nao digitou um numero
            return false;
        }
	}

	
}
