package edu.uci.ics.luisae.service.movies.models;

public class PeopleSeachRequest {
    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_OFFSET = 0;
    private static final String DEFAULT_ORDERBY = "name";
    private static final String DEFAULT_DIRECTION = "asc";


    private String name;
    private String birthday;
    private String movie_title;
    private Integer limit;
    private Integer offset;
    private String orderby;
    private String direction;
    private String secondaryOrder;
    private String secondaryDirection;

    public PeopleSeachRequest(){}
    public PeopleSeachRequest(String name, String birthday, String movie_title, Integer limit, Integer offset, String orderby,
                              String direction){
        this.name = name;
        this.birthday = birthday;
        this.movie_title = movie_title;


        this.limit = limit==null?DEFAULT_LIMIT:(limit!=10&&limit!=25&&limit!=50&&limit!=100?DEFAULT_LIMIT:limit);
        this.offset = offset==null?DEFAULT_OFFSET:(offset!=0&&offset%limit!=0?DEFAULT_OFFSET:offset);
        this.orderby = orderby==null?DEFAULT_ORDERBY:(!orderby.equals("name")&&
                !orderby.equals("birthday")&&!orderby.equals("popularity")?DEFAULT_ORDERBY:orderby);
        this.direction = direction==null?DEFAULT_DIRECTION:(!direction.equals("asc")&&!direction.equals("desc")?
                DEFAULT_DIRECTION:direction);
        this.secondaryOrder = this.orderby.equals("popularity")?"name":"popularity";
        this.secondaryDirection = this.secondaryOrder.equals("name")?"asc":"desc";
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

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
