package edu.uci.ics.luisae.service.movies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;

public class MovieSeachRequest {
    private static final int DEFAULT_OFFSET = 0;
    private static final int DEFAULT_LIMIT = 10;
    private static final String DEFAULT_ORDERBY = "title";
    private static final String DEFAULT_DIRECTION = "asc";


    private String title;
    private Integer year;
    private String director;
    private String genre;
    private Boolean hidden;
    private Integer limit;
    private Integer offset;
    private String orderby;
    private String direction;
    private String secondaryOrder;
    private String secondaryDirection;

    public MovieSeachRequest(){}
    //TODO valuable lesson, you cant put null boolean checks and String checks together
    public MovieSeachRequest(String title,Integer year, String director, String genre,
                             Boolean hidden,Integer limit, Integer offset, String orderby,
                             String direction){
            if (title != null)
                this.title = title;
            if (year != null)
                //if problem, add jackson non default
                this.year = year;
            else {
                ServiceLogger.LOGGER.info("year is null");
            }
            if (director != null)
                this.director = director;
            this.genre = genre;

            this.hidden = hidden != null ? hidden : false;
            if (limit != null)
                this.limit = (limit.intValue() != 10 && limit.intValue() != 25 && limit.intValue() != 50 && limit.intValue() != 100 ? DEFAULT_LIMIT : limit);
            else
                this.limit = DEFAULT_LIMIT;
            if (offset != null)
                this.offset = ((offset != 0 && (offset % this.limit != 0)) ? DEFAULT_OFFSET : offset);
            else
                this.offset = DEFAULT_OFFSET;
            if (orderby != null) {
                if (!orderby.equals("title") && !orderby.equals("rating") && !orderby.equals("year"))
                    this.orderby = DEFAULT_ORDERBY;
                else
                    this.orderby = orderby;
            } else
                this.orderby = DEFAULT_ORDERBY;
            if (direction != null) {
                if (!direction.equals("asc") && !direction.equals("desc"))
                    this.direction = DEFAULT_DIRECTION;
                else
                    this.direction = direction;
            } else
                this.direction = DEFAULT_DIRECTION;

            if (this.orderby.equals("rating")) {
                this.secondaryOrder = "title";
                this.secondaryDirection = "asc";
            } else {
                this.secondaryOrder = "rating";
                this.secondaryDirection = "desc";
            }

    }

    @JsonIgnore
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
    }

    public String getSecondaryDirection() {
        return secondaryDirection;
    }

    public void setSecondaryDirection(String secondaryDirection) {
        this.secondaryDirection = secondaryDirection;
    }

    public String getSecondaryOrder() {
        return secondaryOrder;
    }

    public void setSecondaryOrder(String secondaryOrder) {
        this.secondaryOrder = secondaryOrder;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
