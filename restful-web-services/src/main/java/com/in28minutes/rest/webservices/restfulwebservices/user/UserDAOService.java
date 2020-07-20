package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

/*Le ponemos component porque le estamos indicando que queremos que se maneje con Sprint, este componente estaría hablando con
  la base de datos y deberíamos meterlo en una carperta de repositorios pero como es una matriz estática de la que estamos sacando
  información, lo dejamos aquí*/
@Component
public class UserDAOService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"Adam",new Date()));
        users.add(new User(2,"Eva",new Date()));
        users.add(new User(3,"Cain",new Date()));
        users.add(new User(4,"Abel",new Date()));
    }

    private static int usersCount = 4;

    //Creamos ahora métodos para manejar a los usuarios, primero para encontrar a todos
    public List<User> findAll(){
        return users;
    }
    //Para guardar nuevos usuarios
    public User save(User user){
        if (user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }
    //Para encontrar un usuario
    public User findOne(int id) {
        /* Esto es un foreach, está recorriendo la lista de users y la está guardando en una variable "user" de tipo User y le
           está diciendo que si encuentra a usuario con un id igual al id que le he pasado yo antes que devuelva a ese usuario
           si no, devuelvo un string */
        for (User user:users) {
            if (user.getId()==id) {
                return user;
            }
        }
        return null;
    }
    //Vamos a crear un método para borrar usuarios
    public User deletebyId(int id){
    /*No puedo usar un bucle for porque no puedo borrar un usuario en medio de una petición, en lugar de eso vamos a usar un
    * iterador*/
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
    return null;
    }

}
