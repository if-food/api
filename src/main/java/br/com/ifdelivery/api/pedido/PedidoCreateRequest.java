package br.com.ifdelivery.api.pedido;

import br.com.ifdelivery.modelo.cliente.Cliente;
import br.com.ifdelivery.modelo.item_pedido.ItemPedido;
import br.com.ifdelivery.modelo.pedido.Pedido;
import br.com.ifdelivery.modelo.pedido.enums.MetodosPgtoEnum;
import br.com.ifdelivery.modelo.restaurante.Restaurante;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCreateRequest{
    private Long idCliente;
    private Long idRestaurante;
    @Enumerated(EnumType.STRING)
    private MetodosPgtoEnum formaPgto;
    private String observacao;
    private List<ItemPedido> itens;

    public Pedido build(Restaurante restaurante, Cliente cliente) {
        return Pedido.builder()
                .restaurante(restaurante)
                .cliente(cliente)
                .metodoPagamento(formaPgto)
                .build();
    }
}
