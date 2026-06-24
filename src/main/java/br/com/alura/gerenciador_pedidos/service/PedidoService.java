package br.com.alura.gerenciador_pedidos.service;

import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    List<Pedido> listaPedidos;

    //Declarando o pedidoRepository
    private PedidoRepository pedidoRepository;

    //Injetando a depedência do pedidoRepository no método construtor
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void salvarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public void salvarTodos(List<Pedido> pedidos) {
        pedidoRepository.saveAll(pedidos);
    }

    public void pedidosSemData() {
        pedidoRepository.findByDataIsNull().forEach(p -> {
            System.out.println("Id do(s) pedido(s) sem data: " + p.getId());
        });
    }

    public void pedidoComData() {
        pedidoRepository.findByDataIsNotNull().forEach(p -> {
            System.out.println("Id do(s) pedido(s) com data: " + p.getId() + " - " +  p.getData());
        });
    }

    public List<Pedido> buscaPedidosEntreDatas(LocalDate inicio, LocalDate fim) {
        listaPedidos = pedidoRepository.buscaPedidosEntreDatas(inicio, fim);
        listaPedidos.forEach(p ->
                System.out.printf("Pedido: %d - Data: %s%n",
                        p.getId(), p.getData())
        );
        return listaPedidos;
    }

}
