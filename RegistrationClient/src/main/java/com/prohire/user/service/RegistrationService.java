package com.prohire.user.service;

import com.prohire.service.ProhireService;
import com.prohire.service.annotation.PHEndpoint;
import com.prohire.service.annotation.PHEndpoints;
import com.prohire.service.annotation.PHService;
import com.prohire.service.annotation.PHServiceMethod;
import com.prohire.user.dto.ClientDTO;
import com.prohire.user.dto.ResponseDTO;

@PHService(minor = 1, major = 1)
@PHEndpoints(endpoints = {
        @PHEndpoint(name = RegistrationService.REGISTRATION_SYNC_ENDPOINT, synchronous = true),
        @PHEndpoint(name = RegistrationService.REGISTRATION_ASYNC_ENDPOINT, synchronous = false)
})
public interface RegistrationService extends ProhireService {

    public static final String REGISTRATION_SYNC_ENDPOINT = "registrationEndpointSync";
    public static final String REGISTRATION_ASYNC_ENDPOINT = "registrationEndpointAsync";
    public static final String CLIENT_REGISTRATION_METHOD = "registerClientMethod";


    public static final String SERVICE_NAME ="REGISTRATION_SERVICE";

    @PHServiceMethod(endPointName = REGISTRATION_SYNC_ENDPOINT, name = CLIENT_REGISTRATION_METHOD)
    ResponseDTO registerClientMethod(ClientDTO clientDTO);

}
