package br.com.ifdelivery.api.produto;

import br.com.ifdelivery.api.produto.dto.ProdutoDTO;
import br.com.ifdelivery.modelo.produto.Produto;
import br.com.ifdelivery.modelo.produto.ProdutoService;
import br.com.ifdelivery.modelo.restaurante.Restaurante;
import br.com.ifdelivery.modelo.restaurante.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Operation(summary = "Cadastrar um novo produto", description = "Endpoint responsável por cadastrar um novo produto")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> save(@RequestPart("data") ProdutoRequest request,
                                  @RequestPart("imageFile") MultipartFile imageFile) {
        try {

            Long restauranteId = request.getRestauranteId();

            Long categoriaId = request.getCategoriaId();

            Produto produtoNovo = produtoService.save(request.build(), restauranteId, categoriaId, imageFile);
            return ResponseEntity.ok(produtoNovo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todos os produtos", description = "Endpoint responsavel por listar todos os produtos")
    @GetMapping("/cardapio/{restauranteId}")
    public Map<String, List<ProdutoDTO>> obterCardapio(@PathVariable Long restauranteId) {
        return produtoService.listarCardapioRestaurante(restauranteId);
    }

    @Operation(summary = "Atualizar um produto", description = "Endpoint responsavel por atualizar um produto")
    @PutMapping("/")
    public ResponseEntity<Produto> update(@RequestParam Long produtoId,
                                                                      @RequestParam(required = false) Long categoriaId,
                                                                      @RequestBody ProdutoRequest request) {

        Produto produtoAtualizado =   produtoService.update(produtoId, categoriaId, request.build());

        return ResponseEntity.ok(produtoAtualizado);

    }

    @Operation(summary = "Deletar um produto", description = "Endpoint responsavel por deletar um produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
