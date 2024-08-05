package br.com.ifdelivery.api.restaurante;

import br.com.ifdelivery.modelo.acesso.Usuario;
import br.com.ifdelivery.modelo.restaurante.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteRequest {

    private String email;
    private String password;
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


    public Usuario buildUsuario() {
        return Usuario.builder()
                .username(email)
                .password(password)
                .roles(Arrays.asList(Usuario.ROLE_RESTAURANTE))
                .build();
    }

    public Restaurante build(){
        return Restaurante.builder()
                .usuario(buildUsuario())
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
