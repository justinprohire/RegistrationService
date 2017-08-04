package com.prohire.user.client;

import com.prohire.service.annotation.PHServiceClient;
import com.prohire.user.service.LoginService;
import com.prohire.service.PHServiceInvoker;
import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;

@Component
@PHServiceClient
public class LoginClient implements LoginService {

private PHServiceInvoker serviceInvoker;


	@Override
	public String loginClientMethod(final com.prohire.user.dto.ClientDTO clientDTO) {
		Class[] paramClasses = new Class[1];
		try {
			paramClasses[0] = Class.forName("com.prohire.user.dto.ClientDTO");
		} catch(ClassNotFoundException cnfe) { throw new IllegalStateException(cnfe); }

		try {
			return (String) serviceInvoker.callSync("loginClientMethod", null, paramClasses, 60000, clientDTO);
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
