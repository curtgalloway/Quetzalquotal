package net.galloway.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    private String timestamp;
    private int errorCode;
    private String errorMessage;
    private int elapsed;
    private int creditCount;

}
