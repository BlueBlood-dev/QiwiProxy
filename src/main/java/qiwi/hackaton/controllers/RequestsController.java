package qiwi.hackaton.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qiwi.hackaton.models.Response;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.services.CachingService;

@Controller
public class RequestsController {

    private final CachingService cachingService;

    @Autowired
    public RequestsController(CachingService cachingService) {
        this.cachingService = cachingService;
    }

    @PutMapping
    @ResponseBody
    public Response createVisaPayment(@RequestBody Request request){
        return new Response();
    }


    @PostMapping
    @ResponseBody
    public Response createMasterPayment(@RequestBody Request request){
        return new Response();
    }

    @DeleteMapping
    public void deleteCache(){

    }
}
