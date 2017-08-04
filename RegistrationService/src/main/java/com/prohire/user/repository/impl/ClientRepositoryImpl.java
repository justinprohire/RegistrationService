package com.prohire.user.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prohire.entity.json.template.JsonbRowMapper;
import com.prohire.entity.json.template.JsonbTemplate;
import com.prohire.entity.json.template.predicate.JsonbCondition;
import com.prohire.entity.json.template.predicate.KeyValueCondition;
import com.prohire.user.model.SystemClient;
import com.prohire.user.repository.ClientRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    @Autowired
    JsonbTemplate jsonbTemplate;

    private JsonbRowMapper<SystemClient> rowMapper;

    public ClientRepositoryImpl(){this.rowMapper = new JsonbRowMapper<>(new ObjectMapper());}

    /**
     * This method will fetch for a System client using the email address
     *
     * @param emailAddress
     * @return
     */
    @Override
    public SystemClient getSystemClientByEmail(String emailAddress) {
        SystemClient foundClient = null;
        if(StringUtils.isNotEmpty(emailAddress)){
            foundClient = jsonbTemplate.queryForObject(SystemClient.class,"(aggregate_data->>'emailAddress' = ?) ",emailAddress.trim());
        }
        return foundClient;
    }

    /**
     * This method will be used to update a client
     * @param client
     */
    @Override
    public void updateSystemClient(SystemClient client) {
        if(client!=null && StringUtils.isNotEmpty(client.getId())){
            jsonbTemplate.save(client);
        }
    }

    /**
     * This method will be used to get all the clients having
     * name 'Justin' or 'Andy'
     * @return
     */
    @Override
    public List<SystemClient> getSytemClientsByNames() {
        JsonbCondition nameConditon = KeyValueCondition.equal("firstName","Justin")
                                        .or(KeyValueCondition.equal("firstName","Andy"));
        return jsonbTemplate.query(SystemClient.class,nameConditon);
    }

    /**
     * This method will be used to return all the active clients
     * @return
     */
    @Override
    public List<SystemClient> getActiveSytemClients() {
        JsonbCondition activeUserCondition = KeyValueCondition.equal("active",Boolean.TRUE);
        return  jsonbTemplate.query(SystemClient.class,activeUserCondition);
    }
}
