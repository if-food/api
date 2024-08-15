package br.com.ifdelivery.modelo.pedido;

import br.com.ifdelivery.modelo.item_pedido.ItemPedido;
import br.com.ifdelivery.modelo.item_pedido.ItemPedidoRepository;
import br.com.ifdelivery.modelo.pedido.enums.StatusEntregaEnum;
import br.com.ifdelivery.modelo.pedido.enums.StatusPgtoEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public Pedido salvar(Pedido pedido, List<ItemPedido> itens) {
        pedido.setHabilitado(Boolean.TRUE);
        pedido.setVersao(1L);
        pedido.setDataCriacao(LocalDate.now());
        pedido.setStatusEntrega(StatusEntregaEnum.PENDENTE);
        pedido.setStatusPgto(StatusPgtoEnum.PENDENTE);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for (ItemPedido item : itens) {
            item.setPedido(pedidoSalvo);
        }
        itemPedidoRepository.saveAll(itens);

        pedidoSalvo.setItens(itens);
        System.out.println(pedidoSalvo);
        return pedidoRepository.save(pedidoSalvo);
    }

    public Pedido atualizar(long id, Pedido pedidoAlterado) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        if (pedidoAlterado.getStatusEntrega() != null) {
            pedido.setStatusEntrega(pedidoAlterado.getStatusEntrega());
        }
        if (pedidoAlterado.getStatusPgto() != null) {
            pedido.setStatusPgto(pedidoAlterado.getStatusPgto());
        }

        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {

        Pedido pedido = pedidoRepository.findById(id).get();
        pedido.setHabilitado(Boolean.FALSE);
        pedido.setVersao(pedido.getVersao() + 1);

        pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Iterable<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }
}
