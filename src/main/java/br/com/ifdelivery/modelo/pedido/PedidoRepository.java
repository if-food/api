package br.com.ifdelivery.modelo.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //buscar pedido por id do cliente
    List<Pedido> findByClienteId(Long clienteId);

    //buscar pedido por id do restaurante
    List<Pedido> findByRestauranteId(Long restauranteId);
}
