package br.com.ifdelivery.modelo.cliente;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifdelivery.modelo.usuario.Usuario;
import br.com.ifdelivery.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cliente")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeAuditavel {
	
  //  @OneToMany(mappedBy = "usuario", orphanRemoval = true, fetch = FetchType.EAGER)
 //   private List<Endereco> enderecos;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

    @Column
    private String nome;
    
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

}
