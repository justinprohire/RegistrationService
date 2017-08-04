package com.prohire.user.repository;

import com.prohire.user.model.SystemClient;

import java.util.List;

public interface ClientRepository {

    SystemClient getSystemClientByEmail(String emailAddress);
    void updateSystemClient(SystemClient client);
    List<SystemClient> getSytemClientsByNames();
    List<SystemClient> getActiveSytemClients();

}
