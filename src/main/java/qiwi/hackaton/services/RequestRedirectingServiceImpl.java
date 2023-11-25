package qiwi.hackaton.services;

import org.springframework.stereotype.Service;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.Response;

@Service
public class RequestRedirectingServiceImpl implements RequestRedirectingService {

    @Override
    public Response createVisaPayment(Request request){
        return null;
    }

    @Override
    public Response createMasterPayment(Request request) {
        return null;
    }

}
