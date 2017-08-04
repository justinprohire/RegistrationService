package com.prohire.user.helper;

import com.prohire.user.dto.ClientDTO;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {

    private static final String EMAIL_PATTREN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTREN,Pattern.CASE_INSENSITIVE);

    /**
     * This method will validate the email address
     * @param emailAddress
     * @return
     */
    public static boolean isValidEmail(String emailAddress){
        boolean myReturn = false;
        if(StringUtils.isNotEmpty(emailAddress)){
            Matcher matcher = pattern.matcher(emailAddress);
            myReturn = matcher.matches();
        }
        return myReturn;
    }

    /**
     * This method will validate the clientDTO object for registration
     * @param clientDTO
     * @return
     */
    public static boolean validateClient(ClientDTO clientDTO) {
        boolean myReturn = false;
        if(clientDTO!=null &&
                StringUtils.isNotEmpty(clientDTO.getFirstName()) &&
                StringUtils.isNotEmpty(clientDTO.getLastName()) &&
                isValidEmail(clientDTO.getEmailAddress()) &&
                clientDTO.getPhoneNumbers()!=null &&
                clientDTO.getAddress() !=null){
            myReturn = true;
        }
        return  myReturn;
    }
}
