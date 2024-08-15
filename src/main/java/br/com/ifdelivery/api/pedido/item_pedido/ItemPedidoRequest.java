package br.com.ifdelivery.api.pedido.item_pedido;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoRequest {
    private Long produtoId;
    private Integer quantidade;
}
