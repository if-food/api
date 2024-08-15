package br.com.ifdelivery.api.restaurante;

import br.com.ifdelivery.modelo.restaurante.CategoriasEnum;
import br.com.ifdelivery.modelo.restaurante.Restaurante;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private boolean isActive;
    private boolean isOpen;
    private boolean aceitaPix;
    private boolean aceitaCartaoCredito;
    private boolean aceitaCartaoDebito;
    private boolean aceitaDinheiro;
    private boolean aceitaValeRefeicao;
    private boolean aceitaValeAlimentacao;
    @Enumerated(EnumType.STRING)
    private CategoriasEnum categoria;

    public Restaurante build() {
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
                .isActive(isActive)
                .isOpen(isOpen)
                .aceitaPix(aceitaPix)
                .aceitaCartaoCredito(aceitaCartaoCredito)
                .aceitaCartaoDebito(aceitaCartaoDebito)
                .aceitaDinheiro(aceitaDinheiro)
                .aceitaValeRefeicao(aceitaValeRefeicao)
                .aceitaValeAlimentacao(aceitaValeAlimentacao)
                .categoriasEnum(categoria)
                .build();
    }

}
