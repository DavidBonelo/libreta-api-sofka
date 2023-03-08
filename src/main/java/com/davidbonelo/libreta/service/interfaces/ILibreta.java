package com.davidbonelo.libreta.service.interfaces;

import com.davidbonelo.libreta.domain.Contacto;
import com.davidbonelo.libreta.domain.Telefono;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ILibreta {

    /**
     * Devuelve una lista de Contactos con todos contactos del sistema
     */
    public List<Contacto> getList();

    /**
     * Devuelve una lista de Contactos con todos contactos del sistema ordenados por el campo indicado
     * (nombre o apellido) ya sea ascendente o descendente
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de contactos
     */
    public List<Contacto> getList(String field, Sort.Direction order);

    /**
     * Busca un dato dado entre el nombre y/o los apellidos en un contacto
     *
     * @param dataToSearch Dato a buscar
     * @return Lista de contactos
     */
    public List<Contacto> searchContacto(String dataToSearch);

    /**
     * Crea un contacto en el sistema
     *
     * @param contacto Objeto del contacto a crear
     * @return Objeto del contacto creado
     */
    public Contacto createContacto(Contacto contacto);

    /**
     * Crea un teléfono en el sistema a nombre de un contacto
     *
     * @param telefono Objeto del teléfono a crear
     * @return Objeto del teléfono creado
     */
    public Telefono createTelefono(Telefono telefono);

    /**
     * Actualiza una tupla completa de un contacto
     *
     * @param id       Identificador del contacto a actualizar
     * @param contacto Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     */
    Contacto updateContacto(Integer id, Contacto contacto);

    /**
     * Actualiza el nombre de un contacto basado en su identificador
     *
     * @param id       Identificador del contacto a actualizar
     * @param contacto Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     */
    public Contacto updateNombre(Integer id, Contacto contacto);

    /**
     * Actualiza el apellido de un contacto basado en su identificador
     *
     * @param id       Identificador del contacto a actualizar
     * @param contacto Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     */
    public Contacto updateApellidos(Integer id, Contacto contacto);

    /**
     * Actualiza la tupla completa de un teléfono en el sistema basado en su identificador
     *
     * @param id       Identificador del teléfono a actualizar
     * @param telefono Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     */
    public Telefono updateTelefono(Integer id, Telefono telefono);

    /**
     * Actualiza solamente el teléfono de un contacto a partir del ID de la tupla del teléfono
     *
     * @param id       Identificador del teléfono a actualizar
     * @param telefono Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     */
    public Telefono updateOnlyTelefono(Integer id, Telefono telefono);

    /**
     * Borra un contacto del sistema basado en su identificador
     *
     * @param id Identificación del contacto a borrar
     * @return Objeto del contacto borrado
     */
    Contacto deleteContacto(Integer id);

    /**
     * Borra un teléfono del sistema basado en su identificador
     *
     * @param id Identificación del teléfono a borrar
     * @return Objeto del teléfono borrado
     */
    Telefono deleteTelefono(Integer id);
}
