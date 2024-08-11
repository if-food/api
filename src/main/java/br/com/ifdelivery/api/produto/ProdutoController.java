package br.com.ifdelivery.api.produto;

import br.com.ifdelivery.modelo.produto.Produto;
import br.com.ifdelivery.modelo.produto.ProdutoService;
import br.com.ifdelivery.modelo.restaurante.Restaurante;
import br.com.ifdelivery.modelo.restaurante.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin
public class ProdutoController {

    private final ProdutoService produtoService;
    private final RestauranteService restauranteService;

    public ProdutoController(ProdutoService produtoService, RestauranteService restauranteService) {
        this.produtoService = produtoService;
        this.restauranteService = restauranteService;
    }

    @Operation(summary = "Cadastrar um novo produto", description = "Endpoint responsavel por cadastrar um novo produto")
    @PostMapping
    public ResponseEntity<Produto> save (@RequestBody ProdutoRequest request) {
        try {
            Restaurante restaurante = restauranteService.obterPorID(request.getRestauranteId());
            if (restaurante == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            Produto produtoNovo = produtoService.save(request.build(restaurante));
            return new ResponseEntity<>(produtoNovo, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Listar todos os produtos", description = "Endpoint responsavel por listar todos os produtos")
    @GetMapping("/cardapio/{restauranteId}")
    public List<Produto> obterCardapio(@PathVariable Long restauranteId) {
        return produtoService.listarCardapioRestaurante(restauranteId);
    }

//    @Operation(summary = "Listar um produto", description = "Endpoint responsavel por listar um produto")
//    @GetMapping("/{id}")
//    public Produto obterPorID(@PathVariable Long id) {
//        return produtoService.obterPorID(id);
//    }

    @Operation(summary = "Atualizar um produto", description = "Endpoint responsavel por atualizar um produto")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody ProdutoRequest request) {
        try {
            Restaurante restaurante = restauranteService.obterPorID(request.getRestauranteId());
            if (restaurante == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            produtoService.update(id, request.build(restaurante));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Deletar um produto", description = "Endpoint responsavel por deletar um produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
