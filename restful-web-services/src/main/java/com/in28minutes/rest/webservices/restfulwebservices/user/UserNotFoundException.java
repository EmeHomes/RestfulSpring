package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*La clase se ha creado para los casos en los que no se encuentra un usuario,
 *es muy específica asi que se le añade un status específico*/
@ResponseStatus(HttpStatus.NOT_FOUND)
//La clase en este caso hereda de RuntimeException
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
