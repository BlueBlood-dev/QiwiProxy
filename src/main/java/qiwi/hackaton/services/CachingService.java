package qiwi.hackaton.services;

import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.Response;

import java.util.Optional;

public interface CachingService {
    Optional<Response> getCachedResponse(Request request);
}
