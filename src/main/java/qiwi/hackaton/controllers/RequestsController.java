package qiwi.hackaton.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qiwi.hackaton.models.Response;
import qiwi.hackaton.models.Request;
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

    @PutMapping("/visa")
    public Response createVisaPayment(@RequestBody Request request) {
        Optional<Response> response = cachingService.getCachedResponse(request);
        if (response.isEmpty()) {
            Response partnerResponse = requestRedirectingService.createVisaPayment(request);
            cachingService.saveToCache(request, partnerResponse);
            return partnerResponse;
        }
        return response.get();
    }


    @PostMapping("/master")
    public Response createMasterPayment(@RequestBody Request request) {
        Optional<Response> response = cachingService.getCachedResponse(request);
        if (response.isEmpty()) {
            Response partnerResponse = requestRedirectingService.createMasterPayment(request);
            cachingService.saveToCache(request, partnerResponse);
            return partnerResponse;
        }
        return response.get();
    }

    @DeleteMapping("/delete")
    public void deleteCache() {
        cachingService.clearCache();
    }

    @GetMapping("/ping")
    public String ping() {
        System.out.println("Pong");
        return "pong";
    }

    @RequestMapping(value = "/proxy/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> proxyRequest(@RequestBody(required = false) String body, HttpServletRequest request) {
        // Extract the request method and path from the original request
        String method = request.getMethod();
        String requestPath = request.getRequestURI();

        // Check if the request is for the specific endpoint
        if ("/ping".equals(requestPath)) {
            // Log "pong" to the server console
            System.out.println("pong");

            // Return a response (optional, you can return an empty response)
            return ResponseEntity.ok("pong");

        }
        return ResponseEntity.ok("ZHOPA)");
    }
}


