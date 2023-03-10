package com.davidbonelo.libreta.controller;

import com.davidbonelo.libreta.domain.Contacto;
import com.davidbonelo.libreta.domain.Telefono;
import com.davidbonelo.libreta.service.LibretaService;
import com.davidbonelo.libreta.utility.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@RestController
public class LibretaController {
    private final Response response = new Response();
    @Autowired
    private LibretaService libretaService;
    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    @GetMapping(path = "api")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    @GetMapping(path = "api/v1")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Index del sistema, responde con el listado de contactos y sus teléfonos
     *
     * @return Objeto Response en formato JSON
     */
    @GetMapping(path = "api/v1/index")
    public ResponseEntity<Response> index(HttpServletResponse httpResponse) {
        response.restart();
        try {
            response.data = libretaService.getList();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(path = "api/v1/index/orderby/{orderBy}/{order}")
    public ResponseEntity<Response> indexOrderBy(@PathVariable(value = "orderBy") String orderBy,
                                                 @PathVariable(value = "order") Sort.Direction order) {
        response.restart();
        try {
            response.data = libretaService.getList(orderBy, order);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);

    }

    /**
     * Devuelve el listado de contactos y sus teléfonos basados en un datos a buscar por nombre y/o apellidos
     *
     * @param dataToSearch Información a buscar
     * @return Objeto Response en formato JSON
     */
    @GetMapping(path = "api/v1/search/contact/{dataToSearch}")
    public ResponseEntity<Response> searchContactByNombreOrApellido(@PathVariable(value = "dataToSearch") String dataToSearch) {
        response.restart();
        try {
            response.data = libretaService.searchContacto(dataToSearch);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Crea un nuevo contacto en el sistema
     *
     * @param contacto Objeto Contacto acrear
     * @return Objeto Response en formato JSON
     */
    @PostMapping(path = "api/v1/contact")
    public ResponseEntity<Response> createContacto(@RequestBody Contacto contacto) {
        response.restart();
        try {
            log.info("Contacto a crear: {}", contacto);
            response.data = libretaService.createContacto(contacto);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Crea un nuevo número de teléfono en el sistema
     *
     * @param telefono Objeto Telefono a crear
     * @return Objeto Response en formato JSON
     */
    @PostMapping(path = "api/v1/phone")
    public ResponseEntity<Response> createTelefono(@RequestBody Telefono telefono) {
        response.restart();
        try {
            log.info("Telefono a crear: {}", telefono);
            response.data = libretaService.createTelefono(telefono);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de un contacto
     *
     * @param contacto Objeto contacto a actualizar
     * @param id       Identificador del contacto a actualizar
     * @return Objeto Response en formato JSON
     */
    @PutMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> updateContacto(@PathVariable(value = "id") Integer id,
                                                   @RequestBody Contacto contacto) {
        response.restart();
        try {
            contacto.setId(id);
            log.info("Contacto a actualizar: {}", contacto);
            response.data = libretaService.updateContacto(id, contacto);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de un número de teléfono
     *
     * @param telefono Objeto telefono a actualizar
     * @param id       Identificador del número de teléfono a actualizar
     * @return Objeto Response en formato JSON
     */
    @PutMapping(path = "api/v1/phone/{id}")
    public ResponseEntity<Response> updateTelefono(@PathVariable(value = "id") Integer id,
                                                   @RequestBody Telefono telefono) {
        response.restart();
        try {
            telefono.setId(id);
            log.info("Telefono a actualizar: {}", telefono);
            response.data = libretaService.updateTelefono(id, telefono);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Actualiza el nombre de un contacto basado en su identificador
     *
     * @param contacto Objeto Contacto
     * @param id       Identificador del contacto a actualizar
     * @return Objeto Response en formato JSON
     */
    @PatchMapping(path = "api/v1/contact/{id}/name")
    public ResponseEntity<Response> updateNombreFromContacto(@PathVariable(value = "id") Integer id,
                                                             @RequestBody Contacto contacto) {
        response.restart();
        try {
            contacto.setId(id);
            log.info("Contacto a actualizar: {}", contacto);
            response.data = libretaService.updateNombre(id, contacto);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Actualiza el apellido de un contacto basado en su identificador
     *
     * @param contacto Objeto Contacto
     * @param id       Identificador del contacto a actualizar
     * @return Objeto Response en formato JSON
     */
    @PatchMapping(path = "api/v1/contact/{id}/lastname")
    public ResponseEntity<Response> updateApellidoFromContacto(@PathVariable(value = "id") Integer id,
                                                               @RequestBody Contacto contacto) {
        response.restart();
        try {
            contacto.setId(id);
            log.info("Contacto a actualizar: {}", contacto);
            response.data = libretaService.updateApellidos(id, contacto);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Actualiza el número de teléfono basado en su identificador
     *
     * @param telefono Objeto Contacto
     * @param id       Identificador del número de teléfono a actualizar
     * @return Objeto Response en formato JSON
     */
    @PatchMapping(path = "api/v1/phone/{id}/number")
    public ResponseEntity<Response> updateOnlyTelefono(@PathVariable(value = "id") Integer id,
                                                       @RequestBody Telefono telefono) {
        response.restart();
        try {
            telefono.setId(id);
            log.info("Telefono a actualizar: {}", telefono);
            response.data = libretaService.updateOnlyTelefono(id, telefono);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Borra un contacto del sistema
     *
     * @param id Identificador del contacto a borrar
     * @return Objeto Response en formato JSON
     */
    @DeleteMapping(path = "api/v1/contact/{id}")
    public ResponseEntity<Response> deleteContactto(@PathVariable(value = "id") Integer id) {
        response.restart();
        try {
            response.data = libretaService.deleteContacto(id);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);

    }

    /**
     * Borra un teléfono del sistema
     *
     * @param id Identificador del teléfono a borrar
     * @return Objeto Response en formato JSON
     */
    @DeleteMapping(path = "api/v1/phone/{id}")
    public ResponseEntity<Response> deleteTelefono(@PathVariable(value = "id") Integer id) {
        response.restart();
        try {
            response.data = libretaService.deleteTelefono(id);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse) {
        response.restart();
        try {
            httpResponse.sendRedirect("/api/v1/index");
        } catch (IOException exception) {
            response.error = true;
            response.data = exception.getCause();
            response.message = exception.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if (exception.getRootCause() instanceof SQLException sqlEx) {
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062 -> response.message = "El dato ya está registrado";
                case 1452 -> response.message = "El usuario indicado no existe";
                default -> {
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
                }
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
