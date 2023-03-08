package com.davidbonelo.libreta.repository;

import com.davidbonelo.libreta.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
    /**
     * Busca los contactos que empizan por X dato tanto por nombre como por apellido
     *
     * @param data Dato a buscar
     * @return Listado de contactos encontrados
     */
    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE (cnt.nombre LIKE :data% OR cnt.apellido LIKE :data%) " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreOrApellidoStartingWith(@Param("data") String data);

    /**
     * Busca los contactos que contienen X dato tanto por nombre como por apellido
     *
     * @param data Dato a buscar
     * @return Listado de contactos encontrados
     */
    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE cnt.nombre LIKE %:data% OR cnt.apellido LIKE %:data% " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreOrApellidoContains(@Param("data") String data);

    /**
     * Busca los contactos que finalizan por X dato tanto por nombre como por apellido
     *
     * @param data Dato a buscar
     * @return Listado de contactos encontrados
     */
    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE cnt.nombre LIKE %:data OR cnt.apellido LIKE %:data " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreOrApellidoEndingWith(@Param("data") String data);

    /**
     * Actualiza el nombre de un contacto basado en su identificador
     *
     * @param id Identificador del contacto
     * @param nombre Nuevo nombre del contacto
     */
    @Modifying
    @Query(value = "update Contacto cnt set cnt.nombre = :nombre, cnt.updatedAt = CURRENT_TIMESTAMP where cnt.id = :id")
    public void updateNombre(@Param(value = "id") Integer id, @Param(value = "nombre") String nombre);

    /**
     * Actualiza el apellido de un contacto basado en su identificador
     *
     * @param id Identificador del contacto
     * @param apellido Nuevo apellido del contacto
     */
    @Modifying
    @Query(value = "update Contacto cnt set cnt.apellido = :apellido, cnt.updatedAt = CURRENT_TIMESTAMP where cnt.id = :id")
    public void updateApellido(@Param(value = "id") Integer id, @Param(value = "apellido") String apellido);
}