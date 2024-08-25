package br.com.ifdelivery.api.categoria_produto;

import br.com.ifdelivery.modelo.categoria_produto.CategoriaProduto;
import br.com.ifdelivery.modelo.categoria_produto.CategoriaProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categoria_produto")
public class CategoriaProdutoController {

    private final CategoriaProdutoService categoriaProdutoService;

    public CategoriaProdutoController(CategoriaProdutoService categoriaProdutoService) {
        this.categoriaProdutoService = categoriaProdutoService;
    }

    @PostMapping("/")
    public ResponseEntity<?> save (@RequestParam Long restauranteId,
                                                        @RequestBody CategoriaProdutoRequest request) {
        try {
            CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.build(), restauranteId);
            return ResponseEntity.ok(categoriaProduto);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarPorRestaurante(@RequestParam Long restauranteId) {
        List<CategoriaProduto> categorias = categoriaProdutoService.listarPorRestaurante(restauranteId);
        return ResponseEntity.ok(categorias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            categoriaProdutoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoriaProdutoRequest request) {
        try {
            CategoriaProduto categoriaProduto = categoriaProdutoService.update(id, request.build());
            return ResponseEntity.ok(categoriaProduto);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
