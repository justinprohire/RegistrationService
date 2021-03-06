package com.prohire.user.client;

import com.prohire.service.annotation.PHServiceClient;
import com.prohire.user.service.SuspendService;
import com.prohire.service.PHServiceInvoker;
import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;

@Component
@PHServiceClient
public class SuspendClient implements SuspendService {

private PHServiceInvoker serviceInvoker;


	@Override
	public com.prohire.user.dto.ResponseDTO suspendClientMethod(final String emailAddress) {
		Class[] paramClasses = new Class[1];
		try {
			paramClasses[0] = Class.forName("java.lang.String");
		} catch(ClassNotFoundException cnfe) { throw new IllegalStateException(cnfe); }

		try {
			return (com.prohire.user.dto.ResponseDTO) serviceInvoker.callSync("suspendClientMethod", null, paramClasses, 60000, emailAddress);
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
	public com.prohire.user.dto.ResponseDTO unSuspendClientMethod(final String emailAddress) {
		Class[] paramClasses = new Class[1];
		try {
			paramClasses[0] = Class.forName("java.lang.String");
		} catch(ClassNotFoundException cnfe) { throw new IllegalStateException(cnfe); }

		try {
			return (com.prohire.user.dto.ResponseDTO) serviceInvoker.callSync("unSuspendClientMethod", null, paramClasses, 60000, emailAddress);
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
