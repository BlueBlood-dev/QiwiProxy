package qiwi.hackaton.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qiwi.hackaton.models.Response;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.services.CachingService;
import qiwi.hackaton.services.RequestRedirectingService;

import java.util.Optional;

@Controller
public class RequestsController {

    private final CachingService cachingService;

    private final RequestRedirectingService requestRedirectingService;

    @Autowired
    public RequestsController(CachingService cachingService, RequestRedirectingService requestingService) {
        this.cachingService = cachingService;
        this.requestRedirectingService = requestingService;
    }

    @PutMapping("/visa")
    @ResponseBody
    public Response createVisaPayment(@RequestBody Request request){
        Optional<Response> response = cachingService.getCachedResponse(request);
        if(response.isEmpty()) {
            Response partnerResponse = requestRedirectingService.createVisaPayment(request);
            cachingService.saveToCache(request, partnerResponse);
            return partnerResponse;
        }
        return response.get();
    }


    @PostMapping("/master")
    @ResponseBody
    public Response createMasterPayment(@RequestBody Request request){
        Optional<Response> response = cachingService.getCachedResponse(request);
        if(response.isEmpty()) {
            Response partnerResponse = requestRedirectingService.createMasterPayment(request);
            cachingService.saveToCache(request, partnerResponse);
            return partnerResponse;
        }
        return response.get();
    }

    @DeleteMapping("/delete")
    public void deleteCache(){
        cachingService.clearCache();
    }

    @GetMapping("/ping")
    public void ping(){
        System.out.println("Pong");
    }
}
