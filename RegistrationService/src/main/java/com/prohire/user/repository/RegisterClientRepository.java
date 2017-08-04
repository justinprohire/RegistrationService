package com.prohire.user.repository;

import com.prohire.user.model.RegistrationResponse;
import com.prohire.user.model.SystemClient;

public interface RegisterClientRepository {
    RegistrationResponse registerSystemClient(SystemClient clientModel);
}
