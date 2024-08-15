package br.com.ifdelivery.modelo.pedido;

import br.com.ifdelivery.api.pedido.item_pedido.ItemPedidoRequest;
import br.com.ifdelivery.modelo.cliente.Cliente;
import br.com.ifdelivery.modelo.cliente.ClienteRepository;
import br.com.ifdelivery.modelo.item_pedido.ItemPedido;
import br.com.ifdelivery.modelo.item_pedido.ItemPedidoRepository;
import br.com.ifdelivery.modelo.pedido.enums.StatusEntregaEnum;
import br.com.ifdelivery.modelo.pedido.enums.StatusPgtoEnum;
import br.com.ifdelivery.modelo.produto.Produto;
import br.com.ifdelivery.modelo.produto.ProdutoRepository;
import br.com.ifdelivery.modelo.restaurante.Restaurante;
import br.com.ifdelivery.modelo.restaurante.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository,ProdutoRepository produtoRepository,ClienteRepository clienteRepository, RestauranteRepository restauranteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.clienteRepository = clienteRepository;
        this.restauranteRepository = restauranteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Pedido criarPedido(Long clienteId, Long restauranteId, List<ItemPedidoRequest> itens) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setHabilitado(Boolean.TRUE);
        pedido.setVersao(1L);
        pedido.setDataCriacao(LocalDate.now());

        // Salva o pedido no banco de dados
        pedido = pedidoRepository.save(pedido);

        for (ItemPedidoRequest itemPedidoRequest : itens){
            Produto produto = produtoRepository.findById(itemPedidoRequest.getProdutoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemPedidoRequest.getQuantidade());

            itemPedidoRepository.save(itemPedido);
            }
        return pedido;
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
