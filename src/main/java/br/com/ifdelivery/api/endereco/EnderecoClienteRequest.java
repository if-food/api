package br.com.ifdelivery.api.endereco;

import br.com.ifdelivery.modelo.endereco.EnderecoCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoClienteRequest {

   private String cep;

   private String estado;

   private String cidade;

   private String bairro;

   private String rua;

   private String numero;
   
   private String complemento;

   private String tipo;

   public EnderecoCliente build() {

       return EnderecoCliente.builder()
               .cep(cep)
               .estado(estado)
               .cidade(cidade)
               .bairro(bairro)
               .rua(rua)
               .numero(numero)
               .complemento(complemento)
               .tipo(tipo)
               .build();
   }
}


