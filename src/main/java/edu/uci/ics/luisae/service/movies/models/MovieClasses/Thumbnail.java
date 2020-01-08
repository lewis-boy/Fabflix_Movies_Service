package edu.uci.ics.luisae.service.movies.models.MovieClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thumbnail {
    @JsonProperty(value = "movie_id", required = true)
    private String movie_id;
    @JsonProperty(value = "title", required = true)
    private String title;
    @JsonProperty(value = "backdrop_path", required = true)
    private String backdrop_path;
    @JsonProperty(value = "poster_path", required = true)
    private String poster_path;
    @JsonProperty(value = "hidden", access = JsonProperty.Access.WRITE_ONLY, required = true)
    private Boolean hidden;

    public Thumbnail(){}

    @JsonProperty(value = "hidden", access = JsonProperty.Access.WRITE_ONLY, required = true)
    public Boolean getHidden() {
        return hidden;
    }

    @JsonProperty(value = "hidden", access = JsonProperty.Access.WRITE_ONLY, required = true)
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
}
