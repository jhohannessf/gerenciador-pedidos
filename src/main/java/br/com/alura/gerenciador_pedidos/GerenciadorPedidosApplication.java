package br.com.alura.gerenciador_pedidos;

import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.service.CategoriaService;
import br.com.alura.gerenciador_pedidos.service.FornecedorService;
import br.com.alura.gerenciador_pedidos.service.PedidoService;
import br.com.alura.gerenciador_pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {

	// Spring injeta o ProdutoService
	@Autowired
	private ProdutoService produtoService;

	// Spring injeta a CategoriaService
	@Autowired
	private CategoriaService categoriaService;

	// Spring injeta o PedidoService
	@Autowired
	private PedidoService pedidoService;

	// Spring injeta o FornecedorService
	@Autowired
	private FornecedorService fornecedorService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);
	}


	@java.lang.Override
	public void run(java.lang.String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

//		System.out.println("Digite o nome do produto: ");
//		String nome = sc.nextLine();
//
//		System.out.println("Digite o valor do produto: ");
//		double preco = sc.nextDouble();
//		sc.nextLine(); // limpa o buffer
//
//		if (nome.isBlank()) {
//			System.out.println("Nome do produto não pode ser vazio ou nulo.");
//			return;
//		}
//		if (preco <= 0) {
//			System.out.println("Valor do produto não pode ser menor ou igual a zero");
//			return;
//		}
//
//		System.out.println("Informe o nome da Categoria: ");
//		String nomeCategoria = sc.nextLine();
//		if (nomeCategoria.isBlank()){
//			System.out.println("Nome da categoria não pode ser vazio ou nulo.");
//			return;
//		}
//
//		// Salvando a Categoria
//		Categoria categoria = new Categoria(nomeCategoria);
//		categoria = categoriaService.consultarSalvarCategoria(categoria);
//
//		// Salvando o Fornecedor
//		Fornecedor fornecedor =  new Fornecedor("Pichau");
//		fornecedorService.salvarForncedor(fornecedor);
//
//		// Salvando o Produto
//		Produto produto = new Produto(nome, preco, categoria, fornecedor);
//		produtoService.salvarProduto(produto);
//
		// Salvando um pedido sem data
//		Pedido pedido = new Pedido();
//		pedido.setProdutos(List.of(produto));
//		pedidoService.salvarPedido(pedido);

		// Salvando um pedido com data
//		Pedido pedido1 = new Pedido(LocalDate.now());
//		pedido1.setProdutos(List.of(produto));
//		pedidoService.salvarPedido(pedido1);
//		//Pedido pedido2 = new Pedido(LocalDate.now().minusDays(1));
//		//pedido2.setProdutos(List.of(produto));
//		//pedidoService.salvarTodos(List.of(pedido1, pedido2));

		// Retornando produtos com nome fornecido
//		System.out.println("Informe o nome do produto que deseja buscar: ");
//		var nomeProduto = sc.nextLine();
//		produtoService.buscarPorNome(nomeProduto);

		// Retorne todos os produtos associados a uma categoria específica.
//		System.out.println("Informe o nome da categoria que deseja buscar: ");
//		var nomeCategoriaProduto = sc.nextLine();
//		produtoService.buscarPorCategoria(nomeCategoriaProduto);

		// Retorne produtos com preço maior que o valor fornecido.
//		System.out.println("Informe o preço do Produto que deseja buscar: ");
//		var precoMaiorProduto = sc.nextDouble();
//		produtoService.buscarPorMaiorPreco(precoMaiorProduto);
//
//		// Retorne produtos com preço menor que o valor fornecido.
//		System.out.println("Informe o preço do Produto que deseja buscar: ");
//		var precoMenorProduto = sc.nextDouble();
//		produtoService.buscarPorMenorPreco(precoMenorProduto);

		// Retorne pedidos que ainda não possuem uma data de entrega.
//		pedidoService.pedidosSemData();

		// Retorne pedidos que possuem uma data de entrega.
//		pedidoService.pedidoComData();

		// Retorne produtos de uma categoria ordenados pelo preço de forma crescente.
//		produtoService.consultarProdutosDeCategoriaPeloMenorPreco("Tecnologia");

		// Retorne produtos de uma categoria ordenados pelo preço de forma decrescente.
//		produtoService.consultarProdutosDeCategoriaPeloMaiorPreco("Tecnologia");

		// Retorne a contagem de produtos em uma categoria específica.
//		produtoService.contarProdutosEmCategoria("Tecnologia");

		// Retorne a contagem de produtos cujo preço seja maior que o valor fornecido.
//		produtoService.contarProdutosEmPreco(500.00);

		// Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
//		List<Produto> produtos = produtoService.buscaNomeProdutosOuComPrecoMenor("Teclado", 400.00);
//		produtos.forEach(produto -> {
//			System.out.println(produto.getNome());
//			System.out.println(produto.getPreco());
//		});

		// Retorne os três produtos mais caros.
//		List<Produto> produtos = produtoService.buscaTop3ProdutosPorPreco();
//		produtos.forEach(produto -> {
//			System.out.println(produto.getNome());
//		});

		// Retorne os cinco produtos mais baratos de uma categoria.
		List<Produto> produtos1 = produtoService.buscaTop5ProdutosPorCategoria("Tecnologia");
		produtos1.forEach(produto -> {
			System.out.println(produto.getNome());
		});


		sc.close();

	}
}
