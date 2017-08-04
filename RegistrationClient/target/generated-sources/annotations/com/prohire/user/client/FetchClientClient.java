package com.prohire.user.client;

import com.prohire.service.annotation.PHServiceClient;
import com.prohire.user.service.FetchClientService;
import com.prohire.service.PHServiceInvoker;
import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;

@Component
@PHServiceClient
public class FetchClientClient implements FetchClientService {

private PHServiceInvoker serviceInvoker;


	@Override
	public java.util.List<com.prohire.user.dto.ClientDTO> fetchByName() {
		Class[] paramClasses = null;

		try {
			return (java.util.List<com.prohire.user.dto.ClientDTO>) serviceInvoker.callSync("fetchByName", null, paramClasses, 60000);
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
	public java.util.List<com.prohire.user.dto.ClientDTO> fetchActiveClients() {
		Class[] paramClasses = null;

		try {
			return (java.util.List<com.prohire.user.dto.ClientDTO>) serviceInvoker.callSync("fetchActiveClients", null, paramClasses, 60000);
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
