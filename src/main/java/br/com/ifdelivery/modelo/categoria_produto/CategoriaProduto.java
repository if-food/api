package br.com.ifdelivery.modelo.categoria_produto;

import br.com.ifdelivery.modelo.produto.Produto;
import br.com.ifdelivery.modelo.restaurante.Restaurante;
import br.com.ifdelivery.util.entity.EntidadeAuditavel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "Categorias_Produtos",uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "restaurante_id"}))
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProduto extends EntidadeAuditavel {

    @Column
    private String nome;

    @Column
    private String descricao;

    // relacionamento com a tabela de produtos
    @JsonIgnore
    @OneToMany(mappedBy = "categoriaProduto", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Produto> produtos;

    //relacionamento com a tabela de restaurante
    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

}
