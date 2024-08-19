package br.com.ifdelivery.modelo.categoria_produto;

import br.com.ifdelivery.modelo.restaurante.Restaurante;
import br.com.ifdelivery.modelo.restaurante.RestauranteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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


        public List<CategoriaProduto> listarPorRestaurante(Long restauranteId) {
            return categoriaProdutoRepository.findByRestauranteId(restauranteId);
        }


        public void delete(Long id) {

            Optional<CategoriaProduto> optionalCategoriaProduto = categoriaProdutoRepository.findById(id);

            if (optionalCategoriaProduto.isPresent()) {
                CategoriaProduto categoriaProduto = optionalCategoriaProduto.get();
                categoriaProduto.setHabilitado(Boolean.FALSE);
                categoriaProduto.setVersao(categoriaProduto.getVersao() + 1);
                categoriaProdutoRepository.save(categoriaProduto);
            } else {
                throw new EntityNotFoundException("CategoriaProduto não encontrada com  id: " + id);
            }
        }

        public CategoriaProduto update(Long id, CategoriaProduto categoriaProdutoAlterada) {

            Optional<CategoriaProduto> optionalCategoriaProduto = categoriaProdutoRepository.findById(id);

            if (optionalCategoriaProduto.isPresent()) {
                CategoriaProduto categoriaProduto = optionalCategoriaProduto.get();
                categoriaProduto.setNome(categoriaProdutoAlterada.getNome());
                categoriaProduto.setDescricao(categoriaProdutoAlterada.getDescricao());
                categoriaProdutoRepository.save(categoriaProduto);
                return categoriaProduto;
            } else {
                throw new EntityNotFoundException("CategoriaProduto não encontrada com  id: " + id);
            }
        }
}
