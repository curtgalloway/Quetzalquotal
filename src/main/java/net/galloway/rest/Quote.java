package net.galloway.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private Status status;
    //private Data data;

    public Quote() {}



    @Override
    public String toString() {
        return "";
    }
}
