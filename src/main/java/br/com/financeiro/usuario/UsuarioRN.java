package br.com.financeiro.usuario;

import java.util.List;

import br.com.financeiro.categoria.CategoriaRN;
import br.com.financeiro.util.DAOFactory;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	
	public Usuario carregar(Integer codigo){
		return this.usuarioDAO.carregar(codigo);
	}
	
	public Usuario buscaPorLogin(String login){
		return this.usuarioDAO.buscaPorLogin(login);
	}
	
	public void salvar(Usuario usuario){
		Integer codigo = usuario.getCodigo();
		
		if (codigo == null || codigo==0){
			usuario.getPermissao().add("ROLE_USUARIO");
			this.usuarioDAO.salvar(usuario);
			
			CategoriaRN categoriaRN = new CategoriaRN();
			categoriaRN.salvaEstruturaPadrao(usuario);
		}else{
			this.usuarioDAO.atualizar(usuario);
		}
	}
	
	public void excluir(Usuario usuario){
		CategoriaRN categoriaRN = new CategoriaRN();
		categoriaRN.excluir(usuario);
		
		this.usuarioDAO.excluir(usuario);
	}
	
	public List<Usuario> listar(){
		return this.usuarioDAO.listar();
	}

}
