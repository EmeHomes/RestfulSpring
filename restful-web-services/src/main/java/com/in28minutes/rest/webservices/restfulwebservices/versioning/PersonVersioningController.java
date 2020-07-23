package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
/*
Versioning con URIS, ejemplo básico
    @GetMapping("V1/person")
    public PersonV1 personV1(){
        return new PersonV1("Harry Potter");
    }

    @GetMapping("V2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Hermione", "Granger"));
    }
*/

/*
* Versioning segundo nivel, Negociacion con el Header y los contenidos
* Primero mostramos como hacerlo con parámetros, para probarlo en postman person/param?version=(1 ó 2)
* @GetMapping(value = "V1/param", params = "version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Harry Potter");
    }

    @GetMapping(value="V2/param", params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Hermione", "Granger"));
    }
*
*Ahora probamos a hacerlo con headers, para probarlo en postman person/header y en los header se mete en
* nombre del header y a su derecha el valor
* @GetMapping(value = "V1/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Harry Potter");
    }

    @GetMapping(value="V2/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Hermione", "Granger"));
    }
*
* Ahora probamos a hacerlo con producers, para probarlo en postman person/produces y en los header se mete en
* Accept y a su derecha application/vnd.company.app-(V1 ó V2)+json, a veces esto se llama Mime typeing versioning
*/

    @GetMapping(value = "V1/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Harry Potter");
    }

    @GetMapping(value="V2/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Hermione", "Granger"));
    }

}
