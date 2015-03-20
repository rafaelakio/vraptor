package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

@Resource
public class ProdutoController {
	
	private final ProdutoDao produtos;

	public ProdutoController() {
		this.produtos = new ProdutoDao();
	}
	
	public void formulario(){
	}
	
	@Get public List<Produto> listaGet() { return new ProdutoDao().pegaTodos(); }
	
	@Post
	public void adiciona(Produto produto){
		produtos.salva(produto);
	}
	
	// para executar no browser
	// http://localhost:8080/vraptor-produtos/produto/lista
	public List<Produto> lista() {
		return new ProdutoDao().pegaTodos();
	}
	
	// para executar no browser
	// http://localhost:8080/vraptor-produtos/produto/exibeQueryParam?id=1
	public Produto exibeQueryParam(Long id){
		return produtos.pegaPorId(id);
	}
	
	// para executar no browser
	// http://localhost:8080/vraptor-produtos/produto/1
	@Path("/produto/{id}")
	public Produto exibe(Long id){
		return produtos.pegaPorId(id);
	}
}
