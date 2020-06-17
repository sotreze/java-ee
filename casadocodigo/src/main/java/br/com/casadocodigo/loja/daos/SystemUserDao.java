package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;

import br.com.casadocodigo.loja.models.SystemUser;

public class SystemUserDao {
	
    @PersistenceContext
    private EntityManager manager;
	
    public void salvar(SystemUser systemUser) {
    	
    	transformaSenhaDoUsuarioEmHash(systemUser);
        manager.persist(systemUser);
    }

	private void transformaSenhaDoUsuarioEmHash(SystemUser systemUser) {

		String salto = BCrypt.gensalt();
	    String senhaHashed = BCrypt.hashpw(systemUser.getSenha(), salto);
	    systemUser.setSenha(senhaHashed);
		
	}
	
	public SystemUser procuraSystemUser(SystemUser systemUser) {
		
	       TypedQuery<SystemUser> query= manager
	               .createQuery("select u from Usuario u where u.email=:email", SystemUser.class);
	           query.setParameter("email", systemUser.getEmail());

	           SystemUser systemUserRetornado = query.getResultList().stream().findFirst().orElse(null);
	           
	           if(validaSenhaDoSystemUserComOHashDoBanco(systemUser, systemUserRetornado)){
	               return systemUserRetornado;
	           }

	           return null;

	}

	private boolean validaSenhaDoSystemUserComOHashDoBanco(SystemUser systemUser, SystemUser systemUserRetornado) {

	    if(systemUserRetornado == null){
	        return false;
	    }
		
	    return BCrypt.checkpw(systemUser.getSenha(), systemUserRetornado.getSenha());
		
	}

}
