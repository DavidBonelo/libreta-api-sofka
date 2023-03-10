package com.davidbonelo.libreta.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "telefono")
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tel_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace con la entidad Contacto (un contacto puede tener muchos número de teléfono)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Contacto.class, optional = false)
    @JoinColumn(name = "tel_contacto_id", nullable = false)
    @JsonBackReference
    private Contacto contacto;

    /**
     * Número de teléfono
     */
    @Column(name = "tel_telefono", nullable = false, length = 30)
    private String telefono;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "tel_created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * Fecha y hora en que la tupla ha sido actualizada por última vez
     */
    @Column(name = "tel_updated_at")
    private Instant updatedAt;
}