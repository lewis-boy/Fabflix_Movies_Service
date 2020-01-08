package edu.uci.ics.luisae.service.movies.models.IdmModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.Base.RequestModel;

public class PrivilegeRequest extends RequestModel {
    @JsonProperty(value = "email", required = true)
    private String email;
    @JsonProperty(value = "plevel", required = true)
    private int plevel;
    public PrivilegeRequest(){}
    @JsonCreator public PrivilegeRequest(@JsonProperty(value = "email", required = true) String email,
                                         @JsonProperty(value = "plevel", required = true) int plevel){
        this.email = email;
        this.plevel = plevel;
    }

    public int getPlevel() {
        return plevel;
    }

    public void setPlevel(int plevel) {
        this.plevel = plevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
