package qiwi.hackaton.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import qiwi.hackaton.models.ParametersDTO;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.RequestDTO;
import qiwi.hackaton.models.Response;
import qiwi.hackaton.models.mappers.RequestMapper;

import java.util.List;

@Service
public class RequestRedirectingServiceImpl implements RequestRedirectingService {
    RestTemplate restTemplate;

    RequestRedirectingServiceImpl() {
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
    }

    @Override
    public Response createPayment(Request request) {
        ParametersDTO parameters = request.getParameters();
        String url = String.format("https://%s%s", request.getUrlParam().getPartner(), request.getUrlParam().getEndpoint());
        if (request.getMethod() != null) {
            return createPaymentPut(url, new HttpEntity<>(RequestMapper.toRequestDto(request)));
        } else {
            return createPaymentPost(url, parameters);
        }
    }

    private Response createPaymentPost(String url, ParametersDTO requestEntity) {
        ResponseEntity<Response> response = restTemplate.postForEntity(url, requestEntity, Response.class);
        return response.getBody();
    }

    private Response createPaymentPut(String url, HttpEntity<RequestDTO> requestEntity) {
        HttpEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Response.class);
        return response.getBody();
    }
}
