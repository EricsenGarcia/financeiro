package br.com.financeiro.util;

import br.com.financeiro.categoria.CategoriaDAO;
import br.com.financeiro.categoria.CategoriaDAOHibernate;
import br.com.financeiro.conta.*;
import br.com.financeiro.usuario.*;
public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionfactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static ContaDAO criarContaDAO(){
		ContaDAOHibernate contaDao = new ContaDAOHibernate();
		contaDao.setSession(HibernateUtil.getSessionfactory().getCurrentSession());
		return contaDao;
	}
	
	public static CategoriaDAO criarCategoriaDAO(){
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionfactory().getCurrentSession());
		return categoriaDAO;
	}

}
