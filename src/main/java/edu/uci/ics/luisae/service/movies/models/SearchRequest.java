package edu.uci.ics.luisae.service.movies.models;

import edu.uci.ics.luisae.service.movies.Base.RequestModel;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SearchRequest extends RequestModel {
    private String title;
    private Integer year;
    private String director;
    private String genre;
    private Boolean hidden;
    private Integer limit;
    private Integer offset;
    private String orderby;
    private String direction;
    private String secondary;

    public SearchRequest(){}
    public SearchRequest( String title,
                   Integer year,
                   String director,
                   String genre,
                   Boolean hidden,
                   Integer limit,
                   Integer offset,
                   String orderby,
                   String direction){
        //TODO set defaults
        if(title != null)
            this.title = title;
        if(director != null)
            this.director = director;
        if( year != null)
            this.year  = year;
        if( genre != null)
            this.genre  = genre;
        if( hidden != null)
            this.hidden  = hidden;
        else
            this.hidden = false;
        if( limit != null)
            this.limit  = limit;
        else
            this.limit = 10;
        if( offset != null)
            this.offset  = offset;
        else
            this.offset = 0;
        if( orderby != null)
            this.orderby  = orderby;
        else
            this.orderby = "title";
        if( direction != null)
            this.direction  = direction;
        else
            this.direction = "asc";
        if(this.orderby.equals("rating"))
            this.secondary = "title";
        else
            this.secondary = "rating";
    }

    public void print(){
        if(title != null)
            ServiceLogger.LOGGER.info("title: " + title);
        if(director != null)
            ServiceLogger.LOGGER.info("director: " + director);
        if( year != null)
            ServiceLogger.LOGGER.info("year: " + year);
        if( genre != null)
            ServiceLogger.LOGGER.info("year: " + year);
        if( hidden != null)
            ServiceLogger.LOGGER.info("hidden: " + hidden);
        if( limit != null)
            ServiceLogger.LOGGER.info("limit: " + limit);
        if( offset != null)
            ServiceLogger.LOGGER.info("offset: " + offset);
        if( orderby != null)
            ServiceLogger.LOGGER.info("orderby: " + orderby);
        if( direction != null)
            ServiceLogger.LOGGER.info("direction: " + direction);
        if(secondary != null)
            ServiceLogger.LOGGER.info("secondary: " + secondary);
    }

    //public static String buildMovieQuery(){}

    public ArrayList<String> buildStringArrayList(){
        ArrayList<String> a = new ArrayList<>();
        if(title != null)
            a.add("title");
        if(director != null)
            a.add("director");
        if( year != null)
            a.add("year");
        if( genre != null)
            a.add("genre");
        if( hidden != null)
            a.add("hidden");
        if( limit != null)
            a.add("limit");
        if( offset != null)
            a.add("offset");
        if( orderby != null)
            a.add("orderby");
        if( direction != null)
            a.add("direction");
        return a;
    }

    public String getDirection() {
        return direction;
    }

    public String getOrderby() {
        return orderby;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public Integer getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }
}
