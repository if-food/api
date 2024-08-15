package br.com.ifdelivery.api.restaurante;

import br.com.ifdelivery.modelo.restaurante.Restaurante;
import br.com.ifdelivery.modelo.restaurante.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurante")
@CrossOrigin
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @Operation(summary = "Cadastrar um restaurante", description = "Endpoint responsável por cadastrar um restaurante")
    @PostMapping
    public ResponseEntity<Restaurante> save(@RequestBody @Valid RestauranteRequest request) {

        Restaurante restaurante = restauranteService.save(request.build());

        return new ResponseEntity<Restaurante>(restaurante, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os restaurantes", description = "Endpoint responsável por listar todos os restaurantes")
    @GetMapping
    public List<Restaurante> listarTodos() {
        return restauranteService.listarTodos();
    }

    @Operation(summary = "Obter um restaurante por ID", description = "Endpoint responsável por obter um restaurante por ID")
    @GetMapping("/{id}")
    public Restaurante obterPorID(@PathVariable Long id) {
        return restauranteService.obterPorID(id);
    }

    @Operation(summary = "Atualizar um restaurante", description = "Endpoint responsável por atualizar um restaurante")
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> update(@PathVariable("id") Long id, @RequestBody RestauranteRequest request) {

        restauranteService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Deletar um restaurante", description = "Endpoint responsável por deletar um restaurante")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        restauranteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
