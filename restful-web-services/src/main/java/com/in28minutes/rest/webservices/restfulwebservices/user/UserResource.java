package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    // Aquí estamos autoactivando el componente que creamos en el servicio
    @Autowired
    private UserDAOService service;
    
    /*Queremos obtener todos los detalles de todos los usuarios así que llamamos al servicio donde ya
      hay creado un método para ello, findAll*/
    //GET/users retrieveAllUsers

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }
    /*Queremos obtener todos los detalles de un usuario así que llamamos al servicio donde ya
      hay creado un método para ello, findAll*/
    //GET/users/{}id retrieveUser(int id)

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        /* Para que cuando no se encuentre un usuario con un id determinado, primero se crea una
         *variable local del tipo de la clase en cuestión, después se indica cuando se va a lanzar
         *ese error y se crea una clase que contendrá el mensaje de error*/
        User user = service.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id-"+id);
        //"all-users", SEREVER_PATH + "/users"
        //retrieveAllUsers

        EntityModel<User> model = new EntityModel<>(user);

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkTo.withRel("all-users"));

        return model;
    }
    /*Ahora queremos obtener información de los detalles del usuario. El input va a ser los detalles
     *del usuario y el output lo creado y la URI */

    /*Se añade @Valid para realizar una validación desde nuestro código*/
    @PostMapping("/users")
    public ResponseEntity<Object> createdUser(@Valid @RequestBody User user) {
        //Aquí hemos creado la función que guarda a un usuario nuevo
        User savedUser = service.save(user);
        //Como queremos sacar su estatus a continuación estariamos devolviendo la actual URL
            /*Primero estariamos usando este generador de componentes de URI de servlet a partir de
             *la solicitud actual, todo ello lo guardo en location */
            URI location = ServletUriComponentsBuilder.
            //Aquí le estoy añadiendo el "slash", le indico que después de /users viene algo
            fromCurrentRequest().path("/{id}").
            /*Aquí le indico qué va después de ese slash, y uso la función ya creada para sacar el id
             *y meterlo en la URI */
            buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // No hace falta que devuelva nada porque con que devuelva un 200 sabemos que la petición está bien
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deletebyId(id);

        if(user==null)
            throw  new UserNotFoundException("id-"+id);
;
    }


}

