package org.jog.crudRepository.model.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -3014783722422100146L;

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }


}
