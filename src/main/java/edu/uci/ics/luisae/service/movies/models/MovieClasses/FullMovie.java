package edu.uci.ics.luisae.service.movies.models.MovieClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;

public class FullMovie {
    @JsonProperty(value = "movie_id", required = true)
    private String movie_id;
    @JsonProperty(value = "title", required = true)
    private String title;
    @JsonProperty(value = "year", required = true)
    private int year;
    @JsonProperty(value = "director", required = true)
    private String director;
    @JsonProperty(value = "rating", required = true)
    private Double rating;
    @JsonProperty(value = "num_votes", required = true)
    private int num_votes;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty(value = "budget")
    private Integer budget;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty(value = "revenue")
    private Long revenue;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty(value = "overview")
    private String overview;
    @JsonProperty(value = "backdrop_path")
    private String backdrop_path;
    @JsonProperty(value = "poster_path")
    private String poster_path;
    @JsonProperty(value = "hidden")
    private Boolean hidden;
    @JsonProperty(value = "genres")
    private Genre[] genres;
    @JsonProperty(value = "people")
    private People[] people;

    public FullMovie(){}

    @JsonProperty(value = "people")
    public People[] getPeople() {
        return people;
    }

    public void setPeople(People[] people) {
        this.people = people;
    }

    @JsonProperty(value = "genres")
    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

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

    @JsonProperty(value = "overview")
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @JsonProperty(value = "revenue")
    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    @JsonProperty(value = "budget")
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @JsonProperty(value = "num_votes", required = true)
    public int getNum_votes() {
        return num_votes;
    }

    public void setNum_votes(int num_votes) {
        this.num_votes = num_votes;
    }

    @JsonProperty(value = "rating", required = true)
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @JsonProperty(value = "director", required = true)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @JsonProperty(value = "year", required = true)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty(value = "title", required = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty(value = "movie_id", required = true)
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
        ServiceLogger.LOGGER.info("num_votes: " + num_votes);
        ServiceLogger.LOGGER.info("budget: " + (budget == 0?"null":budget));
        ServiceLogger.LOGGER.info("revenue: " + (revenue == 0?"null":revenue));
        ServiceLogger.LOGGER.info("overview: " + (overview.isEmpty()? "null":overview));
        ServiceLogger.LOGGER.info("backdrop: " + (backdrop_path.isEmpty()? "null":backdrop_path));
        ServiceLogger.LOGGER.info("poster: " + (poster_path.isEmpty()? "null":poster_path));
        ServiceLogger.LOGGER.info("hidden: " + hidden);
        for(People p : people)
            ServiceLogger.LOGGER.info(p.getName());
        for(Genre g : genres)
            ServiceLogger.LOGGER.info(g.getName());
    }

}
