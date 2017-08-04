package com.prohire.user.service;

import com.prohire.event.PHEventDispatcher;
import com.prohire.service.PHServiceInvoker;
import com.prohire.user.dto.ClientDTO;
import com.prohire.user.helper.MapperHelper;
import com.prohire.user.model.SystemClient;
import com.prohire.user.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FetchClientServiceImpl implements FetchClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoggingService loggingService;

    @Override
    public List<ClientDTO> fetchByName() {
        List<ClientDTO> returnList =  new ArrayList<>();
        List<SystemClient> clientList = clientRepository.getSytemClientsByNames();
        if(!clientList.isEmpty()){
            clientList.forEach(a->{
                returnList.add(MapperHelper.getDTOFromSystemClient(a));
            });
        }
        loggingService.logData(SERVICE_NAME,"there are " + returnList.size() + " clients by name");
        return returnList;
    }

    @Override
    public List<ClientDTO> fetchActiveClients() {
        List<ClientDTO> returnList =  new ArrayList<>();
        List<SystemClient> clientList = clientRepository.getActiveSytemClients();
        if(!clientList.isEmpty()){
            clientList.forEach(a->{
                returnList.add(MapperHelper.getDTOFromSystemClient(a));
            });
        }
        loggingService.logData(SERVICE_NAME,"there are " + returnList.size() + " active clients");
        return returnList;
    }

    @Override
    public void setServiceInvoker(PHServiceInvoker phServiceInvoker) {

    }
}
