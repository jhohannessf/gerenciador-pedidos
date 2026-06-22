package br.com.alura.gerenciador_pedidos.service;

import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service // Isso transforma sua classe em um Bean do Spring.
public class ProdutoService {

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
}
