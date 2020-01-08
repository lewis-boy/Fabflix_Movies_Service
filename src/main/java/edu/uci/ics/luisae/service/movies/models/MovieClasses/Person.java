package edu.uci.ics.luisae.service.movies.models.MovieClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    @JsonProperty(value = "person_id", required = true)
    private Integer person_id;
    @JsonProperty(value = "name", required = true)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "gender")
    private String gender;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "birthday")
    private String birthday;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "deathday")
    private String deathday;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "biography")
    private String biography;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "birthplace")
    private String birthplace;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "popularity")
    private Double popularity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "profile_path")
    private String profile_path;

    public Person(){}

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "profile_path")
    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "popularity")
    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "birthplace")
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "biography")
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "deathday")
    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @JsonProperty(value = "name", required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(value = "person_id", required = true)
    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}
