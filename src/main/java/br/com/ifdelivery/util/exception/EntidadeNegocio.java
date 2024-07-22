package br.com.ifdelivery.util.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@MappedSuperclass
public abstract class EntidadeNegocio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @Column
    private Boolean habilitado;

}

