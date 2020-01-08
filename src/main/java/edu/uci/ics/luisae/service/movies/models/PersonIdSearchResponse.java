package edu.uci.ics.luisae.service.movies.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.Base.ResponseModel;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.Person;

public class PersonIdSearchResponse extends ResponseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "person", required = true)
    Person person;

    public PersonIdSearchResponse(){}
    @JsonCreator
    public PersonIdSearchResponse(@JsonProperty(value = "person")Person person){
        this.person = person;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "person", required = true)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
