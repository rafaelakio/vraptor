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
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
	private final RepositorioDeProdutos produtos;
	//private final ProdutoDao produtos;
	private final Result result;
	private Validator validator;
	
	// injetando dependencia do componente gerenciado pelo vRaptor - ProdutoDao
	public ProdutoController(Result result, RepositorioDeProdutos produto, Validator validator) {
		this.result = result;
		this.produtos = produto;
		this.validator = validator;
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
	public void adiciona(final Produto produto){
		
		// se quiser fazer a validacao por javabean, podemos utilizar o checking
		// as mensagens nesse caso, sao configuradas no arquivo messages.properties
		validator.checking(new Validations() {
			{
				that(produto.getNome().trim().equals(""), "erro", "produto.nome.invalido");
			}
		});
		
		// realizando validacoes com internacionalizacao
		if (produto.getDescricao().trim().equals("")) {
			validator.add(new I18nMessage("descricao", "produto.descricao.invalido"));
		}
		
		// validação de dados da tela;
		if (produto.getPreco() < 0.1) {
			validator.add(new ValidationMessage("O preço deve ser maior do que R$ 0,10", "preco"));
		}
		// se algum erro ocorrer, o validator pode redicionar o fluxo para outra request
		validator.onErrorUsePageOf(ProdutoController.class).formulario();
		
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
	
	public void remove(Produto produto) {
		produtos.remove(produto);
		// informa que não quero renderizar nada como resposta desse metodo
		result.nothing();
	}
}
