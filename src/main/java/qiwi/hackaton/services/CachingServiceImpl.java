package qiwi.hackaton.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qiwi.hackaton.controllers.repositories.CacheRepository;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.Response;

import java.util.Optional;

@Service
public class CachingServiceImpl implements CachingService{

    private final CacheRepository cacheRepository;
    @Autowired
    public CachingServiceImpl(CacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    @Override
    public Optional<Response> getCachedResponse(Request request) {
        return cacheRepository.getCache(request);
    }

    @Override
    public void saveToCache(Request request, Response response) {
        cacheRepository.saveToRepository(request, response);
    }


    @Override
    public void clearCache() {
        cacheRepository.clearCache();
    }
}
