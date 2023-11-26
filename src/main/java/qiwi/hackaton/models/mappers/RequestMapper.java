package qiwi.hackaton.models.mappers;

import qiwi.hackaton.models.Request;
import qiwi.hackaton.models.RequestDTO;

public class RequestMapper {
    public static RequestDTO toRequestDto(Request request){
        return new RequestDTO(request.getParameters(), request.getMethod(), request.getId());
    }
}
