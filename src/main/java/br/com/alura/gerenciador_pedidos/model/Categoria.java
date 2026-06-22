package br.com.alura.gerenciador_pedidos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;

    // O relacionamento já é controlado pelo atributo categoria dentro de Produto
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) //Define que as operações feitas na Categoria serão propagadas para os Produto.
    private List<Produto> produtos = new ArrayList<>();

    // Construtor padrão obrigatório pelo JPA
    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
