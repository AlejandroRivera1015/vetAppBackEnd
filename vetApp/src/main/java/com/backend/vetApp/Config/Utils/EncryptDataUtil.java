package com.backend.vetApp.Config.Utils;

import  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptDataUtil {

    public static String toEncrypt(String data) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return  encoder.encode(data);
    }

    public static boolean toDecrypt( String toCompareData , String encryptedData ){
       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return   encoder.matches(toCompareData, encryptedData);

    }



}
