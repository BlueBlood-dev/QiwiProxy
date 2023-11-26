package qiwi.hackaton.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qiwi.hackaton.controllers.repositories.CacheRepository;
import qiwi.hackaton.models.ParametersDTO;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.URLParam;
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
    public Optional<Response> getCachedResponse(Request parameters) {
        return cacheRepository.getCache(parameters);
    }

    @Override
    public Response saveToCache(Request parameters, Response response) {
        return cacheRepository.saveToRepository(parameters, response);
    }


    @Override
    public void clearCache(String partner) {
        cacheRepository.clearCache(partner);
    }
}
