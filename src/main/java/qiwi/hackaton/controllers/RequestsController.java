package qiwi.hackaton.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.Response;
import qiwi.hackaton.services.CachingService;
import qiwi.hackaton.services.RequestRedirectingService;

import java.util.Optional;

@RestController
public class RequestsController {

    private final CachingService cachingService;

    private final RequestRedirectingService requestRedirectingService;

    @Autowired
    public RequestsController(CachingService cachingService, RequestRedirectingService requestingService) {
        this.cachingService = cachingService;
        this.requestRedirectingService = requestingService;
    }


    @DeleteMapping("/delete")
    public void deleteCache(){
        cachingService.clearCache();
    }

    @RequestMapping(value = "/**", method = {RequestMethod.POST, RequestMethod.PUT})
    public String createPayment(HttpServletRequest httpServletRequest){

        return httpServletRequest.getServletPath();
    }
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
