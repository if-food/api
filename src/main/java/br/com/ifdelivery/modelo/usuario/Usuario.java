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
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Usuario")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends EntidadeAuditavel implements UserDetails {

    @OneToMany(mappedBy = "usuario", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Entrega> entregas;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}