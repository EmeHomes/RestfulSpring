package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    /* @GetMapping("/filtering")
    *    public SomeBean retrieveSomeBean() {
    *        return new SomeBean("value1", "value2", "value3");
    *    }
    * Esto era para un STATIC FILTERING*/


    //Ahora estamos haciendo un DINAMIC FILTERING, ahora filtraremo y devolveremods field1, field2
    @GetMapping("/filtering")
    //Para que esto funcione en la clase SomeBean tenemos que añadirle la propiedad de Spring JsonFilter
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
                filterOutAllExcept("field1", "field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

        return mapping;
    }

    /* @GetMapping("/filtering-list")
    * public List<SomeBean> retrieveListOfSomeBeans(){
    *   return Arrays.asList(new SomeBean("value1", "value2", "value3"),
    *            new SomeBean("value12", "value22", "value32"));}
    * //Esto era para filtrar de manera estática*/
    @GetMapping("/filtering-list")
    //Para que esto funcione en la clase SomeBean tenemos que añadirle la propiedad de Spring JsonFilter
    public List<SomeBean> retrieveListOfSomeBean(){
       List<SomeBean> list = Arrays.asList(new SomeBean("value12", "value22", "value32"),
               new SomeBean("value12", "value22", "value32"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
                filterOutAllExcept("field2", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return list;
    }
}
