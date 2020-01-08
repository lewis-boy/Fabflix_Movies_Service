package edu.uci.ics.luisae.service.movies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.Genre;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.People;

public class PeopleRequest {
    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_OFFSET = 0;
    private static final String DEFAULT_ORDERBY = "title";
    private static final String DEFAULT_DIRECTION = "asc";


    private String name;
    private Integer limit;
    private Integer offset;
    private String orderby;
    private String direction;

    public PeopleRequest(){}
    public PeopleRequest(String name, Integer limit, Integer offset, String orderby,String direction){
        this.name = name;
        this.limit = limit==null?DEFAULT_LIMIT:(limit!=10&&limit!=25&&limit!=50&&limit!=100?DEFAULT_LIMIT:limit);
        this.offset = offset==null?DEFAULT_OFFSET:(offset!=0&&offset%limit!=0?DEFAULT_OFFSET:offset);
        this.orderby = orderby==null?DEFAULT_ORDERBY:(!orderby.equals("title")&&
               !orderby.equals("year")&&!orderby.equals("rating")?DEFAULT_ORDERBY:orderby);
        this.direction = direction==null?DEFAULT_DIRECTION:(!direction.equals("asc")&&!direction.equals("desc")?
               DEFAULT_DIRECTION:direction);


//        this.limit = limit==null?DEFAULT_LIMIT:(limit!=10&&limit!=25&&limit!=50&&limit!=100?DEFAULT_LIMIT:limit);
//        this.offset = offset==null?DEFAULT_OFFSET:(offset!=0&&offset%limit!=0?DEFAULT_OFFSET:offset);
//        this.orderby = orderby==null?DEFAULT_ORDERBY:(!orderby.equals("name")&&
//                !orderby.equals("birthday")&&!orderby.equals("popularity")?DEFAULT_ORDERBY:orderby);
//        this.direction = direction==null?DEFAULT_DIRECTION:(!direction.equals("asc")&&!direction.equals("desc")?
//                DEFAULT_DIRECTION:direction);
//        this.secondaryOrder = this.orderby.equals("popularity")?"name":"popularity";
//        this.secondaryDirection = this.secondaryOrder.equals("name")?"asc":"desc";
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

    public String getName() {
        return name;
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public void print(){
        ServiceLogger.LOGGER.info("name: " + (this.name == null? "null":this.name));
//        ServiceLogger.LOGGER.info("birthday: " + (this.birthday == null? "null":this.birthday));
//        ServiceLogger.LOGGER.info("movie_title: " + (this.movie_title == null?"null":this.movie_title));
        ServiceLogger.LOGGER.info("limit: " + this.limit);
        ServiceLogger.LOGGER.info("offset: " + this.offset);
        ServiceLogger.LOGGER.info("orderby: " + this.orderby);
        ServiceLogger.LOGGER.info("direction: " + this.direction);
//        ServiceLogger.LOGGER.info("secondary order: " + this.secondaryOrder);
//        ServiceLogger.LOGGER.info("secondary direction: " + this.secondaryDirection);
    }
}
