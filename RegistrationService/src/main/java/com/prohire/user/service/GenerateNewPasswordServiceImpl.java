package com.prohire.user.service;

import com.prohire.event.PHEventDispatcher;
import com.prohire.event.annotation.PHEventType;
import com.prohire.event.exception.EventDispatchException;
import com.prohire.service.PHServiceInvoker;
import com.prohire.user.dto.ResponseDTO;
import com.prohire.user.helper.PasswordGeneratorHelper;
import com.prohire.user.helper.ValidationHelper;
import com.prohire.user.model.SystemClient;
import com.prohire.user.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateNewPasswordServiceImpl implements GenerateNewPasswordService{

    public static final String SUCCESS ="SUCCESS";
    public static final String EMAIL_NOT_FOUND ="EMAIL_NOT_FOUND";
    public static final String INVALID_EMAIL ="INVLAID_EMAIL";


    @Autowired
    ClientRepository fetchClientRepository;

    @Autowired
    LoggingService loggingService;

    @Autowired
    PHEventDispatcher eventDispatcher;

    @Override
    public ResponseDTO requestNewPasswordMethod(String emailAddress) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setRegistrationStatus(INVALID_EMAIL);
            if(ValidationHelper.isValidEmail(emailAddress)){
                SystemClient client = fetchClientRepository.getSystemClientByEmail(emailAddress);
                if (client != null) {
                    String newPassword = PasswordGeneratorHelper.generatePassword();
                    client.setPassword(newPassword);
                    responseDTO.setEmailAddress(emailAddress);
                    responseDTO.setPassword(newPassword);
                    fetchClientRepository.updateSystemClient(client);
                    responseDTO.setRegistrationStatus(SUCCESS);
                } else {
                    responseDTO.setRegistrationStatus(EMAIL_NOT_FOUND);
                }
            }
            loggingService.logData(SERVICE_NAME,"Password regeneration status for user " + emailAddress
                    + " is " + responseDTO.getRegistrationStatus());
            //logging event for kafka
            eventDispatcher.sendEvent(PHEventType.TARIFF_UPDATE,"Login for " + emailAddress
                    + " is SUCCESS" );
        } catch (EventDispatchException e) {
            e.printStackTrace();
        }

        return  responseDTO;
    }

    @Override
    public void setServiceInvoker(PHServiceInvoker phServiceInvoker) {

    }
}
