package com.prohire.user.client;

import com.prohire.service.annotation.PHServiceClient;
import com.prohire.user.service.RegistrationService;
import com.prohire.service.PHServiceInvoker;
import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;

@Component
@PHServiceClient
public class RegistrationClient implements RegistrationService {

private PHServiceInvoker serviceInvoker;


	@Override
	public com.prohire.user.dto.ResponseDTO registerClientMethod(final com.prohire.user.dto.ClientDTO clientDTO) {
		Class[] paramClasses = new Class[1];
		try {
			paramClasses[0] = Class.forName("com.prohire.user.dto.ClientDTO");
		} catch(ClassNotFoundException cnfe) { throw new IllegalStateException(cnfe); }

		try {
			return (com.prohire.user.dto.ResponseDTO) serviceInvoker.callSync("registerClientMethod", null, paramClasses, 60000, clientDTO);
		} catch(Throwable e){
			if(e instanceof com.prohire.service.exception.ProhireException) {
			com.prohire.service.exception.ProhireException pe = (com.prohire.service.exception.ProhireException) e;
				throw pe;
			} else {
			throw new IllegalStateException(e);
			}
		}
	}

	@Override
	public void setServiceInvoker(final PHServiceInvoker serviceInvoker) {
		this.serviceInvoker = serviceInvoker;
	}

}
