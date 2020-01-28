package edu.uci.ics.luisae.service.movies.models.MovieClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDeserialize {
    @JsonProperty(value = "hello")
    private String hello;
    @JsonProperty(value = "hellos")
    private String[] hellos;

    TestDeserialize(){}

    public String[] getHellos() {
        return hellos;
    }

    public void setHellos(String[] hellos) {
        this.hellos = hellos;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
