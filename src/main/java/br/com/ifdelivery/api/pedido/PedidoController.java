package br.com.ifdelivery.api.pedido;

import br.com.ifdelivery.api.pedido.item_pedido.ItemPedidoRequest;
import br.com.ifdelivery.api.produto.ProdutoRequest;
import br.com.ifdelivery.modelo.pedido.Pedido;
import br.com.ifdelivery.modelo.pedido.PedidoService;
import br.com.ifdelivery.modelo.produto.ProdutoService;
import br.com.ifdelivery.modelo.restaurante.RestauranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;


    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> save (@RequestParam Long clienteId,
                                        @RequestParam Long restauranteId,
                                        @RequestBody List<ItemPedidoRequest> request) {

        Pedido pedido = pedidoService.criarPedido(clienteId,restauranteId,request);
        return  ResponseEntity.ok(pedido);
    }

}
