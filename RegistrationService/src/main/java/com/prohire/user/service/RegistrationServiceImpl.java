package com.prohire.user.service;

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


    /**
     * This method will use the repository to register the System Client
     *
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO registerClientMethod(ClientDTO clientDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setRegistrationStatus(INVALID_DATA);
        if (clientDTO != null && ValidationHelper.validateClient(clientDTO)) {
            if (clientRepository.getSystemClientByEmail(clientDTO.getEmailAddress()) == null) {
                SystemClient clientModel = MapperHelper.getSystemClientFromDTO(clientDTO);
                RegistrationResponse response = registerClientRepository.registerSystemClient(clientModel);
                if (response != null) {
                    responseDTO = MapperHelper.mapRegistrationDTOFromResponse(response);
                    responseDTO.setRegistrationStatus(SUCCESS);
                    emailService.sendEmail(clientDTO.getEmailAddress());
                }
            } else {
                responseDTO.setRegistrationStatus(USER_ALREADY_REGISTERTED);
            }
        }
        loggingService.logData(SERVICE_NAME, "Registration status for user " + clientDTO.getEmailAddress()
                + " is " + responseDTO.getRegistrationStatus());

        return responseDTO;
    }


    @Override
    public void setServiceInvoker(PHServiceInvoker phServiceInvoker) {

    }


}
