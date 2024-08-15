package br.com.ifdelivery.modelo.item_pedido;

import br.com.ifdelivery.modelo.pedido.Pedido;
import br.com.ifdelivery.modelo.produto.Produto;
import br.com.ifdelivery.util.entity.EntidadeAuditavel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ItensDoPedido")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido extends EntidadeAuditavel {

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitario;

    @Transient
    public Double getSubtotal() {
        return precoUnitario * quantidade;
    }
}
