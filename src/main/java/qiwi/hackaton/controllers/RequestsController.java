package qiwi.hackaton.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import qiwi.hackaton.models.URLParam;
import qiwi.hackaton.models.ParametersDTO;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.Response;
import qiwi.hackaton.services.CachingService;
import qiwi.hackaton.services.RequestRedirectingService;

import java.util.UUID;

@RestController
public class RequestsController {
    final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final XmlMapper xmlMapper = new XmlMapper();
    private final CachingService cachingService;

    private final RequestRedirectingService requestRedirectingService;

    @Autowired
    public RequestsController(CachingService cachingService, RequestRedirectingService requestingService) {
        this.cachingService = cachingService;
        this.requestRedirectingService = requestingService;
    }


    @DeleteMapping("/delete")
    public void deleteCache(HttpServletRequest httpServletRequest) {
        String partner = httpServletRequest.getServerName();
        cachingService.clearCache(partner);
    }

    @RequestMapping(value = {"/{paymentSystem}/{id}", "/{paymentSystem}"},
            method = {RequestMethod.POST, RequestMethod.PUT},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Response createPayment(@PathVariable(required = false) UUID id, HttpServletRequest httpServletRequest, @RequestBody ObjectNode jsonNode) {
        URLParam urlParam = new URLParam();
        Request request = new Request();
        ParametersDTO parametersDTO = null;
        try {
            request = mapper.treeToValue(jsonNode, Request.class);
            if (!jsonNode.has("params")) {
                parametersDTO = xmlMapper.treeToValue(jsonNode, ParametersDTO.class);
            } else {
                parametersDTO = mapper.treeToValue(jsonNode.get("params"), ParametersDTO.class);
                id = request.getId();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        urlParam.setPartner(httpServletRequest.getServerName());
        urlParam.setEndpoint(httpServletRequest.getRequestURI());
        request.setId(id);
        request.setUrlParam(urlParam);
        request.setParameters(parametersDTO);
        var cached = cachingService.getCachedResponse(request);
        if (cached.isEmpty())
            return cachingService.saveToCache(request, requestRedirectingService.createPayment(request)).withId(id);
        return cached.get().withId(id);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
