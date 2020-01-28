package edu.uci.ics.luisae.service.movies.models.MovieClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RandomMovie {
    private String title;
    private Integer year;
    private String backdrop_path;
    private String poster_path;
    private String stars;

    @JsonCreator
    public RandomMovie(@JsonProperty("title") String title,
                       @JsonProperty("year") Integer year,
                       @JsonProperty("backdrop path") String backdrop_path,
                       @JsonProperty("poster_path") String poster_path,
                       @JsonProperty("stars") String stars){
        this.title = title;
        this.year = year;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.stars = stars;
    }


    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @JsonIgnore
    public void print(){
        ServiceLogger.LOGGER.info("title: " + (title == null? "null":title));
        ServiceLogger.LOGGER.info("year: " + year);
        ServiceLogger.LOGGER.info("backdrop: " + (backdrop_path.isEmpty()? "null":backdrop_path));
        ServiceLogger.LOGGER.info("poster: " + (poster_path.isEmpty()? "null":poster_path));
        ServiceLogger.LOGGER.info(stars);
        if(this.stars == null)
            ServiceLogger.LOGGER.warning("THIS IS NULL");
    }

}

