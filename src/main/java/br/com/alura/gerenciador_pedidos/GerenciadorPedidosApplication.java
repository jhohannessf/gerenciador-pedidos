package br.com.alura.gerenciador_pedidos;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Fornecedor;
import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.service.CategoriaService;
import br.com.alura.gerenciador_pedidos.service.FornecedorService;
import br.com.alura.gerenciador_pedidos.service.PedidoService;
import br.com.alura.gerenciador_pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
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

		System.out.println("Digite o nome do produto: ");
		String nome = sc.nextLine();

		System.out.println("Digite o valor do produto: ");
		double preco = sc.nextDouble();
		sc.nextLine(); // limpa o buffer

		if (nome.isBlank()) {
			System.out.println("Nome do produto não pode ser vazio ou nulo.");
			return;
		}
		if (preco <= 0) {
			System.out.println("Valor do produto não pode ser menor ou igual a zero");
			return;
		}

		System.out.println("Informe o nome da Categoria: ");
		String nomeCategoria = sc.nextLine();
		if (nomeCategoria.isBlank()){
			System.out.println("Nome da categoria não pode ser vazio ou nulo.");
			return;
		}

		// Salvando a Categoria
		Categoria categoria = new Categoria(nomeCategoria);
		categoria = categoriaService.consultarSalvarCategoria(categoria);

		// Salvando o Fornecedor
		Fornecedor fornecedor =  new Fornecedor("Pichau");
		fornecedorService.salvarForncedor(fornecedor);

		// Salvando o Produto
		Produto produto = new Produto(nome, preco, categoria, fornecedor);
		produtoService.salvarProduto(produto);

		// Salvando um pedido
		Pedido pedido1 = new Pedido(LocalDate.now());
		pedido1.setProdutos(List.of(produto));
		pedidoService.salvarPedido(pedido1);
		//Pedido pedido2 = new Pedido(LocalDate.now().minusDays(1));
		//pedido2.setProdutos(List.of(produto));
		//pedidoService.salvarTodos(List.of(pedido1, pedido2));


		sc.close();

	}
}
