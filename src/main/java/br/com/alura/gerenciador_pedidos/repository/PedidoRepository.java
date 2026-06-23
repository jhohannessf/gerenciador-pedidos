package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Retorne pedidos que ainda não possuem uma data de entrega.
    List<Pedido> findByDataIsNull();

    // Retorne pedidos com data de entrega preenchida.
    List<Pedido> findByDataIsNotNull();

    // Retorne pedidos feitos após uma data específica.
    List<Pedido> findByDataAfter(LocalDate data);

    // Retorne pedidos feitos antes de uma data específica.
    List<Pedido> findByDataBefore(LocalDate data);

    // Retorne pedidos feitos em um intervalo de datas.
    List<Pedido> findByDataBetween(LocalDate data1, LocalDate data2);

}
