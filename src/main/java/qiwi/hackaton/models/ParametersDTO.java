package qiwi.hackaton.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
@Data
@NoArgsConstructor
@XmlRootElement(name = "request")
public class ParametersDTO {
    @JacksonXmlProperty(localName = "pg_card_number")
    @JsonProperty("card_number")
    String cardNumber;
    @JacksonXmlProperty(localName = "pg_description")
    @JacksonXmlCData
    @JsonProperty("description")
    String description;
    @JacksonXmlProperty(localName = "pg_cardholder")
    @JacksonXmlCData
    @JsonProperty("card_holder_name")
    String cardHolder;
    @JacksonXmlProperty(localName = "pg_expire_date")
    @JsonProperty("card_expire")
    String expireDate;
    @JacksonXmlProperty(localName = "pg_cvv")
    @JsonProperty("card_cvc")
    String cvv;
    @JsonProperty("amount")
    String amount;
}
