package com.prohire.user.service;

import com.prohire.event.PHEventDispatcher;
import com.prohire.event.annotation.PHEventType;
import com.prohire.event.exception.EventDispatchException;
import com.prohire.service.PHServiceInvoker;
import com.prohire.user.dto.ClientDTO;
import com.prohire.user.helper.ValidationHelper;
import com.prohire.user.model.SystemClient;
import com.prohire.user.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String CLIENT_SUSPENDED = "SUSPENDED";
    public static final String INVLAID_EMAIL = "INVALID EMAIL";

    @Autowired
    ClientRepository fetchClientRepository;


    @Autowired
    LoggingService loggingService;

    @Autowired
    PHEventDispatcher eventDispatcher;


    /**
     * This method will check for the email and password to be
     * valid and returns success or fail
     *
     * @param clientDTO
     * @return String
     */
    @Override
    public String loginClientMethod(ClientDTO clientDTO) {
        try {
            if (clientDTO != null && ValidationHelper.isValidEmail(clientDTO.getEmailAddress())) {
                SystemClient client = fetchClientRepository.getSystemClientByEmail(clientDTO.getEmailAddress());
                if (client != null && client.isActive()) {
                    if (client.getPassword().equals(clientDTO.getPassword())) {
                        System.out.println("*********Login Success*********");
                        //logging to Logging Service
                        loggingService.logData(SERVICE_NAME, "Login Success for " + clientDTO.getEmailAddress());
                        //logging event for kafka
                        eventDispatcher.sendEvent(PHEventType.TARIFF_UPDATE,"Login for " + clientDTO.getEmailAddress()
                        + " is SUCCESS" );
                        return SUCCESS;
                    } else {
                        System.out.println("*********Invalid Login*********");
                        loggingService.logData(SERVICE_NAME, "Login Failure for " + clientDTO.getEmailAddress());
                        //logging event for kafka
                        eventDispatcher.sendEvent(PHEventType.TARIFF_UPDATE,"Login for " + clientDTO.getEmailAddress()
                                + " is FAIL" );
                        return FAIL;
                    }
                } else {
                    System.out.println("*********Inactive Client*********");
                    loggingService.logData(SERVICE_NAME, clientDTO.getEmailAddress() + " is suspended");
                    //logging event for kafka
                    eventDispatcher.sendEvent(PHEventType.TARIFF_UPDATE,"Login for " + clientDTO.getEmailAddress()
                            + " is SUSPENDED" );
                    return CLIENT_SUSPENDED;
                }
            }
        } catch (EventDispatchException e) {
            e.printStackTrace();
        }
        return INVLAID_EMAIL;

    }

    @Override
    public void setServiceInvoker(PHServiceInvoker phServiceInvoker) {

    }
}
