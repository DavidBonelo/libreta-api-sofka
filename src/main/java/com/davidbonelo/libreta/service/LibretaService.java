package com.davidbonelo.libreta.service;

import com.davidbonelo.libreta.domain.Contacto;
import com.davidbonelo.libreta.domain.Telefono;
import com.davidbonelo.libreta.repository.ContactoRepository;
import com.davidbonelo.libreta.repository.TelefonoRepository;
import com.davidbonelo.libreta.service.interfaces.ILibreta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;

@Service
public class LibretaService implements ILibreta {
    /**
     * Repositorio de Contacto
     */
    @Autowired
    private ContactoRepository contactoRepository;

    /**
     * Repositorio de Telefono
     */
    @Autowired
    private TelefonoRepository telefonoRepository;

    /**
     * Devuelve una lista de Contactos con todos contactos del sistema
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> getList() {
        return contactoRepository.findAll();
    }

    /**
     * Devuelve una lista de Contactos con todos contactos del sistema ordenados por el campo indicado ya sea ascendente
     * o descendete
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de contactos
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> getList(String field, Sort.Direction order) {
        return contactoRepository.findAll(Sort.by(order, field));
    }

    /**
     * Busca un dato entre el nombre o los apellidos en un contacto
     *
     * @param dataToSearch Dato a buscar
     * @return Lista de contactos
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> searchContacto(String dataToSearch) {
        var contacto1 = contactoRepository.findByNombreOrApellidoStartingWith(dataToSearch);
        var contacto2 = contactoRepository.findByNombreOrApellidoContains(dataToSearch);
        var contacto3 = contactoRepository.findByNombreOrApellidoEndingWith(dataToSearch);
        var answer = new HashSet<Contacto>();
        answer.addAll(contacto1);
        answer.addAll(contacto2);
        answer.addAll(contacto3);
        return answer.stream().toList();
    }

    /**
     * Crea un contacto en el sistema
     *
     * @param contacto Objeto del contacto a crear
     * @return Objeto del contacto creado
     */
    @Override
    @Transactional
    public Contacto createContacto(Contacto contacto) {
        contacto.setCreatedAt(Instant.now());
        return contactoRepository.save(contacto);
    }

    /**
     * Crea un teléfono en el sistema a nombre de un contacto
     *
     * @param telefono Objeto del teléfono a crear
     * @return Objeto del teléfono creado
     */
    @Override
    @Transactional
    public Telefono createTelefono(Telefono telefono) {
        telefono.setCreatedAt(Instant.now());
        return telefonoRepository.save(telefono);
    }

    /**
     * Actualiza una tupla completa de un contacto
     *
     * @param id       Identificador del contacto a actualizar
     * @param contacto Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     */
    @Override
    @Transactional
    public Contacto updateContacto(Integer id, Contacto contacto) {
        contacto.setId(id);
        contacto.setUpdatedAt(Instant.now());
        return contactoRepository.save(contacto);
    }

    /**
     * Actualiza el nombre de un contacto
     *
     * @param id       Identificador del contacto a actualizar
     * @param contacto Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     */
    @Override
    @Transactional
    public Contacto updateNombre(Integer id, Contacto contacto) {
        contacto.setId(id);
        var contact = contactoRepository.findById(id);
        if (contact.isPresent()) {
            var foundContact = contact.get();
            foundContact.setUpdatedAt(Instant.now());
            foundContact.setNombre(contacto.getNombre());
            return contactoRepository.save(foundContact);
        }
//        contactoRepository.updateNombre(id, contacto.getNombre());
        return contacto;
    }

    /**
     * Actualiza el apellido de un contacto
     *
     * @param id       Identificador del contacto a actualizar
     * @param contacto Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     */
    @Override
    @Transactional
    public Contacto updateApellidos(Integer id, Contacto contacto) {
        contacto.setId(id);
        var contact = contactoRepository.findById(id);
        if (contact.isPresent()) {
            var foundContact = contact.get();
            foundContact.setUpdatedAt(Instant.now());
            foundContact.setApellido(contacto.getApellido());
            return contactoRepository.save(foundContact);
        }
//        contactoRepository.updateApellido(id, contacto.getApellido());
        return contacto;
    }

    /**
     * Actualiza la tupla completa de un teléfono en el sistema
     *
     * @param id       Identificador del teléfono a actualizar
     * @param telefono Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     */
    @Override
    @Transactional
    public Telefono updateTelefono(Integer id, Telefono telefono) {
        telefono.setId(id);
        telefono.setUpdatedAt(Instant.now());
        telefonoRepository.save(telefono);
        return telefono;
    }

    /**
     * Actualiza solamente el teléfono de un contacto a partir del ID de la tupla del teléfono
     *
     * @param id       Identificador del teléfono a actualizar
     * @param telefono Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     */
    @Override
    @Transactional
    public Telefono updateOnlyTelefono(Integer id, Telefono telefono) {
        telefono.setId(id);
        var phone = telefonoRepository.findById(id);
        if (phone.isPresent()) {
            var foundPhone = phone.get();
            foundPhone.setUpdatedAt(Instant.now());
            foundPhone.setTelefono(telefono.getTelefono());
            return telefonoRepository.save(foundPhone);
        }
//        telefonoRepository.updateTelefono(id, telefono.getTelefono());
        return telefono;
    }

    /**
     * Borra un contacto del sistema
     *
     * @param id Identificación del contacto a borrar
     * @return Objeto del contacto borrado
     */
    @Override
    @Transactional
    public Contacto deleteContacto(Integer id) {
        var contacto = contactoRepository.findById(id);
        if (contacto.isPresent()) {
            contactoRepository.delete(contacto.get());
            return contacto.get();
        } else {
            return null;
        }
    }

    /**
     * Borra un teléfono del sistema
     *
     * @param id Identificación del teléfono a borrar
     * @return Objeto del teléfono borrado
     */
    @Override
    @Transactional
    public Telefono deleteTelefono(Integer id) {
        var telefono = telefonoRepository.findById(id);
        if (telefono.isPresent()) {
            telefonoRepository.delete(telefono.get());
            return telefono.get();
        } else {
            return null;
        }
    }
}
