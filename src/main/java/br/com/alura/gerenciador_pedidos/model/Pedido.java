package br.com.alura.gerenciador_pedidos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;

    // Essa relação já é controlada pelo lado do Produto, não crie uma tabela nova aqui. Só reflita o relacionamento
//    @ManyToMany(mappedBy = "pedidos")
//    private List<Produto> produtos = new ArrayList<>();

    // Cria uma tabela intermediária automaticamente por ser n x n
    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    public Pedido() {}

    public Pedido(LocalDate data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
