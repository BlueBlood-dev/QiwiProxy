package qiwi.hackaton.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Result {
    @JsonProperty("transaction_id")
    @JacksonXmlProperty(localName = "transaction_id")
    String transactionId;
    @JsonProperty("confirm_url")
    @JacksonXmlProperty(localName = "confirm_url")
    String confirmUrl;
}
