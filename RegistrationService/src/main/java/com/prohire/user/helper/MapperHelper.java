package com.prohire.user.helper;

import com.prohire.user.dto.AddressDTO;
import com.prohire.user.dto.ClientDTO;
import com.prohire.user.dto.PhoneNumbersDTO;
import com.prohire.user.dto.ResponseDTO;
import com.prohire.user.model.AddressModel;
import com.prohire.user.model.PhoneNumbers;
import com.prohire.user.model.RegistrationResponse;
import com.prohire.user.model.SystemClient;

public class MapperHelper {

    public static SystemClient getSystemClientFromDTO(ClientDTO clientDTO){
        SystemClient clientModel = new SystemClient();
        clientModel.setEmailAddress(clientDTO.getEmailAddress());
        clientModel.setFirstName(clientDTO.getFirstName());
        clientModel.setLastName(clientDTO.getLastName());
        if(clientDTO.getAddress()!=null){
            AddressModel addressModel = new AddressModel();
            addressModel.setDoorNumber(clientDTO.getAddress().getDoorNumber());
            addressModel.setPostCode(clientDTO.getAddress().getPostCode());
            addressModel.setStreet(clientDTO.getAddress().getStreet());
            clientModel.setAddress(addressModel);
        }
        if(clientDTO.getPhoneNumbers()!=null){
            PhoneNumbers phoneNumbers = new PhoneNumbers();
            phoneNumbers.setHome(clientDTO.getPhoneNumbers().getHome());
            phoneNumbers.setMobile(clientDTO.getPhoneNumbers().getMobile());
            phoneNumbers.setWork(clientDTO.getPhoneNumbers().getWork());
            clientModel.setPhoneNumbers(phoneNumbers);
        }

        return clientModel;
    }

    public static ClientDTO getDTOFromSystemClient(SystemClient client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setActive(client.isActive());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setEmailAddress(client.getEmailAddress());
        clientDTO.setPassword(client.getPassword());
        if(client.getAddress()!=null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setDoorNumber(client.getAddress().getDoorNumber());
            addressDTO.setPostCode(client.getAddress().getPostCode());
            addressDTO.setStreet(client.getAddress().getStreet());
            clientDTO.setAddress(addressDTO);
        }
        if(client.getPhoneNumbers()!=null){
            PhoneNumbersDTO phoneNumbersDTO = new PhoneNumbersDTO();
            phoneNumbersDTO.setHome(client.getPhoneNumbers().getHome());
            phoneNumbersDTO.setMobile(client.getPhoneNumbers().getMobile());
            phoneNumbersDTO.setWork(client.getPhoneNumbers().getWork());
            clientDTO.setPhoneNumbers(phoneNumbersDTO);
        }
        return clientDTO;
    }

    public static ResponseDTO mapRegistrationDTOFromResponse(RegistrationResponse response) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setRegistrationStatus(response.getRegistrationStatus());
        responseDTO.setPassword(response.getPassword());
        responseDTO.setEmailAddress(response.getEmailAddress());
        return responseDTO;
    }
}
