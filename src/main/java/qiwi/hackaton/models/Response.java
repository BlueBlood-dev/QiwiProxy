package qiwi.hackaton.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Data
@XmlRootElement(name = "response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    @JacksonXmlProperty(localName = "pg_id")
    @JsonProperty("id")
    @JacksonXmlCData
    UUID id;
    @JsonProperty("result")
    @JacksonXmlProperty
    Result result;
    @JacksonXmlProperty(localName = "pg_status")
    @JacksonXmlCData
    String status;
    @JacksonXmlProperty(localName = "pg_description")
    @JacksonXmlCData
    String description;

    public Response withId(UUID id) {
        this.id = id;
        return this;
    }
}
