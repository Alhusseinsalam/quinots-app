package dev.husein.quinots.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseError {
    @JsonProperty("Error")
    private String errMsg;
}
