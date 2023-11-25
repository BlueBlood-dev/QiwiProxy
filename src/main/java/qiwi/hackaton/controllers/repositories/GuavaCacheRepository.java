package qiwi.hackaton.controllers.repositories;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Repository;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.Response;

import java.util.Optional;

@Repository
public class GuavaCacheRepository implements CacheRepository{
    private final Cache<Request, Response> guavaCache = CacheBuilder.newBuilder().build();

    @Override
    public Optional<Response> getCache(Request request) {
        return Optional.ofNullable(guavaCache.getIfPresent(request));
    }

    @Override
    public void saveToRepository(Request request, Response response) {
        guavaCache.put(request,response);
    }

    @Override
    public void clearCache() {
        guavaCache.cleanUp();
    }
}
