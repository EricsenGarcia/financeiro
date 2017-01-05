package br.com.financeiro.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.financeiro.usuario.Usuario;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private String confirmaSenha;
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public String getConfirmaSenha() {
		return confirmaSenha;
	}



	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}



	public UsuarioBean() {		
		
	}

}
