package br.com.alura.gerenciador_pedidos.model;

import jakarta.persistence.*;

@Entity // Informa o JPA que essa classe é uma tabela no banco
@Table(name = "produtos") // Informa o JPA que a tabela dessa classe terá outro nome no banco
public class Produto {
    @Id // Define a chave primária da tabela, identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID será gerado automaticamente. Auto Incremento do banco de dados.
    private Long id;
    @Column(nullable = false, unique = true) // A coluna não pode ser nula e o nome não pode repetir
    private String nome;
    @Column(name = "valor") // Informa o JPA que o nome da coluna no banco vai ser este passado e não o atributo da classe
    private double preco;

    // Cria uma Foreigh key na tabela produto com relacionamento n x 1
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    //Construtor padrão
    public Produto() {}

    public Produto(String nome, double preco, Categoria categoria,  Fornecedor fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                ", fornecedor=" + fornecedor;
    }
}
