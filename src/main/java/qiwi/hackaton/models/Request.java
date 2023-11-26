package qiwi.hackaton.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;
@Data
public class Request {
    URLParam urlParam;
    @JsonProperty("params")
    ParametersDTO parameters;
    String method;
    UUID id;
}
