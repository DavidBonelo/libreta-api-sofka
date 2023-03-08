package com.davidbonelo.libreta.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "contacto")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    /**
     * Nombre del contacto
     */
    @Column(name = "cnt_nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Apellidos del contacto
     */
    @Column(name = "cnt_apellido", nullable = false, length = 100)
    private String apellido;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "cnt_created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * Fecha y hora en que la tupla ha sido actualizada por última vez
     */
    @Column(name = "cnt_updated_at")
    private Instant updatedAt;

    /**
     * Punto de enlace entre la entidad del Contacto y Teléfono (un contacto puede tener muchos números de teléfono)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Telefono.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "contacto"
    )
    @JsonManagedReference
    private List<Telefono> telefonos = new ArrayList<>();

}
