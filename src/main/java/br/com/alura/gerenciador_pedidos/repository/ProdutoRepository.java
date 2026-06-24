package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

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


    // Usando JPQL

    // Crie uma consulta que retorne os produtos com preço maior que um valor
    @Query("SELECT p FROM Produto p WHERE p.preco > :valor")
    List<Produto> buscaProdutoMaiorValor(@Param("valor") Double valor);

    // Crie uma consulta que retorne os produtos ordenados pelo preço crescente.
    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC")
    List<Produto> buscaOrdenadaPorPrecoAsc();

    // Crie uma consulta que retorne os produtos ordenados pelo preço decrescente.
    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> buscaOrdenadaPorPrecoDesc();

    // Crie uma consulta que retorne os pedidos feitos entre duas datas.
    @Query("SELECT p FROM Produto p WHERE p.nome ILIKE :letra% ")
    List<Produto> buscaProdutoComecaLetra(String letra);

    // Crie uma consulta que retorne a média de preços dos produtos.
    @Query("SELECT AVG(p.preco) FROM Produto p")
    Double consultaMediaPrecosPorProduto();

    // Crie uma consulta que retorne o preço máximo de um produto em uma categoria
    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome = :categoria")
    Double buscarPrecoMaximoPorCategoria(@Param("categoria") String categoria);

    // Crie uma consulta para contar o número de produtos por categoria.
    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome")
    List<Object[]> contarNumeroProdutoPorCategoria();

    // Crie uma consulta para filtrar categorias com mais de 10 produtos.
    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome HAVING COUNT(p) > :quantidade")
    List<Object[]> filtrarCategoriaComMaisde10Produtos(@Param("quantidade") long quantidade);

    // Crie uma consulta para retornar os produtos filtrados por nome ou por categoria.
    @Query("SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) OR (:categoria IS NULL OR p.categoria.nome = :categoria)")
    List<Produto> buscarProdutosFiltrados(@Param("nome") String nome, @Param("categoria") String categoria);

    // Crie uma consulta NATIVA para buscar os cinco produtos mais caros
    @Query(value = "SELECT * FROM produtos p ORDER BY valor desc LIMIT 5", nativeQuery = true)
    List<Produto> buscarTop5ProdutosMaisCaros();


}
