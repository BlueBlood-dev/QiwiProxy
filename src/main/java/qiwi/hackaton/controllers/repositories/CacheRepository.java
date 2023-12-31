package qiwi.hackaton.controllers.repositories;

import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.URLParam;
import qiwi.hackaton.models.Response;

import java.util.Optional;

public interface CacheRepository {
    Optional<Response> getCache(Request request);
    Response saveToRepository(Request request, Response response);
    void clearCache(String partner);
}
