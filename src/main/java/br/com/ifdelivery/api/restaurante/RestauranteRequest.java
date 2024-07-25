package br.com.ifdelivery.api.restaurante;

import br.com.ifdelivery.modelo.restaurante.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteRequest {
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;

    public Restaurante build(){
        return Restaurante.builder()
                .razaoSocial(razaoSocial)
                .nomeFantasia(nomeFantasia)
                .cnpj(cnpj)
                .cep(cep)
                .estado(estado)
                .cidade(cidade)
                .bairro(bairro)
                .rua(rua)
                .numero(numero)
                .complemento(complemento)
                .build();
    }

}
