package com.prohire.user.helper;

import org.apache.commons.lang.RandomStringUtils;

public class PasswordGeneratorHelper {

    public static String generatePassword(){
        return RandomStringUtils.randomAlphanumeric(5);
    }
}
