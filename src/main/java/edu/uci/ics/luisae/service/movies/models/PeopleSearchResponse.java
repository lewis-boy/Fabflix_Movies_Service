package edu.uci.ics.luisae.service.movies.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.Base.ResponseModel;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.Movie;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.Person;

public class PeopleSearchResponse extends ResponseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "people", required = true)
    private Person[] people;

    public PeopleSearchResponse(){}
    @JsonCreator
    public PeopleSearchResponse(@JsonProperty(value = "people", required = true) Person[] people){
        this.people = people;
    }

    public void setPeople(Person[] people) {
        this.people = people;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "people", required = true)
    public Person[] getPeople() {
        return people;
    }
}
