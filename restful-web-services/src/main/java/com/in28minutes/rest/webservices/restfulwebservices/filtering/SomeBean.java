package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*STATIC FILTERING= En el caso de que algún contenido de la petición no deba darse al usuario puede usarse JsonIgnoreProperties,
 *indicando los valores no devueltos, esto se hace antes de crear la clase*/
@JsonIgnoreProperties(value={"field1","field2"})
public class SomeBean {
    private String field1;
    private String field2;

    @JsonIgnore
    /*STATIC FILTERING= En el caso de que algún contenido de la petición no deba darse al usuario puede usarse Json Ignore,
     *se lo indico en los atributos de la clase no en el constructor*/
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}