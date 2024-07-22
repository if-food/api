package br.com.ifdelivery.api.produto;

import br.com.ifdelivery.modelo.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {
    private String codigo;
    private String titulo;
    private String categoria;
    private String descricao;
    private String imagem;
    private Double valorUnitario;

    public Produto build(){
        return Produto.builder()
                .codigo(codigo)
                .titulo(titulo)
                .categoria(categoria)
                .descricao(descricao)
                .imagem(imagem)
                .valorUnitario(valorUnitario)
                .build();
    }
   }
