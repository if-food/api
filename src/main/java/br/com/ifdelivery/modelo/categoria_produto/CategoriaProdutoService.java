package br.com.ifdelivery.modelo.categoria_produto;

import br.com.ifdelivery.modelo.restaurante.Restaurante;
import br.com.ifdelivery.modelo.restaurante.RestauranteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CategoriaProdutoService {

        private final CategoriaProdutoRepository categoriaProdutoRepository;
        private final RestauranteService restauranteService;

        public CategoriaProdutoService(CategoriaProdutoRepository categoriaProdutoRepository, RestauranteService restauranteService) {
            this.categoriaProdutoRepository = categoriaProdutoRepository;
            this.restauranteService = restauranteService;
        }

        public CategoriaProduto save(CategoriaProduto categoriaProduto, Long restauranteId) {

            Restaurante restaurante = restauranteService.obterPorID(restauranteId);
            if (restaurante == null) {
                throw new RuntimeException("Restaurante não encontrado");
            }
            categoriaProduto.setRestaurante(restaurante);
            categoriaProduto.setHabilitado(Boolean.TRUE);
            categoriaProduto.setVersao(1L);
            categoriaProduto.setDataCriacao(LocalDate.now());
            return categoriaProdutoRepository.save(categoriaProduto);
        }

        public CategoriaProduto obterPorID(Long id) {
            return categoriaProdutoRepository.findById(id).orElse(null);
        }

        //to do - implementar listagem de categorias por restaurante e deleção de categoria

}
