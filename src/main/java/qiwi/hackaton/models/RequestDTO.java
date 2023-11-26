package qiwi.hackaton.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    @JsonProperty("params")
    ParametersDTO parameters;
    String method;
    UUID id;
}
