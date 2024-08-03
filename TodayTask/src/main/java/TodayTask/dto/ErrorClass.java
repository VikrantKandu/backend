package TodayTask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorClass {

    @JsonProperty
    private  String errorCode;
    @JsonProperty
    private  String errorMessage;

    public ErrorClass(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    // getters for errorCode and errorMessage
}