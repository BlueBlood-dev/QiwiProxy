package qiwi.hackaton.services;

import qiwi.hackaton.models.ParametersDTO;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.URLParam;
import qiwi.hackaton.models.Response;

import java.util.Optional;

public interface CachingService {
    Optional<Response> getCachedResponse(Request request);

    Response saveToCache(Request request, Response response);

    void clearCache(String partner);
}
