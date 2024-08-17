package br.com.ifdelivery.modelo.item_pedido;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public ItemPedido save(ItemPedido itemPedido) {
        itemPedido.setHabilitado(Boolean.TRUE);
        itemPedido.setVersao(1L);
        itemPedido.setDataCriacao(LocalDate.now());
        return itemPedidoRepository.save(itemPedido);
    }
}
