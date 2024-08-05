package br.com.ifdelivery.modelo.restaurante;

import br.com.ifdelivery.modelo.acesso.Usuario;
import br.com.ifdelivery.util.entity.EntidadeAuditavel;
import jakarta.persistence.*;
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


    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

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

}
