package br.com.ifdelivery.modelo.usuario;

import br.com.ifdelivery.modelo.endereco.Endereco;
import br.com.ifdelivery.modelo.entrega.Entrega;
import br.com.ifdelivery.util.entity.EntidadeAuditavel;
import br.com.ifdelivery.util.exception.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "Usuario")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends EntidadeAuditavel {
/*
    @OneToMany(mappedBy = "usuario", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Entrega> entregas;
*/
    @OneToMany(mappedBy = "usuario", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Endereco> enderecos;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private Integer desconto;

    @Column
    private LocalDate dataNascimento;

    @Column
    private String telefone;

    @Column
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Role role;

}