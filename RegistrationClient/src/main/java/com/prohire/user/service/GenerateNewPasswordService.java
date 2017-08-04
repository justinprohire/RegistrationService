package com.prohire.user.service;


import com.prohire.service.ProhireService;
import com.prohire.service.annotation.PHEndpoint;
import com.prohire.service.annotation.PHEndpoints;
import com.prohire.service.annotation.PHService;
import com.prohire.service.annotation.PHServiceMethod;
import com.prohire.user.dto.ResponseDTO;

@PHService(minor = 1,major = 1)
@PHEndpoints(endpoints = {
        @PHEndpoint(name = GenerateNewPasswordService.REQUEST_NEW_PASSWORD_ENPOINT,synchronous = true)
})
public interface GenerateNewPasswordService extends ProhireService {

    public static final String REQUEST_NEW_PASSWORD_ENPOINT ="requestNewPassword";
    public static final String REQUEST_NEW_PASSWORD_METHOD ="requestNewPasswordMethod";

    public static final String SERVICE_NAME ="GenerateNewPasswordService";

    @PHServiceMethod(endPointName = REQUEST_NEW_PASSWORD_ENPOINT,name = REQUEST_NEW_PASSWORD_METHOD)
    ResponseDTO requestNewPasswordMethod(String emailAddress);



}
