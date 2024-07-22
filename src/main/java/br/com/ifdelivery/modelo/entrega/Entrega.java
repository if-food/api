package br.com.ifdelivery.modelo.entrega;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifdelivery.util.exception.EntidadeAuditavel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Entrega")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entrega extends EntidadeAuditavel{
    
}
