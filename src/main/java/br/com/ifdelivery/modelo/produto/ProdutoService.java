package br.com.ifdelivery.modelo.produto;

import br.com.ifdelivery.api.produto.dto.ProdutoDTO;
import br.com.ifdelivery.modelo.categoria_produto.CategoriaProdutoService;
import br.com.ifdelivery.modelo.cliente.Cliente;
import br.com.ifdelivery.modelo.restaurante.RestauranteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @Transactional
    public Produto save(Produto produto, Long restauranteId, Long categoriaId, MultipartFile imageFile) throws IOException {

        try {
            produto.setRestaurante(restauranteService.obterPorRestauranteId(restauranteId));
        } catch (Exception e) {
            throw new IllegalArgumentException("Restaurante não encontrado");
        }
        try {
            produto.setCategoriaProduto(categoriaProdutoService.obterPorID(categoriaId));
        } catch (Exception e) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }
        if (!(imageFile.isEmpty())) {
            produto.setPhoto(imageFile.getBytes());
        }
        Produto ultimoProduto  = produtoRepository.findTopByOrderByIdDesc();
        if (ultimoProduto == null) {
            produto.setCodigo(String.format("%010d", 1));
        } else {
            produto.setCodigo(String.format("%010d", ultimoProduto.getId() + 1));
        }
        produto.setHabilitado(Boolean.TRUE);
        produto.setVersao(1L);
        produto.setDataCriacao(LocalDate.now());


        if (imageFile != null && !imageFile.isEmpty()) {
            produto.setPhoto(imageFile.getBytes());
        }

        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {

        return produtoRepository.findAll();
    }


    @Transactional
    public Produto update(Long id, Long categoriaId, Produto produtoAlterado) {
        // Find the existing product by ID
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        // Update product fields
        produto.setTitulo(produtoAlterado.getTitulo());
        produto.setDescricao(produtoAlterado.getDescricao());
        produto.setValorUnitario(produtoAlterado.getValorUnitario());
        produto.setImagem(produtoAlterado.getImagem());

        // Update category if needed (assuming CategoriaProduto is a relationship)
        if (categoriaId != null ) {
            try {
                produto.setCategoriaProduto(categoriaProdutoService.obterPorID(categoriaId));
            }
            catch (Exception e){
                throw new IllegalArgumentException("Erro ao setar categoria, verifique se a categoria está cadastrada");
            }

        }

        // Save the updated product
        produtoRepository.save(produto);
        return produto;
    }

    @Transactional
    public void delete(Long id) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        produto.setHabilitado(Boolean.FALSE);
        produto.setVersao(produto.getVersao() + 1);

        produtoRepository.save(produto);
    }
    @Transactional
    public Map<String, List<ProdutoDTO>> listarCardapioRestaurante(Long restauranteId) {
        List<Produto> produtos = produtoRepository.findByRestauranteId(restauranteId);
        return produtos.stream()
                .map(p -> ProdutoDTO.builder()
                        .id(p.getId())
                        .codigo(p.getCodigo())
                        .titulo(p.getTitulo())
                        .descricao(p.getDescricao())
                        .photo(p.getPhoto())
                        .valorUnitario(p.getValorUnitario())
                        .categoriaNome(p.getCategoriaProduto().getNome()) // Ou qualquer outro campo relevante
                        .build())
                .collect(Collectors.groupingBy(ProdutoDTO::getCategoriaNome));
    }

    @Transactional
    public void alterarImagem(Long produtoId, MultipartFile imageFile) throws IOException {
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            produto.setPhoto(imageFile.getBytes());
            produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Cliente not found");
        }
    }

}
