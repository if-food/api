package br.com.ifdelivery.modelo.produto;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto save(Produto produto) {
        System.out.println("entrou no serivce");
        Long ultimoProduto  = produtoRepository.findTopByOrderByIdDesc().getId();
        if (ultimoProduto == null) {
            produto.setCodigo(String.format("%010d", 1));
        } else {
            produto.setCodigo(String.format("%010d", ultimoProduto + 1));
        }
        produto.setHabilitado(Boolean.TRUE);
        produto.setVersao(1L);
        produto.setDataCriacao(LocalDate.now());
        return produtoRepository.save(produto);

    }

    public List<Produto> listarTodos() {

        return produtoRepository.findAll();
    }

    public Produto obterPorID(Long id) {

        return produtoRepository.findById(id).get();
    }

    @Transactional
    public void update(long id, Produto produtoAlterado){

        Produto produto = produtoRepository.findById(id).get();
        produto.setTitulo(produtoAlterado.getTitulo());
        produto.setDescricao(produtoAlterado.getDescricao());
        produto.setValorUnitario(produtoAlterado.getValorUnitario());
        produto.setCategoria(produtoAlterado.getCategoria());
        produto.setImagem(produtoAlterado.getImagem());
        produtoRepository.save(produto);
    }

    @Transactional
    public void delete(Long id) {

        Produto produto = produtoRepository.findById(id).get();
        produto.setHabilitado(Boolean.FALSE);
        produto.setVersao(produto.getVersao() + 1);

        produtoRepository.save(produto);
    }

    public List<Produto> listarCardapioRestaurante(Long restauranteId) {
        return produtoRepository.findByRestauranteId(restauranteId);
    }
}
