package edu.uci.ics.luisae.service.movies.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.Base.ResponseModel;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.RandomMovie;

public class MovieRandomResponse extends ResponseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "movies", required = true)
    private RandomMovie[] movies;
    public MovieRandomResponse(){}
//    @JsonCreator
//    public MovieRandomResponse(@JsonProperty(value = "movies", required = true) RandomMovie[] movies){
//        this.movies = movies;
//    }
    public void setMovies(RandomMovie[] movies) {
        this.movies = movies;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "movies", required = true)
    public RandomMovie[] getMovies() {
        return movies;
    }


}
