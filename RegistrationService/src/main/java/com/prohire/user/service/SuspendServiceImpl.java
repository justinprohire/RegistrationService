package com.prohire.user.service;

import com.prohire._domain.HireCompany;
import com.prohire.event.PHEventDispatcher;
import com.prohire.event.annotation.PHEventType;
import com.prohire.event.exception.EventDispatchException;
import com.prohire.service.PHServiceInvoker;
import com.prohire.user.dto.ResponseDTO;
import com.prohire.user.helper.ValidationHelper;
import com.prohire.user.model.SystemClient;
import com.prohire.user.repository.ClientRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuspendServiceImpl implements SuspendService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoggingService loggingService;

    @Autowired
    PHEventDispatcher eventDispatcher;


    private static final String INVALID_EMAIL = "INVALID_EMAIL";
    private static final String EMAIL_NOT_FOUND = "EMAIL_NOT_FOUND";
    private static final String SUCCESS = "SUCCESS";
    private static final String ERROR = "ERROR";

    /**
     * This method will call the setActive method
     *
     * @param emailAddress
     * @return
     */
    @Override
    public ResponseDTO suspendClientMethod(String emailAddress) {
        ResponseDTO responseDTO = null;
        try {
            responseDTO = setActive(emailAddress, false);
            loggingService.logData(SERVICE_NAME, "Suspend status for " + emailAddress +
                    " is " + responseDTO.getRegistrationStatus());
            eventDispatcher.sendEvent(PHEventType.TARIFF_UPDATE, "Suspend done");
        } catch (EventDispatchException e) {
            e.printStackTrace();
        }
        return responseDTO;
    }


    /**
     * This method will call the setActive method
     *
     * @param emailAddress
     * @return
     */
    @Override
    public ResponseDTO unSuspendClientMethod(String emailAddress)  {
        ResponseDTO responseDTO = setActive(emailAddress, true);
        try {
            loggingService.logData(SERVICE_NAME, "UnSuspend status for " + emailAddress +
                    " is " + responseDTO.getRegistrationStatus());

            eventDispatcher.sendEvent(PHEventType.TARIFF_UPDATE,"Un Suspend done");
        } catch (EventDispatchException e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    @Override
    public void setServiceInvoker(PHServiceInvoker phServiceInvoker) {

    }

    /**
     * This method will validate the email address and then update the active
     * flag
     *
     * @param emailAddress
     * @param active
     * @return
     */
    private ResponseDTO setActive(String emailAddress, boolean active) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setRegistrationStatus(ERROR);
        if (ValidationHelper.isValidEmail(emailAddress)) {
            SystemClient client = clientRepository.getSystemClientByEmail(emailAddress);
            if (client != null && StringUtils.isNotEmpty(client.getId())) {
                client.setActive(active);
                clientRepository.updateSystemClient(client);
                responseDTO.setRegistrationStatus(SUCCESS);
            } else {
                responseDTO.setRegistrationStatus(EMAIL_NOT_FOUND);
            }
        } else {
            responseDTO.setRegistrationStatus(INVALID_EMAIL);
        }
        return responseDTO;
    }
}
