package br.com.ifdelivery.api.produto;

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
    private String categoria;
    private String descricao;
    private String imagem;
    private Double valorUnitario;
    private Long restauranteId;

    public Produto build(Restaurante restaurante){
        return Produto.builder()
                .titulo(titulo)
                .categoria(categoria)
                .descricao(descricao)
                .imagem(imagem)
                .valorUnitario(valorUnitario)
                .restaurante(restaurante)
                .build();
    }
   }
