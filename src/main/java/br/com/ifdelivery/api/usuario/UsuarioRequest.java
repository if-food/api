package br.com.ifdelivery.api.usuario;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifdelivery.modelo.usuario.Usuario;
import br.com.ifdelivery.util.entity.Role;
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

    public Usuario Usuariobuild() {
        return Usuario.builder()
                .username(email)
                .password(senha)
                .roles(Arrays.asList(Usuario.ROLE_CLIENTE))
                .build();
    }
}