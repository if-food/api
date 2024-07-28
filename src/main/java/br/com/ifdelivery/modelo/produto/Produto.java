package br.com.ifdelivery.modelo.produto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import br.com.ifdelivery.util.entity.EntidadeAuditavel;

@Entity
@Table(name = "Produtos")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends EntidadeAuditavel {

    @Column
    private String codigo;
    @Column
    private String titulo;
    @Column
    private String categoria;
    @Column
    private String descricao;
    @Column
    private String imagem;
    @Column
    private Double valorUnitario;

    //TO DO
    // IMPLEMENTAR RELACIONAMENTO COM RESTAURANTE
    // @ManyToOne
    // @JoinColumn(name = "restaurante_id")
    // private Restaurante restaurante;
}
