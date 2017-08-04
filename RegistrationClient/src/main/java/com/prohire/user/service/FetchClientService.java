package com.prohire.user.service;

import com.prohire.service.ProhireService;
import com.prohire.service.annotation.PHEndpoint;
import com.prohire.service.annotation.PHEndpoints;
import com.prohire.service.annotation.PHService;
import com.prohire.service.annotation.PHServiceMethod;
import com.prohire.user.dto.ClientDTO;

import java.util.List;

@PHService(minor = 1,major = 1)
@PHEndpoints(endpoints = {
        @PHEndpoint(name = FetchClientService.FETCH_ENDPOINT,synchronous = true)
})
public interface FetchClientService extends ProhireService {

    public static final String FETCH_ENDPOINT ="fetchClientEndpoint";
    public static final String FETCH_BY_NAME_METHOD ="fetchByName";
    public static final String FETCH_ACTIVE_CLIENTS ="fetchActiveClients";
    public static final String SERVICE_NAME ="FetchClientService";

    @PHServiceMethod(endPointName = FETCH_ENDPOINT,name =  FETCH_BY_NAME_METHOD)
    List<ClientDTO> fetchByName();

    @PHServiceMethod(endPointName = FETCH_ENDPOINT,name = FETCH_ACTIVE_CLIENTS)
    List<ClientDTO> fetchActiveClients();

}
