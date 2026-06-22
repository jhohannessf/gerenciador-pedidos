package br.com.alura.gerenciador_pedidos.service;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CategoriaService {

    // Declarar o repository
    private final CategoriaRepository categoriaRepository;

    // Injete a dependência
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria consultarSalvarCategoria(Categoria categoria) {
        return categoriaRepository.findByNome(categoria.getNome())
                .orElseGet(()-> categoriaRepository.save(categoria));
    }

    public void salvarCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria cadastrada com esse nome");
        }
        categoriaRepository.save(categoria);
    }

    public List<Produto> consultarProdutosPorCategoria(String nomeCategoria) {
        return categoriaRepository.findByNome(nomeCategoria)
                .map(categoria -> List.copyOf(categoria.getProdutos()))
                .orElse(Collections.emptyList()); // Devolve uma lista vazia, caso o Optional esteja vazio.
    }
}
