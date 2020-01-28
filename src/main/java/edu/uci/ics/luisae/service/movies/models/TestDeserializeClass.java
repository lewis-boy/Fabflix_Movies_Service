package edu.uci.ics.luisae.service.movies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.TestDeserialize;

public class TestDeserializeClass {
    @JsonProperty(value = "tests", required = true)
    private TestDeserialize[] tests;

    TestDeserializeClass(){}

    public TestDeserialize[] getTests() {
        return tests;
    }

    public void setTests(TestDeserialize[] tests) {
        this.tests = tests;
    }
}
