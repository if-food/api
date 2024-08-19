package br.com.ifdelivery.api.produto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProdutoDTO {
    private Long id;
    private String codigo;
    private String titulo;
    private String descricao;
    private String imagem;
    private Double valorUnitario;
    @JsonIgnore
    private String categoriaNome;
}
