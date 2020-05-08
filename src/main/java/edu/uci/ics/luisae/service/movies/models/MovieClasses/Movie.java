package edu.uci.ics.luisae.service.movies.models.MovieClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Movie {
    @JsonProperty(value = "movie_id")
    private String movie_id;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "year")
    private Integer year;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "director")
    private String director;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "rating")
    private Double rating;
    @JsonProperty(value = "backdrop_path")
    private String backdrop_path;
    @JsonProperty(value = "poster_path")
    private String poster_path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "hidden")
    private Boolean hidden;

    public Movie(){}

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "hidden")
    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @JsonProperty(value = "poster_path")
    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @JsonProperty(value = "backdrop_path")
    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @JsonProperty(value = "rating")
    public Double getRating() {
        BigDecimal bd = new BigDecimal(rating).setScale(1, RoundingMode.HALF_UP);
        double newRating = bd.doubleValue();
        return newRating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @JsonProperty(value = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @JsonProperty(value = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty(value = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty(value = "movie_id")
    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    @JsonIgnore
    public void print(){
        ServiceLogger.LOGGER.info("movie id: " + (movie_id == null? "null":movie_id));
        ServiceLogger.LOGGER.info("title: " + (title == null? "null":title));
        ServiceLogger.LOGGER.info("year: " + year);
        ServiceLogger.LOGGER.info("director: " + director);
        ServiceLogger.LOGGER.info("rating: " + rating);
        ServiceLogger.LOGGER.info("backdrop: " + (backdrop_path.isEmpty()? "null":backdrop_path));
        ServiceLogger.LOGGER.info("poster: " + (poster_path.isEmpty()? "null":poster_path));
        ServiceLogger.LOGGER.info("hidden: " + hidden);
    }

}
