package edu.uci.ics.luisae.service.movies.models.MovieClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre {
    @JsonProperty(value = "genre_id", required = true)
    private int genre_id;
    @JsonProperty(value = "name", required = true)
    private String name;

    public Genre(){}
    public Genre(int genre_id, String name){
        this.genre_id = genre_id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
}
