package com.prohire.user;

import com.prohire.user.dto.ClientDTO;
import com.prohire.user.dto.ResponseDTO;
import com.prohire.user.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    public static final String ERROR = "ERROR";
    private static final String SUCCESS = "SUCCESS";
    private static final String INVALID_DATA = "INVALID_DATA";
    private static final String USER_ALREADY_REGISTERTED = "USER_ALREADY_REGISTERTED";

    @Autowired
    RegistrationService registrationService;

    @Autowired
    LoginService loginService;

    @Autowired
    GenerateNewPasswordService generateNewPasswordService;

    @Autowired
    SuspendService suspendService;

    @Autowired
    FetchClientService fetchClientService;



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerSystemClient(@RequestBody ClientDTO client)  {
        ResponseDTO response = registrationService.registerClientMethod(client);
        if (response != null) {
            if (StringUtils.isNotEmpty(response.getPassword())) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("password", response.getPassword());
                return new ResponseEntity<String>(SUCCESS, headers, HttpStatus.CREATED);
            }
            if (USER_ALREADY_REGISTERTED.equalsIgnoreCase(response.getRegistrationStatus())) {
                return new ResponseEntity<String>(USER_ALREADY_REGISTERTED, HttpStatus.ALREADY_REPORTED);
            }
        }
        return new ResponseEntity<String>(INVALID_DATA, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> clientLogin(@RequestParam(value = "loginId") String emailAddress,
                                              @RequestParam(value = "password") String password)  {
        ClientDTO client = new ClientDTO();
        client.setEmailAddress(emailAddress);
        client.setPassword(password);
        String text = loginService.loginClientMethod(client);
        return new ResponseEntity<String>(text, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestNewPassword", method = RequestMethod.PUT)
    public ResponseEntity<String> requestNewPassword(@RequestParam(value = "loginId") String emailAddress) {
        if (StringUtils.isNotEmpty(emailAddress)) {
            ResponseDTO responseDTO = generateNewPasswordService.requestNewPasswordMethod(emailAddress);
            if (responseDTO != null && SUCCESS.equals(responseDTO.getRegistrationStatus())) {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("password", responseDTO.getPassword());
                return new ResponseEntity<String>(SUCCESS, httpHeaders, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>(responseDTO.getRegistrationStatus(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(ERROR, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/suspendClient", method = RequestMethod.PUT)
    public ResponseEntity<String> suspendClient(@RequestParam(value = "loginId") String emailAddress)  {
        if (StringUtils.isNotEmpty(emailAddress)) {
            ResponseDTO responseDTO = suspendService.suspendClientMethod(emailAddress);
            if (responseDTO != null && SUCCESS.equals(responseDTO.getRegistrationStatus())) {
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(responseDTO.getRegistrationStatus(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(ERROR, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/unSuspendClient", method = RequestMethod.PUT)
    public ResponseEntity<String> unSuspendClient(@RequestParam(value = "loginId") String emailAddress)  {
        if (StringUtils.isNotEmpty(emailAddress)) {
            ResponseDTO responseDTO = suspendService.unSuspendClientMethod(emailAddress);
            if (responseDTO != null && SUCCESS.equals(responseDTO.getRegistrationStatus())) {
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(responseDTO.getRegistrationStatus(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(ERROR, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/fetchByName",method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getListByName(){
        List<ClientDTO> returnList = fetchClientService.fetchByName();
        return new ResponseEntity<List<ClientDTO>>(returnList,HttpStatus.OK);
    }

    @RequestMapping(value = "/fetchActiveClients", method= RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getListofActiveClients(){
        List<ClientDTO> returnList = fetchClientService.fetchActiveClients();
        return new ResponseEntity<List<ClientDTO>>(returnList,HttpStatus.OK);
    }




}
