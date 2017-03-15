package br.com.financeiro.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;


import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.financeiro.categoria.Categoria;
import br.com.financeiro.categoria.CategoriaRN;

@ManagedBean(name = "categoriaBean")
@RequestScoped
public class CategoriaBean {
	
	private TreeNode 			categoriasTree;
	private Categoria			editada = new Categoria();
	private List<SelectItem>	categoriaSelect;
	private boolean				mostraEdicao = false;
	
	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextBean;
	
	public void novo(){
		Categoria pai = null;
		if (this.editada.getCodigo() != null){
			CategoriaRN categoriaRN = new CategoriaRN();
			pai = categoriaRN.carregar(this.editada.getCodigo());
		}
		
		this.editada = new Categoria();
		this.editada.setPai(pai);
		this.mostraEdicao = true;
	}
	
	public void selecionar(NodeSelectEvent event){
		this.editada = (Categoria) event.getTreeNode().getData();
		Categoria pai  = this.editada.getPai();
		if (this.editada != null && pai != null && pai.getCodigo() != null){
			this.mostraEdicao = true;
		} else {
			this.mostraEdicao = false;
		}		
	}
	
	public String salvar(){
		CategoriaRN categoriaRN = new CategoriaRN();
		this.editada.setUsuario(this.contextBean.getUsuarioLogado());
		categoriaRN.salvar(this.editada);
		
		this.editada 		= null;
		this.mostraEdicao 	= false;
		this.categoriasTree	= null;
		this.categoriaSelect= null;
		
		return null;		
	}
	
	public String excluir(){
		CategoriaRN categoriaRN = new CategoriaRN();
		categoriaRN.excluir(this.editada);
		
		this.editada 		= null;
		this.mostraEdicao 	= false;
		this.categoriasTree	= null;
		this.categoriaSelect= null;
		
		return null;
	}
	
	public TreeNode getCategoriasTree(){
		if (this.categoriasTree == null){
			CategoriaRN categoriaRN = new CategoriaRN();
			List<Categoria> categorias = categoriaRN.listar(this.contextBean.getUsuarioLogado());
			
			this.categoriasTree = new DefaultTreeNode(null, null);
			this.montaDadosTree(this.categoriasTree, categorias);
		}
		
		return this.categoriasTree;
	}
	
	private void montaDadosTree(TreeNode pai, List<Categoria> lista){
		if (lista != null && lista.size() > 0){
			TreeNode filho = null;
			for (Categoria categoria : lista ){
				filho = new DefaultTreeNode(categoria, pai);
				this.montaDadosTree(filho, categoria.getFilhos());
			}
		}
	}
	
	public List<SelectItem> getCategoriasSelect(){
		if (this.categoriaSelect == null){
			this.categoriaSelect = new ArrayList<SelectItem>();
			CategoriaRN categoriaRN = new CategoriaRN();
			
			List<Categoria> categorias = categoriaRN.listar(this.contextBean.getUsuarioLogado());
			this.montaDadosSelect(this.categoriaSelect, categorias, "");
		}
		
		return categoriaSelect;
	}
	
	private void montaDadosSelect(List<SelectItem> select, List<Categoria> categorias, String prefixo){
		SelectItem item  = null;
		if (categorias != null){
			for(Categoria categoria : categorias){
				item = new SelectItem(categoria, prefixo + categoria.getDescricao());
				item.setEscape(false);
				select.add(item);
				this.montaDadosSelect(select, categoria.getFilhos(), prefixo + "&nbsp;&nbsp;");
			}
		}
		
	}

	public Categoria getEditada() {
		return editada;
	}

	public void setEditada(Categoria editada) {
		this.editada = editada;
	}

	public List<SelectItem> getCategoriaSelect() {
		return categoriaSelect;
	}

	public void setCategoriaSelect(List<SelectItem> categoriaSelect) {
		this.categoriaSelect = categoriaSelect;
	}

	public boolean isMostraEdicao() {
		return mostraEdicao;
	}

	public void setMostraEdicao(boolean mostraEdicao) {
		this.mostraEdicao = mostraEdicao;
	}

	public ContextoBean getContextBean() {
		return contextBean;
	}

	public void setContextBean(ContextoBean contextBean) {
		this.contextBean = contextBean;
	}

	public void setCategoriasTree(TreeNode categoriasTree) {
		this.categoriasTree = categoriasTree;
	}
	
	
	

}
