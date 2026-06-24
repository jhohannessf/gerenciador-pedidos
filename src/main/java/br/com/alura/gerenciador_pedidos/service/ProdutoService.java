package br.com.alura.gerenciador_pedidos.service;

import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service // Isso transforma sua classe em um Bean do Spring.
public class ProdutoService {

    List<Produto> listaProdutos;

    // Declaração do Repository
    private final ProdutoRepository produtoRepository;

    // Inicializando o contrutor passando o Repository = injetando dependências
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void salvarProduto(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new IllegalArgumentException("O produto com este nome já existe.");
        }
        produtoRepository.save(produto);
    }

    // Usando Query Method ou Derived Queries
    public void buscarPorNome(String nome) {
        List<Produto> listaProduto = produtoRepository.findByNomeContaining(nome);
        if (listaProduto.isEmpty()) {
            System.out.println("Produto não encontrado.");
        } else {
            listaProduto.forEach(produto -> System.out.println("Lista de produtos com nome pesquisado: " + produto.getNome()));
        }
    }

    public void buscarPorCategoria(String nome) {
        List<Produto> listaProdutoPorCategoria = produtoRepository.findByCategoriaNomeContainingIgnoreCase(nome);
        if (listaProdutoPorCategoria.isEmpty()) {
            System.out.println("Categoria não encontrada.");
        } else {
            listaProdutoPorCategoria.forEach(produto -> {
                System.out.println("Lista de produtos com categoria " + nome + ": " + produto.getNome());
            });
        }
    }

    public void buscarPorMaiorPreco(Double preco) {
        if (preco > 0) {
            listaProdutos = produtoRepository.findByPrecoGreaterThan(preco);
            listaProdutos.forEach(produto -> {
                System.out.println("Produto com preço maior que: " + preco + " - " + produto.getNome() + " - " + produto.getPreco());
            });
        } else {
            System.out.println("Preço do produto é inválido");
        }
    }

    public void buscarPorMenorPreco(Double preco) {
        if (preco > 0) {
            listaProdutos = produtoRepository.findByPrecoLessThan(preco);
            listaProdutos.forEach(produto -> {
                System.out.println("Produto com preço menor que: " + preco + " - " + produto.getNome() + " - " + produto.getPreco());
            });
        } else {
            System.out.println("Preço do produto é inválido");
        }
    }

    public void consultarProdutosDeCategoriaPeloMenorPreco(String nomeCategoria) {
        produtoRepository.findByCategoriaNomeOrderByPrecoAsc(nomeCategoria)
                .forEach(c -> System.out.println("Produto com categoria: " + nomeCategoria + " - " + c.getNome()));

    }

    public void consultarProdutosDeCategoriaPeloMaiorPreco(String nomeCategoria) {
        produtoRepository.findByCategoriaNomeOrderByPrecoDesc(nomeCategoria)
                .forEach(c -> System.out.println("Produto com categoria: " + nomeCategoria + " - " + c.getNome()));

    }

    public void contarProdutosEmCategoria(String nomeCategoria) {
        long contagem = produtoRepository.countByCategoriaNome(nomeCategoria);
        System.out.println("Contagem de produtos na categoria: " + nomeCategoria + " - " + contagem);
    }

    public void contarProdutosEmPreco(Double preco) {
        long contagem = produtoRepository.countByPrecoGreaterThan(preco);
        System.out.println("Contagem de produtos com preço maior que: " + preco + " - " + contagem);
    }

    public List<Produto> buscaNomeProdutosOuComPrecoMenor(String nome, Double preco) {
        return produtoRepository.findByNomeContainingOrPrecoLessThan(nome, preco);
    }

    public List<Produto> buscaTop3ProdutosPorPreco() {
        return produtoRepository.findTop3ByOrderByPrecoDesc();
    }

    public List<Produto> buscaTop5ProdutosPorCategoria(String nomeCategoria) {
        return produtoRepository.findTop5ByCategoriaNomeOrderByPrecoAsc(nomeCategoria);
    }


    // Usando JPQL
    public List<Produto> buscaProdutoMaiorValor(double valor) {
        listaProdutos = produtoRepository.buscaProdutoMaiorValor(valor);
        listaProdutos.forEach(produto -> {
            System.out.println(produto.getNome());
        });
        return listaProdutos;
    }

    public List<Produto> buscaOrdenadaPorPrecoAsc() {
        listaProdutos = produtoRepository.buscaOrdenadaPorPrecoAsc();
        listaProdutos.forEach(produto ->
                System.out.printf("Produto: %s - Preço: %.2f%n",
                        produto.getNome(), produto.getPreco())
        );
        return listaProdutos;
    }

    public List<Produto> buscaOrdenadaPorPrecoDesc() {
        listaProdutos = produtoRepository.buscaOrdenadaPorPrecoDesc();
        listaProdutos.forEach(produto ->
                System.out.printf("Produto: %s - Preço: %.2f%n",
                        produto.getNome(), produto.getPreco())
        );
        return listaProdutos;
    }

    public List<Produto> buscaProdutoComecaLetra(String letra) {
        listaProdutos = produtoRepository.buscaProdutoComecaLetra(letra);
        listaProdutos.forEach(produto ->
                System.out.printf("Produto: %s%n",
                        produto.getNome())
        );
        return listaProdutos;
    }

    public void consultaMediaPrecosPorProduto() {
        double media = produtoRepository.consultaMediaPrecosPorProduto();

        // Se fosse fazer usando Lista
//        double media = listaProdutos.stream()
//                .mapToDouble(Produto::getPreco) //extrai o preço de cada produto
//                .average() //calcula a média
//                .orElse(0); //caso a lista esteja vazia
        System.out.printf("Media da produto: %.2f%n", media);
    }

    public void buscarPrecoMaximoPorCategoria(String nomeCategoria) {
        Double precoMaximo = produtoRepository.buscarPrecoMaximoPorCategoria(nomeCategoria);
        System.out.println("Preco Maximo: " + precoMaximo);
    }

    public void contarNumeroProdutoPorCategoria() {
        List<Object[]> contagemProdutos = produtoRepository.contarNumeroProdutoPorCategoria(); // Object[] :  É um array que pode guardar QUALQUER tipo de objeto.
        contagemProdutos.forEach(produto -> {
            System.out.println(produto[0] + " - " + produto[1]);
        });
    }

    public void filtrarCategoriaComMaisde10Produtos(long quantidade) {
        List<Object[]> contagemProdutos = produtoRepository.filtrarCategoriaComMaisde10Produtos(quantidade); // Object[] :  É um array que pode guardar QUALQUER tipo de objeto.
        contagemProdutos.forEach(produto -> {
            System.out.println(produto[0] + " - " + produto[1]);
        });
    }

    public List<Produto> buscarProdutosFiltrados(String nome, String categoria) {
        listaProdutos = produtoRepository.buscarProdutosFiltrados(nome, categoria);
        listaProdutos.forEach(produto ->
                System.out.printf("Produto: %s | Categoria: %s%n",
                        produto.getNome(), produto.getCategoria() != null
                                ? produto.getCategoria().getNome()
                                : "Sem categoria")
        );
        return listaProdutos;

    }

    public List<Produto> buscarTop5ProdutosMaisCaros() {
        listaProdutos = produtoRepository.buscarTop5ProdutosMaisCaros();
        listaProdutos.forEach(produto ->
                System.out.printf("Produto: %s - Preço: %.2f%n",
                        produto.getNome(), produto.getPreco())
        );
        return listaProdutos;
    }

}

