package br.com.ifdelivery.modelo.restaurante;

import br.com.ifdelivery.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "Restaurantes")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante extends EntidadeAuditavel {

    @Column
    private String razaoSocial;

    @Column
    private String nomeFantasia;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "CEP")
    private String cep;

    @Column
    private String estado;

    @Column
    private String cidade;

    @Column
    private String bairro;

    @Column
    private String rua;

    @Column
    private String numero;

    @Column
    private String complemento;

    @Column
    private boolean isActive;

    @Column
    private boolean isOpen;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriasEnum categoriasEnum;
}
