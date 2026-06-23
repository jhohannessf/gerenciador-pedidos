package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNome(String nome);

    // Retorne todos os produtos com o nome exato fornecido.
    List<Produto> findByNome(String nome);

    // Retorne todos os produtos associados a uma categoria específica.
    List<Produto> findByCategoriaNomeContainingIgnoreCase(String categoriaNome);

    // Retorne produtos com preço maior que o valor fornecido.
    List<Produto> findByPrecoGreaterThan(Double preco);

    // Retorne produtos com preço menor que o valor fornecido.
    List<Produto> findByPrecoLessThan(Double preco);

    // Retorne produtos cujo nome contenha o termo especificado.
    List<Produto> findByNomeContaining(String nome);

    // Retorne produtos de uma categoria ordenados pelo preço de forma crescente.
    List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    // Retorne produtos de uma categoria ordenados pelo preço de forma decrescente.
    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);

    // Retorne a contagem de produtos em uma categoria específica.
    long countByCategoriaNome(String categoriaNome);

    //Retorne a contagem de produtos cujo preço seja maior que o valor fornecido.
    long countByPrecoGreaterThan(Double preco);

    // Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
    List<Produto> findByNomeContainingOrPrecoLessThan(String nome, double preco);

    // Retorne os três produtos mais caros.
    List<Produto> findTop3ByOrderByPrecoDesc();

    // Retorne os cinco produtos mais baratos de uma categoria.
    List<Produto> findTop5ByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

}
