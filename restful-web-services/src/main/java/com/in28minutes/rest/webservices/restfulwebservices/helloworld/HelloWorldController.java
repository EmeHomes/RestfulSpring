package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

//Con @RestController le digo a Spring que esto es un controlador
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //URI - /hello-world
    //GET / Tengo que decir que esto va a ser un @RequestMapping y entre paréntesis indicarle el método que voy a usar y la URI que quiero que tenga
    //método -"Hello World"
    @RequestMapping(method= RequestMethod.GET, path="/hello-world")
    public String helloWorld(){
        return "Hola mundo, estoy probando a hacer métodos en un controlador de Spring";
    }
    /*
    Este método haría lo mismo que el de arribapero no habría que indicar el RequestMethod porque se indica en el mapeo

     @GetMapping(path="/hello-world")
      public String helloWorld(){
            return "Hola mundo, estoy probando a hacer métodos en un controlador de Spring";
            }
     */

    //hello-world-bean. RECUERDA esto es la instancia de una clase asi que el tipo es HelloWorldBean, no String como en el caso anterior
    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean ("Hola mundo, soy un mensaje de hello world bean");
    }

    //hello-world-bean/path-variable/Maríaa
    @GetMapping(path="/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        // el símbolo %s se sustituirá por elo parámetro variable de su derecha (name)
        return new HelloWorldBean (String.format("Hola mundo, %s está aquí", name));
    }

    //LocaleContextHolder impediría tener que pasarle un paámetro (locale) como veníamos haciendo
    @GetMapping(path = "hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
