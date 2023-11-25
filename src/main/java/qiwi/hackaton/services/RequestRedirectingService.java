package qiwi.hackaton.services;

import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.Response;

public interface RequestRedirectingService {
    Response createVisaPayment(Request request);
    Response createMasterPayment(Request request);
}
