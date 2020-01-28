package edu.uci.ics.luisae.service.movies.models;

import edu.uci.ics.luisae.service.movies.Base.RequestModel;

public class MovieRandomRequest extends RequestModel {
    private static final int DEFAULT_LIMIT = 10;

    private String genre;
    private Integer limit;

    public MovieRandomRequest(){}
    public MovieRandomRequest(String genre, Integer limit){
        this.genre = genre;
        if(limit != null)
            this.limit = (limit.intValue() != 10 && limit.intValue() != 15 && limit.intValue() != 20 ? DEFAULT_LIMIT : limit);
        else
            this.limit = DEFAULT_LIMIT;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
