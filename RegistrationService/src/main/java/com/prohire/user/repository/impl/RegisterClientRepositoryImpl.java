package com.prohire.user.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prohire.entity.json.template.JsonbRowMapper;
import com.prohire.entity.json.template.JsonbTemplate;
import com.prohire.user.helper.PasswordGeneratorHelper;
import com.prohire.user.model.RegistrationResponse;
import com.prohire.user.model.SystemClient;
import com.prohire.user.repository.RegisterClientRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterClientRepositoryImpl implements RegisterClientRepository {

    @Autowired
    JsonbTemplate jsonbTemplate;
    private JsonbRowMapper<SystemClient> rowMapper;
    public RegisterClientRepositoryImpl() {
        this.rowMapper = new JsonbRowMapper<>(new ObjectMapper());
    }

    /**
     * This method will save the details a new client after generating a random password.
     * If the user is present or invalid data is provided the details will not be saved
     * and an error response will be provided.
     *
     * @param clientModel
     * @return
     */
    @Override
    public RegistrationResponse registerSystemClient(SystemClient clientModel) {
        RegistrationResponse response = new RegistrationResponse();
            clientModel.setPassword(PasswordGeneratorHelper.generatePassword());
            jsonbTemplate.save(clientModel);
            response.setPassword(clientModel.getPassword());
            response.setEmailAddress(clientModel.getEmailAddress());
        return response;
    }

    /**
     * This method will validate a SytemClientModel object
     *
     * @param clientModel
     * @return
     */
    private boolean isClientDataValid(SystemClient clientModel) {
        boolean myReturn = true;
        if (StringUtils.isEmpty(clientModel.getFirstName()) ||
                StringUtils.isEmpty(clientModel.getLastName()) ||
                StringUtils.isEmpty(clientModel.getEmailAddress()) ||
                clientModel.getAddress() == null ||
                clientModel.getPhoneNumbers() == null) {
            myReturn = false;
        }
        return myReturn;
    }
}
