package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.online.loja.repositorio.RepositorioDeProdutos;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
	private final RepositorioDeProdutos produtos;
	//private final ProdutoDao produtos;
	private final Result result;
	
	// injetando dependencia do componente gerenciado pelo vRaptor - ProdutoDao
	public ProdutoController(Result result, RepositorioDeProdutos produto) {
		this.result = result;
		this.produtos = produto;
	}
	
	public void formulario(){
	}
	
	@Get public List<Produto> listaGet() { return new ProdutoDao().pegaTodos(); }
	
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
	
	// para executar no browser
	// http://localhost:8080/vraptor-produtos/produto/1/xml
	// enviando em xml
	// como estamos utilizando o results o proprio framework se encarrega de devolver o valor serializado
	@Path("/produto/{id}/xml")
	public void exibeComoXML(Long id){
		Produto produto = produtos.pegaPorId(id);
		this.result.use(Results.xml()).from(produto).serialize();
	}
	
	// para executar no browser
	// http://localhost:8080/vraptor-produtos/produto/1/xml
	// enviando em json
	@Path("/produto/{id}/json")
	public void exibeComoJSON(Long id){
		Produto produto = produtos.pegaPorId(id);
		this.result.use(Results.json()).from(produto).serialize();
	}
	
	@Post
	public void adiciona(Produto produto){
		produtos.salva(produto);
		// incluindo viewmodels para a jsp
		this.result.include("mensagem", "Novo produto adicionado com sucesso!!");
		// forcando um resultado variavel - caso ele nao seja informado a pagina de resposta
		// eh a pagina com o mesmo nome do metodo - no caso adiciona.jsp
		// na alteracao abaixo, forcamos a execucao para a pagina de lista;
		// no caso abaixo, ele redireciona a pagina, porem mantendo a url original
		// desta forma, mesmo redirecionando para a lista, ele mantem na url o endereco de adiciona
		//this.result.forwardTo(ProdutoController.class).lista();
		// para realizar o redirect como uma nova requisicao, alterando assim a url
		// como se fizessemos duas requisicoes diferentes
		this.result.redirectTo(ProdutoController.class).lista();
	}
}
