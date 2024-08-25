package br.com.ifdelivery.api.pedido;

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
    public ResponseEntity<?> save (@RequestParam Long clienteId,
                                        @RequestParam Long restauranteId,
                                        @RequestBody PedidoCreateRequest request) {

        try {
            Pedido pedido = pedidoService.criarPedido(clienteId,restauranteId,request.build(),request.getItens());
            return  ResponseEntity.ok(pedido);
        }
        catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public List<Pedido> obterPedidos(@RequestParam(required = false) Long clienteId, @RequestParam(required = false) Long restauranteId) {
        if (clienteId != null) {
            return pedidoService.listarPorCliente(clienteId);
        } else if (restauranteId != null) {
            return pedidoService.listarPorRestaurante(restauranteId);
        } else {
            throw new IllegalArgumentException("clienteId or restauranteId devem ser fornecidos");
        }
    }

}
