package br.com.ifdelivery.api.produto;

import br.com.ifdelivery.modelo.categoria_produto.CategoriaProduto;
import br.com.ifdelivery.modelo.produto.Produto;
import br.com.ifdelivery.modelo.restaurante.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {
    private String titulo;
    private Long categoriaId    ;
    private String descricao;
    private Double valorUnitario;
    private Long restauranteId;

    public Produto build(){
        return Produto.builder()
                .titulo(titulo)
                .descricao(descricao)
                .valorUnitario(valorUnitario)
                .build();
    }
   }
