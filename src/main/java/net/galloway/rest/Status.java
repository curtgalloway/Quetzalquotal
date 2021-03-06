package net.galloway.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Status {

    private String timestamp;
    private int errorCode;
    private String errorMessage;
    private int elapsed;
    private int creditCount;
    private String notice;

}
