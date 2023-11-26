package qiwi.hackaton.controllers.repositories;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Repository;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.URLParam;
import qiwi.hackaton.models.ParametersDTO;
import qiwi.hackaton.models.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class GuavaCacheRepository implements CacheRepository {
    private final Map<String, Cache<ParametersDTO, Response>> mapCaches = new HashMap<>();
    private final Cache<ParametersDTO, Response> emptyCache = CacheBuilder.newBuilder().build();

    @Override
    public Optional<Response> getCache(Request request) {
        var res = mapCaches.getOrDefault(request.getUrlParam().getPartner(), emptyCache);
        if (request.getParameters() == null)
            return Optional.empty();
        return Optional.ofNullable(res.getIfPresent(request.getParameters()));
    }

    @Override
    public Response saveToRepository(Request request, Response response) {
        String partner = request.getUrlParam().getPartner();
        mapCaches.putIfAbsent(partner, CacheBuilder.newBuilder().build());
        mapCaches.get(partner).put(request.getParameters(), response);
        return response;
    }

    @Override
    public void clearCache(String partner) {
        if (mapCaches.containsKey(partner)) {
            mapCaches.get(partner).invalidateAll();
        }
    }
}
