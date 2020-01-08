package edu.uci.ics.luisae.service.movies.models.MovieClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class People {
    @JsonProperty(value = "person_id", required = true)
    private int person_id;
    @JsonProperty(value = "name", required = true)
    private String name;

    public People(){}

    public People(String name, int person_id){
        this.name = name;
        this.person_id = person_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
