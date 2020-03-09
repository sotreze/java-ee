package br.com.casadocodigo.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.UsuarioDao;
import br.com.casadocodigo.loja.models.Usuario;

@Model
public class CheckoutBean {

    private Usuario usuario = new Usuario();

    @Inject
    private UsuarioDao usuarioDao;

    @Transactional
    public void finalizar() {
        usuarioDao.salvar(usuario);
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
            
}
