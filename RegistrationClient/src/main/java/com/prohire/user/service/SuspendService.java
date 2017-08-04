package com.prohire.user.service;

import com.prohire.service.ProhireService;
import com.prohire.service.annotation.PHEndpoint;
import com.prohire.service.annotation.PHEndpoints;
import com.prohire.service.annotation.PHService;
import com.prohire.service.annotation.PHServiceMethod;
import com.prohire.user.dto.ResponseDTO;

@PHService(major = 1,minor = 1)
@PHEndpoints(endpoints = {
        @PHEndpoint(name = SuspendService.SYNC_ENDPOINT,synchronous = true)
})
public interface SuspendService extends ProhireService {

    public static final String SYNC_ENDPOINT = "suspendServiceEndpoint";
    public static final String SUSPEND_METHOD = "suspendClientMethod";
    public static final String UNSUSPEND_METHOD = "unSuspendClientMethod";

    public static final String SERVICE_NAME ="SUSPEND_SERVICE";

    @PHServiceMethod(endPointName = SYNC_ENDPOINT,name = SUSPEND_METHOD)
    ResponseDTO  suspendClientMethod(String emailAddress);

    @PHServiceMethod(endPointName = SYNC_ENDPOINT,name=UNSUSPEND_METHOD)
    ResponseDTO unSuspendClientMethod(String emailAddress);
}
