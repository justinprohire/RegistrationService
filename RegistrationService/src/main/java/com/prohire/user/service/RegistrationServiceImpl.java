package com.prohire.user.service;

import com.prohire._domain.HireCompany;
import com.prohire.event.PHEventDispatcher;
import com.prohire.event.annotation.PHEventType;
import com.prohire.event.exception.EventDispatchException;
import com.prohire.service.PHServiceInvoker;
import com.prohire.user.dto.ClientDTO;
import com.prohire.user.dto.ResponseDTO;
import com.prohire.user.helper.MapperHelper;
import com.prohire.user.helper.ValidationHelper;
import com.prohire.user.model.RegistrationResponse;
import com.prohire.user.model.SystemClient;
import com.prohire.user.repository.ClientRepository;
import com.prohire.user.repository.RegisterClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final String USER_ALREADY_REGISTERTED = "USER_ALREADY_REGISTERTED";
    private static final String SUCCESS = "SUCCESS";
    private static final String INVALID_DATA = "INVALID_DATA";

    @Autowired
    RegisterClientRepository registerClientRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoggingService loggingService;

    @Autowired
    EmailService emailService;

    @Autowired
    PHEventDispatcher eventDispatcher;


    /**
     * This method will use the repository to register the System Client
     *
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO registerClientMethod(ClientDTO clientDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setRegistrationStatus(INVALID_DATA);
            if (clientDTO != null && ValidationHelper.validateClient(clientDTO)) {
                if (clientRepository.getSystemClientByEmail(clientDTO.getEmailAddress()) == null) {
                    SystemClient clientModel = MapperHelper.getSystemClientFromDTO(clientDTO);
                    RegistrationResponse response = registerClientRepository.registerSystemClient(clientModel);
                    if (response != null) {
                        responseDTO = MapperHelper.mapRegistrationDTOFromResponse(response);
                        responseDTO.setRegistrationStatus(SUCCESS);
                        //email logging service
                        emailService.sendEmail(clientDTO.getEmailAddress());
                        HireCompany emailObj = new HireCompany();
                        emailObj.setShortCode(" Send Email after registration");
                        //email event to kafka
                        eventDispatcher.sendEvent(PHEventType.USER_UPDATE,emailObj);

                    }
                } else {
                    responseDTO.setRegistrationStatus(USER_ALREADY_REGISTERTED);
                }
            }
            //logging service call
            loggingService.logData(SERVICE_NAME, "Registration status for user " + clientDTO.getEmailAddress()
                    + " is " + responseDTO.getRegistrationStatus());
            //logging event to kafka
            eventDispatcher.sendEvent(PHEventType.TARIFF_UPDATE,"User registration for "
                    +clientDTO.getEmailAddress() +" is " + responseDTO.getRegistrationStatus());
        } catch (EventDispatchException e) {
            e.printStackTrace();
        }

        return responseDTO;
    }


    @Override
    public void setServiceInvoker(PHServiceInvoker phServiceInvoker) {

    }


}
