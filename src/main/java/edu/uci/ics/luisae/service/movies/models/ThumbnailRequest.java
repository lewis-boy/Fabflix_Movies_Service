package edu.uci.ics.luisae.service.movies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.Base.RequestModel;

public class ThumbnailRequest extends RequestModel {
    @JsonProperty(value = "movie_ids", required = true)
    String[] movie_ids;

    public ThumbnailRequest(){}

    @JsonProperty(value = "movie_ids", required = true)
    public String[] getMovie_ids() {
        return movie_ids;
    }

    public void setMovie_ids(String[] movie_ids) {
        this.movie_ids = movie_ids;
    }
}
