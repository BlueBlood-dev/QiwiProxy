package qiwi.hackaton.services;

import org.springframework.stereotype.Service;
import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.Response;

import java.util.Optional;

@Service
public class CachingServiceImpl implements CachingService{
    @Override
    public Optional<Response> getCachedResponse(Request request) {
        return null;
    }
}
