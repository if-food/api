package br.com.ifdelivery.api.cliente;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifdelivery.api.usuario.UsuarioRequest;
import br.com.ifdelivery.modelo.cliente.Cliente;
import br.com.ifdelivery.modelo.usuario.Usuario;
import br.com.ifdelivery.util.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
	
	@NotBlank(message = "O preenchimento de Email é obrigatorio")
	@Email
    private String email;

	@NotBlank(message = "O preenchimento de senha é obrigatorio")
    private String senha;

    private String nome;
    
    private Integer desconto;
   
    private LocalDate dataNascimento;

    private String telefone;

    private String cpf;
    
    public Usuario Usuariobuild() {
        return Usuario.builder()
                .username(email)
                .password(senha)
                .roles(Arrays.asList(Usuario.ROLE_CLIENTE))
                .build();
    }
    
    public Cliente build() {
    	return Cliente.builder()
    			.usuario(Usuariobuild())
    			.nome(nome)
    			.email(email)
    			.senha(senha)
    			.cpf(cpf)
    			.dataNascimento(dataNascimento)
    			.telefone(telefone)
    			.desconto(desconto)
    			.build();
    }

}
