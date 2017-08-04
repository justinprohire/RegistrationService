package com.prohire.user.service;

import com.prohire.service.ProhireService;
import com.prohire.service.annotation.PHEndpoint;
import com.prohire.service.annotation.PHEndpoints;
import com.prohire.service.annotation.PHService;
import com.prohire.service.annotation.PHServiceMethod;
import com.prohire.user.dto.ClientDTO;

@PHService(minor = 1,major = 1)
@PHEndpoints(endpoints = {
        @PHEndpoint(name = LoginService.LOGIN_SYNC_ENDPOINT,synchronous = true)

})
public interface LoginService extends ProhireService {
    public static final String LOGIN_SYNC_ENDPOINT = "loginEndpointSync";

    public static final String LOGIN_REGISTRATION_METHOD = "loginClientMethod";
    public static final String SERVICE_NAME ="LOGIN_SERVICE";

    @PHServiceMethod(endPointName = LOGIN_SYNC_ENDPOINT,name = LOGIN_REGISTRATION_METHOD)
    String loginClientMethod(ClientDTO clientDTO);

}
