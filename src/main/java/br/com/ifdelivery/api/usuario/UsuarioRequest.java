package br.com.ifdelivery.api.usuario;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifdelivery.modelo.usuario.Usuario;
import br.com.ifdelivery.util.exception.Role;
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
public class UsuarioRequest {

    private String email;

    private String senha;

    private Integer desconto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String telefone;

    private String cpf;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario build() {
        return Usuario.builder()
                .email(email)
                .senha(senha)
                .desconto(desconto)
                .dataNascimento(dataNascimento)
                .telefone(telefone)
                .cpf(cpf)
                .role(role)
                .build();
    }
}