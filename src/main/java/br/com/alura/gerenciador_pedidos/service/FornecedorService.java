package br.com.alura.gerenciador_pedidos.service;

import br.com.alura.gerenciador_pedidos.model.Fornecedor;
import br.com.alura.gerenciador_pedidos.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    // Declaração do repository
    private final FornecedorRepository fornecedorRepository;

    // Injeção de dependências usando o construtor
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public void salvarForncedor(Fornecedor forncedor) {
        fornecedorRepository.save(forncedor);
    }

}

